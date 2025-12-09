	package Repositories;
	
	import java.time.LocalDate;
	import java.time.format.DateTimeFormatter;
	import java.util.ArrayList;
	import java.util.Collections;
	import java.util.Comparator;
	import java.util.Iterator;
	import java.util.List;
	
	import org.bson.Document;
	
	import com.mongodb.client.FindIterable;
	import com.mongodb.client.MongoCollection;
	import com.mongodb.client.MongoDatabase;
	
	import Models.Club;
	import Models.Equipo;
	import Models.Estadistica;
	import Models.Jugador;
	
	public class CoachXRepository {
	
		private static final String NOMBRE_COLECCION = "CDM_Gerena_fechas";
		private final MongoCollection<Document> coleccion;
		private List<Club> listaClub;
	
		private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
		public CoachXRepository(MongoDatabase db) {
			this.coleccion = db.getCollection(NOMBRE_COLECCION, Document.class);
			this.listaClub = this.read();
		}
		
	
		public List<Club> getListaClub() {
			return listaClub;
		}


		public void setListaClub(List<Club> listaClub) {
			this.listaClub = listaClub;
		}


		public void save(Club c) {
			List<Document> equiposDocs = new ArrayList<>();
			for (Equipo e : c.getEquipos()) {
				List<Document> jugadoresDocs = new ArrayList<>();
				for (Jugador j : e.getJugadores()) {
					Document statsDoc = new Document("id", j.getEstadisticas().getId())
							.append("partidos_jugados", j.getEstadisticas().getPartidosJugados())
							.append("goles", j.getEstadisticas().getGoles())
							.append("minutos", j.getEstadisticas().getMinutos())
							.append("tarjetas", j.getEstadisticas().getTarjetas())
							.append("rendimiento_index", j.getEstadisticas().getRendimientoIndex());
	
					Document jugadorDoc = new Document("id", j.getId()).append("nombre", j.getNombre())
							.append("dorsal", j.getDorsal())
							.append("fecha_nacimiento", j.getFechaNacimiento().format(formatter))
							.append("posicion", j.getPosicion()).append("estado_fisico", j.isEstadoFisico())
							.append("estadisticas", statsDoc);
	
					jugadoresDocs.add(jugadorDoc);
				}
	
				Document equipoDoc = new Document("id", e.getId()).append("nombre", e.getNombre())
						.append("categoria", e.getCategoria()).append("jugadores", jugadoresDocs);
	
				equiposDocs.add(equipoDoc);
			}
	
			Document clubDoc = new Document("id", c.getId()).append("nombre", c.getNombre()).append("escudo", c.getEscudo())
					.append("contacto", c.getContacto()).append("equipos", equiposDocs);
	
			coleccion.insertOne(clubDoc);
			listaClub.add(c);
		}
	
		public List<Club> read() {
			List<Club> clubs = new ArrayList<>();
			FindIterable<Document> docs = coleccion.find();
	
			for (Document doc : docs) {
				Club club = new Club();
				club.setId(doc.getInteger("id", 0));
				club.setNombre(doc.getString("nombre"));
				club.setEscudo(doc.getString("escudo"));
				club.setContacto(doc.getString("contacto"));
	
				List<Equipo> equipos = new ArrayList<>();
				List<Document> equiposDocs = (List<Document>) doc.get("equipos");
				if (equiposDocs != null) {
					for (Document eDoc : equiposDocs) {
						Equipo e = new Equipo(eDoc.getInteger("id", 0), eDoc.getString("nombre"),
								eDoc.getString("categoria"), new ArrayList<>(), null, null);
	
						List<Jugador> jugadores = new ArrayList<>();
						List<Document> jugadoresDocs = (List<Document>) eDoc.get("jugadores");
						if (jugadoresDocs != null) {
							for (Document jDoc : jugadoresDocs) {
								Jugador j = new Jugador();
								j.setId(jDoc.getInteger("id", 0));
								j.setNombre(jDoc.getString("nombre"));
								j.setDorsal(jDoc.getInteger("dorsal", 0));
	
								String fechaStr = jDoc.getString("fecha_nacimiento");
								if (fechaStr != null) {
									j.setFechaNacimiento(LocalDate.parse(fechaStr, formatter));
								}
	
								j.setPosicion(jDoc.getString("posicion"));
								j.setEstadoFisico(jDoc.getBoolean("estado_fisico", true));
	
								Document statsDoc = (Document) jDoc.get("estadisticas");
								if (statsDoc != null) {
									Estadistica stats = new Estadistica();
									stats.setId(statsDoc.getInteger("id", 0));
									stats.setPartidosJugados(statsDoc.getInteger("partidos_jugados", 0));
									stats.setGoles(statsDoc.getInteger("goles", 0));
									stats.setMinutos(statsDoc.getInteger("minutos", 0));
									stats.setTarjetas(statsDoc.getInteger("tarjetas", 0));
									Double rendimiento = statsDoc.getDouble("rendimiento_index");
									stats.setRendimientoIndex(rendimiento != null ? rendimiento : 0.0);
	
									j.setEstadisticas(stats);
								}
	
								jugadores.add(j);
							}
						}
	
						e.setJugadores(jugadores);
						equipos.add(e);
					}
				}
	
				club.setEquipos(equipos);
				clubs.add(club);
			}
	
			return clubs;
		}
	
		public Club crearClub(Club nuevoClub) {
			boolean encontrado = false;
			Iterator<Club> it = listaClub.iterator();
			while (it.hasNext() && !encontrado) {
				Club c = it.next();
				if (c.getId() == nuevoClub.getId()) {
					encontrado = true;
				}
			}
	
			if (!encontrado) {
				save(nuevoClub);
			}
	
			return nuevoClub;
		}
	
		public Club buscarClub(int id) {
			boolean encontrado = false;
			Club club = null;
	
			Iterator<Club> it = listaClub.iterator();
			while (it.hasNext() && !encontrado) {
				Club c = it.next();
				if (c.getId() == id) {
					encontrado = true;
					club = c;
				}
			}
	
			return club;
		}
	
		public void borrarClub(int id) {
			boolean encontrado = false;
			Club club = null;
	
			Iterator<Club> it = listaClub.iterator();
			while (it.hasNext() && !encontrado) {
				Club c = it.next();
				if (c.getId() == id) {
					encontrado = true;
					club = c;
					listaClub.remove(club);
					coleccion.deleteOne(new Document("id", id));
				}
			}
		}
	
		public List<Jugador> getJugadoresPorPosicion(String posicion) {
		    List<Jugador> jugadores = new ArrayList<>();

		   
		    FindIterable<Document> docs = coleccion.find(
		        new Document("equipos.jugadores.posicion", new Document("$eq", posicion))
		    );

		    for (Document doc : docs) {
		        List<Document> equiposDocs = (List<Document>) doc.get("equipos");
		        for (Document eDoc : equiposDocs) {
		            List<Document> jugadoresDocs = (List<Document>) eDoc.get("jugadores");
		            for (Document jDoc : jugadoresDocs) {
		             
		                if (jDoc.getString("posicion").equalsIgnoreCase(posicion)) {
		                    Jugador j = new Jugador();
		                    j.setId(jDoc.getInteger("id", 0));
		                    j.setNombre(jDoc.getString("nombre"));
		                    j.setDorsal(jDoc.getInteger("dorsal", 0));

		                    String fechaStr = jDoc.getString("fecha_nacimiento");
		                    if (fechaStr != null) {
		                        j.setFechaNacimiento(LocalDate.parse(fechaStr, formatter));
		                    }

		                    j.setPosicion(jDoc.getString("posicion"));
		                    j.setEstadoFisico(jDoc.getBoolean("estado_fisico", true));

		            
		                    Document statsDoc = (Document) jDoc.get("estadisticas");
		                    if (statsDoc != null) {
		                        Estadistica stats = new Estadistica();
		                        stats.setId(statsDoc.getInteger("id", 0));
		                        stats.setPartidosJugados(statsDoc.getInteger("partidos_jugados", 0));
		                        stats.setGoles(statsDoc.getInteger("goles", 0));
		                        stats.setMinutos(statsDoc.getInteger("minutos", 0));
		                        stats.setTarjetas(statsDoc.getInteger("tarjetas", 0));
		                        Double rendimiento = statsDoc.getDouble("rendimiento_index");
		                        stats.setRendimientoIndex(rendimiento != null ? rendimiento : 0.0);
		                        j.setEstadisticas(stats);
		                    }

		                    jugadores.add(j);
		                }
		            }
		        }
		    }

		    return jugadores;
		}

		
		
	
		public List<Jugador> ordenarJugadoresPorNombre() {
		    List<Jugador> jugadores = new ArrayList<>();

		    FindIterable<Document> docs = coleccion.find() 
		        .sort(new Document("equipos.jugadores.nombre", 1));  

		   
		    for (Document doc : docs) {
		        List<Document> equiposDocs = (List<Document>) doc.get("equipos");
		        
		        for (Document eDoc : equiposDocs) {
		            List<Document> jugadoresDocs = (List<Document>) eDoc.get("jugadores");
		            
		         
		            for (Document jDoc : jugadoresDocs) {
		                Jugador j = new Jugador();
		                j.setId(jDoc.getInteger("id", 0));
		                j.setNombre(jDoc.getString("nombre"));
		                j.setDorsal(jDoc.getInteger("dorsal", 0));

		                String fechaStr = jDoc.getString("fecha_nacimiento");
		                if (fechaStr != null) {
		                    j.setFechaNacimiento(LocalDate.parse(fechaStr, formatter));
		                }

		                j.setPosicion(jDoc.getString("posicion"));
		                j.setEstadoFisico(jDoc.getBoolean("estado_fisico", true));

		     
		                Document statsDoc = (Document) jDoc.get("estadisticas");
		                if (statsDoc != null) {
		                    Estadistica stats = new Estadistica();
		                    stats.setId(statsDoc.getInteger("id", 0));
		                    stats.setPartidosJugados(statsDoc.getInteger("partidos_jugados", 0));
		                    stats.setGoles(statsDoc.getInteger("goles", 0));
		                    stats.setMinutos(statsDoc.getInteger("minutos", 0));
		                    stats.setTarjetas(statsDoc.getInteger("tarjetas", 0));
		                    Double rendimiento = statsDoc.getDouble("rendimiento_index");
		                    stats.setRendimientoIndex(rendimiento != null ? rendimiento : 0.0);
		                    j.setEstadisticas(stats);
		                }

		                jugadores.add(j);
		            }
		        }
		    }

		    return jugadores;
		}

		
		
		public void actualizarClub(Club actualizado) {
		    boolean encontrado = false;
	
		    Iterator<Club> it = listaClub.iterator();
		    while (it.hasNext() && !encontrado) {
		        Club c = it.next();
		        if (c.getId() == actualizado.getId()) {
		            listaClub.set(listaClub.indexOf(c), actualizado);
		            encontrado = true;
	
		 
		            Document filtro = new Document("id", actualizado.getId());
	
		            List<Document> equiposDocs = new ArrayList<>();
		            for (Equipo e : actualizado.getEquipos()) {
		                List<Document> jugadoresDocs = new ArrayList<>();
		                for (Jugador j : e.getJugadores()) {
		                    Document statsDoc = new Document("id", j.getEstadisticas().getId())
		                            .append("partidos_jugados", j.getEstadisticas().getPartidosJugados())
		                            .append("goles", j.getEstadisticas().getGoles())
		                            .append("minutos", j.getEstadisticas().getMinutos())
		                            .append("tarjetas", j.getEstadisticas().getTarjetas())
		                            .append("rendimiento_index", j.getEstadisticas().getRendimientoIndex());
	
		                    Document jugadorDoc = new Document("id", j.getId())
		                            .append("nombre", j.getNombre())
		                            .append("dorsal", j.getDorsal())
		                            .append("fecha_nacimiento", j.getFechaNacimiento().format(formatter))
		                            .append("posicion", j.getPosicion())
		                            .append("estado_fisico", j.isEstadoFisico())
		                            .append("estadisticas", statsDoc);
	
		                    jugadoresDocs.add(jugadorDoc);
		                }
	
		                Document equipoDoc = new Document("id", e.getId())
		                        .append("nombre", e.getNombre())
		                        .append("categoria", e.getCategoria())
		                        .append("jugadores", jugadoresDocs);
	
		                equiposDocs.add(equipoDoc);
		            }
	
		            Document clubDoc = new Document("id", actualizado.getId())
		                    .append("nombre", actualizado.getNombre())
		                    .append("escudo", actualizado.getEscudo())
		                    .append("contacto", actualizado.getContacto())
		                    .append("equipos", equiposDocs);
	
		            coleccion.replaceOne(filtro, clubDoc);
		        }
		    }
		}
	
	}
