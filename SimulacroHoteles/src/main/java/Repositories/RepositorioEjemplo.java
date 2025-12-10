
package Repositories;



import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;

import Models.Coordenadas;
import Models.Habitacion;
import Models.Hotel;
import Models.Tipo;
import Models.Ubicacion;



public class RepositorioEjemplo {

	private static final String NOMBRE_COLECCION = "hoteles";

	private final MongoCollection<Document> coleccion;

	



	public RepositorioEjemplo(MongoDatabase db) {

	this.coleccion = db.getCollection(NOMBRE_COLECCION);

	}

	

	private Hotel fromHotelDocument2Java(Document doc) {

	Hotel h = new Hotel();

	h.setIdHotel(doc.getString("idHotel"));

	h.setNombre(doc.getString("nombre"));

	h.setEstrellas(doc.getInteger("estrellas",0));

	h.setAdmiteMascotas(doc.getBoolean("admiteMascotas",false));

	h.setFechaApertura(doc.getString("fechaApertura"));

	

	Document docUbicacion= doc.get("ubicacion", Document.class);

	Ubicacion u= new Ubicacion();

	u.setCalle(docUbicacion.getString("calle"));

	u.setNumero(docUbicacion.getInteger("numero",0));

	u.setCodigoPostal(docUbicacion.getString("codigoPostal"));

	

	Document docCoordenadas= doc.get("coordenadas", Document.class);

	Coordenadas c= new Coordenadas();

	c.setLat(docUbicacion.getInteger("lat",0));

	c.setLon(docUbicacion.getInteger("lon",0));

	u.setCoordenadas(c);

	

	//Crear lista de documentos habitacion

	List<Document> habitacionesDocs = doc.getList("habitaciones", Document.class);

	//Crear lista de anuncios que sea añade al hotel

	List<Habitacion> habitacioneslista= new ArrayList<>();

	// Iteramos sobre cada Documento dentro de la lista

	for (Document habitacionDoc : habitacionesDocs) {

	Habitacion a = new Habitacion();

	a.setTipo(Tipo.valueOf(habitacionDoc.getString("tipo")));

	a.setCapacidad(habitacionDoc.getInteger("capacidad",0));

	a.setDisponible(habitacionDoc.getBoolean("disponible"));

	// Usamos Double para el precio decimal

	Number precio= habitacionDoc.get("precio", Number.class);

	a.setPrecio(precio.doubleValue()); 

	

	

	habitacioneslista.add(a);

	}

	h.setHabitaciones(habitacioneslista);

	

	h.setUbicacion(u);

	

	

	return h;

	



	}

	public Document hoteladoc(Hotel h) {

	Document coordenadasDoc = new Document();

	if(h.getUbicacion().getCoordenadas() != null) {

	coordenadasDoc

 .append("lat", h.getUbicacion().getCoordenadas().getLat())

 .append("lon", h.getUbicacion().getCoordenadas().getLon());

	}

	Document ubicacionDoc = new Document();

	if(h.getUbicacion() != null) {

	ubicacionDoc

	.append("calle", h.getUbicacion().getCalle())

	.append("numero", h.getUbicacion().getNumero()) 

	.append("experiencia", h.getUbicacion().getCodigoPostal())

	.append("coordenadas", coordenadasDoc);

	

	}

	

	List<Document> habitacionesDocs = new ArrayList<>();

	if( h.getHabitaciones() !=null) {

	for (Habitacion a :h.getHabitaciones()) {

	Document anuncioDoc = new Document()

	.append("tipo", a.getTipo().name()) // Usar .name() para ENUM

	.append("precio", a.getPrecio())

	.append("capacidad", a.getCapacidad())

	.append("disponible", a.isDisponible())

	.append("precio", a.getPrecio());

	

	habitacionesDocs.add(anuncioDoc);

	}

	

	

	}

	Document hotelDoc = new Document("idHotel", h.getIdHotel())

	.append("nombre", h.getNombre())

	.append("estrellas", h.getEstrellas())

	.append("admiteMascotas", h.isAdmiteMascotas())	

	.append("fechaApertura", h.getFechaApertura()) 

	.append("ubicacion", ubicacionDoc)

	.append("habitaciones", habitacionesDocs); 

	

	return hotelDoc;

	}

	

	public void save (Hotel h) {

	

	coleccion.insertOne(hoteladoc(h));

	}

	public long añadehabitacionporid (String id) {

	// 1. DEFINIR EL FILTRO (Quién queremos actualizar)

	// Buscamos el hotel por su ID. Asumimos que el campo clave se llama "id".

	Document filtro = new Document("idHotel", id);

	// 2. DEFINIR EL NUEVO OBJETO (Qué vamos a añadir)

	// Creamos el documento que representa la nueva habitación Penthouse.

	Document nuevaHabitacion = new Document("tipo", "Penthouse")

	.append("precio", 500.00)

	.append("capacidad", 4)

	.append("disponible", true);

	

	// 3. DEFINIR LA ACTUALIZACIÓN (Cómo lo vamos a añadir)

	// Usamos el operador $push para añadir 'nuevaHabitacion' al array 'habitaciones'.

	Document actualizacion = new Document("$push", 

	new Document("habitaciones", nuevaHabitacion)

	);

	

	// 4. EJECUTAR LA OPERACIÓN

	// Llamamos a updateOne() con el filtro y la actualización.

	UpdateResult resultado = coleccion.updateOne(filtro, actualizacion);

	

	return resultado.getModifiedCount();

	}

	

	public long actualizagranviaa28013 () {

	

	//Buscar documentos calle avenida sierra

	Document filtro = new Document("ubicacion.calle", "Avenida Sierra");

	//indica que valor tiene que cambiarse

	Document codigopostalaactualizar= new Document ("ubicacion.codigoPostal", "28013");

	//documento que actualiza el valor dado (codigopostal)

	Document actualizacion= new Document("$set", codigopostalaactualizar);

	//actualiza los hoteles de avenida sierra al codigopostal

	UpdateResult resultado = coleccion.updateMany(filtro, actualizacion);

	

	return resultado.getModifiedCount();



	}

	

	

	

	public List<Hotel> read(){

	List<Hotel> listahoteles= new ArrayList<>();

	FindIterable<Document> documentos= coleccion.find();

	for (Document document : documentos) {

	Hotel h= fromHotelDocument2Java(document);

	listahoteles.add(h);

	}

	return listahoteles;

	

	}

	

	public long actualizaprecioh101() {

	

	//cojo el hotel h101

	Document filtro = new Document("idHotel", "h101"); 

	//

	Document filtroindividual= new Document ("habitaciones.tipo", Tipo.INDIVIDUAL.toString());

	

	List<Document> listacondicionesand= new ArrayList<>();

	listacondicionesand.add(filtroindividual);

	listacondicionesand.add(filtro);

	Document filtrosand = new Document("$and", listacondicionesand);

	

	Document filtroprecionoventa= new Document("habitaciones.$.precio", 90.00 );

	Document filtroprecionoventaactualizar= new Document("$set", filtroprecionoventa );

	

	UpdateResult resultado=coleccion.updateOne(filtrosand, filtroprecionoventaactualizar);

	

	return resultado.getModifiedCount();

	



	

	}

	

	public void delete(int id) {



	Document encuentrahotel= new Document ("id", id);

	coleccion.findOneAndDelete(encuentrahotel);

	}

	public void update(Hotel h) {

	Document hotelactualizar = new Document ("$set",hoteladoc(h));

	Document encuentrahotel= new Document ("id", h.getIdHotel());

	coleccion.findOneAndUpdate(hotelactualizar, encuentrahotel);



}

	public Hotel getHotelPorId(String id){	

	Hotel hotelbuscadoporid= null;

	Document encuentrahotel= new Document ("idHotel", id);

	Document documento= coleccion.find(encuentrahotel).first();

	hotelbuscadoporid= fromHotelDocument2Java(documento);

	return hotelbuscadoporid;

	

	}

	public List<Hotel> gethotelesenmadrid5estrellasoqueadmitanmascotas(){	

	List<Document> condicionesOR = new ArrayList<>();

	

	// Condición OR 1: 5 Estrellas

	condicionesOR.add(new Document("calificacionEstrellas", 5));

	

	// Condición OR 2: Admite Mascotas

	condicionesOR.add(new Document("admiteMascotas", true));

	

	// Creamos el Documento que representa el OR completo

	Document filtroOR = new Document("$or", condicionesOR); // { "$or": [ { ... }, { ... } ] }



	// 2. Construir el filtro principal (AND): Madrid Y (el filtro OR anterior)

	

	// Condición AND 1: Estar en Madrid (usando $regex para un rango de códigos postales)

	Document filtroMadrid = new Document("ubicacion.codigoPostal", "35660"); 

	

	

	// El filtro final combina las dos condiciones (Madrid Y el OR)

	// Usamos $and para unir el filtro de Madrid con el filtro OR.

	List<Document> condicionesAND = new ArrayList<>();

	condicionesAND.add(filtroMadrid);

	condicionesAND.add(filtroOR);

	

	// El filtro final que se pasa a find()

	Document filtroFinal = new Document("$and", condicionesAND);



	// 3. Ejecutar la consulta

	List<Hotel> hotelesEncontrados = new ArrayList<>();

	

	// coleccion.find() acepta el Document que contiene la lógica AND/OR

	for (Document documento : coleccion.find(filtroFinal)) { 

	Hotel hotel = fromHotelDocument2Java(documento);

	hotelesEncontrados.add(hotel);

	}

	

	return hotelesEncontrados;

	

	}

	public List<Hotel> gethotelessuitejuniordisponible(){	

	

	// 1. Documento interno: Define las condiciones que deben CUMPLIRSE JUNTAS.

	Document condicionesConjuntas = new Document("tipo", Tipo.SUITEJUNIOR.toString())

	.append("disponible", true);

	

	// 2. Filtro $elemMatch: Aplica esas condiciones conjuntas al array 'habitaciones'.

	Document filtrosuitejunior = new Document("habitaciones", new Document("$elemMatch", condicionesConjuntas));

	List<Hotel> hotelesencontrados= new ArrayList<>();

	for (Document documento : coleccion.find(filtrosuitejunior)) { 

	Hotel hotel = fromHotelDocument2Java(documento);

	hotelesencontrados.add(hotel);

	}

	

	return hotelesencontrados;

	

	}

	/*public long eliminaarrayhabitacionesmayora300granhotelcentral() {

	Document filtronombre= new Document ("nombre", "Grand Hotel Central");

	Document filtro

	

	

	}*/

	

}