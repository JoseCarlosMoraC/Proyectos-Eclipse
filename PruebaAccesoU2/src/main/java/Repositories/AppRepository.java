package Repositories;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import Exception.AppException;
import Models.Evento;
import Models.PlanActivo;
import Models.Preferencias;
import Models.Usuario;

public class AppRepository {
private static final String NOMBRE_COLECCION = "MoraBD";
private final MongoCollection<Document> coleccion;

public AppRepository(MongoDatabase db) {
	super();
	this.coleccion = db.getCollection(NOMBRE_COLECCION);
}
private Usuario fromUsuarioDocument2Java(Document doc) {
	Usuario u = new Usuario();
	u.setId(doc.getString("id"));
	u.setUsername(doc.getString("username"));
	u.setEmail(doc.getString("email"));
	u.setPlan_activo(PlanActivo.valueOf(doc.getString("plan_activo")));
	u.setDispositivo(doc.getString("dispositivo"));
	
	Document docPreferencias = doc.get("preferencias", Document.class);
	Preferencias p = new Preferencias();
	p.setTema_oscuro(doc.getBoolean("tema_oscuro", false));
	p.setIdioma(doc.getString("idioma"));
	p.setNotificaciones_push(doc.getBoolean("notificaciones_push", false));
	p.setLimite_datos_moviles(doc.getBoolean("limite_datos_moviles", false));
	
	
	
	List<Document> eventoDocs = doc.getList("eventosLogs", Document.class);
	List<Evento> eventoLista = new ArrayList<>();
	for (Document eventoDoc : eventoDocs) {
		Evento e = new Evento();
		e.setId_evento(doc.getString("id_evento"));
		e.setTag(doc.getString("tag"));
		e.setMensaje(doc.getString("mensaje"));
		e.setTimestamp(doc.getString("timestamp"));
	}
	u.setPreferencias(p);
	u.setLogs_eventos(eventoLista);
	return u;
	
}
public Document UsuarioADoc(Usuario u) {
	Document preferenciasDoc = new Document();
	if(u.getPreferencias() != null) {
		preferenciasDoc
		.append("tema_oscuro", u.getPreferencias().isTema_oscuro())
		.append("idioma", u.getPreferencias().getIdioma())
		.append("notificaciones_push", u.getPreferencias().isNotificaciones_push())
		.append("limite_datos_moviles", u.getPreferencias().isLimite_datos_moviles());
		
	}
	List<Document> eventoLista = new ArrayList<>();
	if(u.getLogs_eventos() != null) {
		for (Evento e : u.getLogs_eventos()) {
			Document eventoDoc = new Document()
					.append("id_evento", e.getId_evento())
					.append("tag", e.getTag())
					.append("mensaje", e.getMensaje())
					.append("timestamp", e.getTimestamp());
			
			
			eventoLista.add(eventoDoc);
			
		}
	}
	Document usuarioDocs = new Document("id", u.getId())
			.append("username", u.getUsername())
			.append("email", u.getEmail())
			.append("plan_activo", u.getPlan_activo().name())
			.append("dispositivo", u.getDispositivo())
			.append("preferencias", preferenciasDoc)
			.append("eventosLogs", eventoLista);
	
	return usuarioDocs;
}
public void save(Usuario u) {
	coleccion.insertOne(UsuarioADoc(u));
}
public List<Usuario> read() {
	List<Usuario> lista = new ArrayList<>();
	FindIterable<Document> docs = coleccion.find();
	for (Document d : docs) {
		lista.add(fromUsuarioDocument2Java(d)); 
	}
	return lista;
}
public List<Usuario> ordenDescendenteYAscendente() {

	List<Usuario> usuariosordenados = new ArrayList<>();

	Document filtro = new Document();


	Document ordenarordenDescendente = new Document("plan_activo", -1);
	Document ordenarAscen = new Document ("email",1);


	FindIterable<Document> documentos = coleccion.find(filtro);


	documentos = documentos.sort(ordenarordenDescendente);
	documentos = documentos.sort(ordenarAscen);


	for (Document document : documentos) {

		Usuario u = fromUsuarioDocument2Java(document);

		usuariosordenados.add(u);

	}

	return usuariosordenados;

}
public void crearUsuario(Usuario u) {
	Document filtro = new Document("id", u.getId());
	Document existente = coleccion.find(filtro).first(); 
	if (existente == null) {
		coleccion.insertOne(UsuarioADoc(u)); 
	}
	else {
		throw new AppException("Error");
	}
}
public Usuario getUsuarioPorId(String id) {
	Usuario usuarioId = null;
	Document encuentraUsuario = new Document("id", id);
	Document documento = coleccion.find(encuentraUsuario).first(); 
	if (documento != null) {
		usuarioId = fromUsuarioDocument2Java(documento);
	}
	return usuarioId;
}
public void update(Usuario u) {
	Document filtro = new Document("idioma", u.getPreferencias().getIdioma());
	Document actualizacion = new Document("$set", UsuarioADoc(u));
	coleccion.findOneAndUpdate(filtro, actualizacion); 
}
public void actualizarAESP(String nuevoIdioma) {
	Document filtro = new Document("preferencias.idioma", "ES");
	Document actualizacion = new Document("$set", new Document("preferencias.idioma", nuevoIdioma));
	coleccion.updateMany(filtro, actualizacion); 
}
public void delete(String plan_activo) {
	Document filtro = new Document("plan_activo", "ANUAL");
	coleccion.findOneAndDelete(filtro); 
}
/*public void eliminarPlanActivo() {
	Document filtro = new Document("plan_activo", "MANUAL").append("$or",(new Document("preferencias.notificaciones_push", false).append("$and", new Document("dispositivo", "android 14"))));
	coleccion.deleteMany(filtro); 
}*/
public void agregarUsuario(String id, Evento nuevoEvento) {
	Document filtro = new Document("id", id);
	Document eventoDocs = new Document().append("id_evento", nuevoEvento.getId_evento()).append("tag", nuevoEvento.getTag()).append("mensaje", nuevoEvento.getMensaje()).append("timestamp", nuevoEvento.getTimestamp());
	Document actualizacion = new Document("$push", new Document("evento", eventoDocs));
	coleccion.updateOne(filtro, actualizacion); 
}
}
