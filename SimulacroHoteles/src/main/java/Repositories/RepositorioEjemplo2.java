package Repositories;

import java.util.ArrayList;

import java.util.List;

import javax.print.Doc;

import org.bson.Document;

import com.mongodb.client.FindIterable;

import com.mongodb.client.MongoCollection;

import com.mongodb.client.MongoDatabase;

public class RepositorioEjemplo2 {

	private static final String NOMBRE_COLECCION = "musicos";

	private final MongoCollection<Document> coleccion;

	public RepositorioEjemplo2(MongoDatabase db) {

		this.coleccion = db.getCollection(NOMBRE_COLECCION);

	}

	// Este mÃ©todo recibe un documento BSON con estructura de Estudiante y devuelve
	// objeto Java Estudiante

	private Musico fromMusicoDocument2Java(Document doc) {

		Musico e = new Musico();

		e.setId(doc.getInteger("id", 0));

		e.setNombreCompleto(doc.getString("nombreCompleto"));

		e.setCorreoelectronico(doc.getString("correo"));

		e.setFechanacimiento(doc.getString("fechaNacimiento"));

		// Crear documento perfil

		Document docPerfil = doc.get("perfil", Document.class);

		Perfil p = new Perfil();

		p.setId(docPerfil.getInteger("anuncioId", 0));

		p.setInstrumentos(docPerfil.getList("instrumentos", String.class));

		p.setExperiencia(docPerfil.getInteger("experiencia", 0));

		p.setGenero(Genero.valueOf(docPerfil.getString("genero")));

		p.setBiografia(docPerfil.getString(docPerfil.getString("biografia")));

		p.setBusca_banda(docPerfil.getBoolean("busca_banda", false));

		e.setPerfil(p);

		// Crear lista de documentos anuncio

		List<Document> anunciosDocs = doc.getList("anuncios", Document.class);

		// Crear lista de anuncios que sea aÃ±ade al Musico

		List<Anuncio> anuncioslista = new ArrayList<>();

		// Iteramos sobre cada Documento dentro de la lista

		for (Document anuncioDoc : anunciosDocs) {

			Anuncio a = new Anuncio();

			a.setId(anuncioDoc.getInteger("anuncioId", 0));

			a.setTitulo(anuncioDoc.getString("titulo"));

			a.setDescripcion(anuncioDoc.getString("descripcion"));

			a.setTipoanuncio(TipoAnuncio.valueOf(anuncioDoc.getString("tipo_anuncio")));

			// Usamos Double para el precio decimal

			Number precio = anuncioDoc.get("precio", Number.class);

			a.setPrecio(precio.doubleValue());

			a.setUrgente(anuncioDoc.getBoolean("urgente"));

			a.setFechacreacion(anuncioDoc.getString("fecha_creacion"));

			anuncioslista.add(a);

		}

		e.setAnuncios(anuncioslista);

		return e;

	}

	public Document musicoadoc(Musico m) {

		Document perfilDoc = new Document();

		if (m.getPerfil() != null) {

			perfilDoc

					.append("perfilId", m.getPerfil().getId())

					.append("instrumentos", m.getPerfil().getInstrumentos())

					.append("experiencia", m.getPerfil().getExperiencia())

					.append("genero", m.getPerfil().getGenero().name())

					.append("biografia", m.getPerfil().getBiografia())

					.append("busca_banda", m.getPerfil().isBusca_banda());

		}

		// 2. Construir Lista de Documentos Anuncio BSON

		List<Document> anunciosDocs = new ArrayList<>();

		if (m.getAnuncios() != null) {

			for (Anuncio a : m.getAnuncios()) {

				Document anuncioDoc = new Document()

						.append("anuncioId", a.getId())

						.append("titulo", a.getTitulo())

						.append("descripcion", a.getDescripcion())

						.append("tipo_anuncio", a.getTipoanuncio().name()) // Usar .name() para ENUM

						.append("precio", a.getPrecio())

						.append("urgente", a.isUrgente())

						.append("fecha_creacion", a.getFechacreacion());

				anunciosDocs.add(anuncioDoc);

			}

		}

		Document musicoDoc = new Document("id", m.getId())

				.append("nombreCompleto", m.getNombreCompleto())

				.append("correo", m.getCorreoelectronico())

				.append("fechaNacimiento", m.getFechanacimiento())

				.append("perfil", perfilDoc)

				.append("anuncios", anunciosDocs);

		return musicoDoc;

	}

	public void save(Musico e) {

		coleccion.insertOne(musicoadoc(e));

	}

	public List<Musico> read() {

		List<Musico> listamusicos = new ArrayList<>();

		FindIterable<Document> documentos = coleccion.find();

		for (Document document : documentos) {

			Musico e = fromMusicoDocument2Java(document);

			listamusicos.add(e);

		}

		return listamusicos;

	}

	public void delete(int id) {

		Document encuentramusico = new Document("id", id);

		coleccion.findOneAndDelete(encuentramusico);

	}

	public void update(Musico m) {

		Document musicoaactualizar = new Document("$set", musicoadoc(m));

		Document encuentramusico = new Document("id", m.getId());

		coleccion.findOneAndUpdate(encuentramusico, musicoaactualizar);

	}

	public Musico getMusicoPorId(int id) {

		Musico musicoid = null;

		Document encuentramusico = new Document("id", id);

		Document documento = coleccion.find(encuentramusico).first();

		musicoid = fromMusicoDocument2Java(documento);

		return musicoid;

	}

	public List<Musico> sortiddescendente() {

		List<Musico> musicosordenadosdescendentemente = new ArrayList<>();

		// creando este filtro, coje todos los documentos, sin filtros

		Document filtro = new Document();

		// creamos el filtro de ordenacion descendente

		Document ordenarordendescendente = new Document("id", -1);

		// aplica el filtro (quienes quieres buscar)

		FindIterable<Document> documentos = coleccion.find(filtro);

		// aplicamos el sort

		documentos = documentos.sort(ordenarordendescendente);

		// recorrer los documentos y guardarlos en una lista de musicos

		for (Document document : documentos) {

			Musico m = fromMusicoDocument2Java(document);

			musicosordenadosdescendentemente.add(m);

		}

		return musicosordenadosdescendentemente;

	}

}