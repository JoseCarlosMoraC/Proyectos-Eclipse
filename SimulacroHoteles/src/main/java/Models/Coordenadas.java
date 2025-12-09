package Models;

public class Coordenadas {
private double lat;
private double lon;

public Coordenadas(double lat, double lon) {
	super();
	this.lat = lat;
	this.lon = lon;
}

public Coordenadas() {
	// TODO Auto-generated constructor stub
}

public double getLat() {
	return lat;
}

public void setLat(double lat) {
	this.lat = lat;
}

public double getLon() {
	return lon;
}

public void setLon(double lon) {
	this.lon = lon;
}


}
