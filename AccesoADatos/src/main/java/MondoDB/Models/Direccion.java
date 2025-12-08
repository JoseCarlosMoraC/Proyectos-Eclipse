package MondoDB.Models;

import java.util.Objects;

public class Direccion {
private String city;
private int zip;
private String street;
private int number;

public Direccion(String city, int zip, String street, int number) {
	super();
	this.city = city;
	this.zip = zip;
	this.street = street;
	this.number = number;
}

public String getCity() {
	return city;
}

public void setCity(String city) {
	this.city = city;
}

public int getZip() {
	return zip;
}

public void setZip(int zip) {
	this.zip = zip;
}

public String getStreet() {
	return street;
}

public void setStreet(String street) {
	this.street = street;
}

public int getNumber() {
	return number;
}

public void setNumber(int number) {
	this.number = number;
}

@Override
public int hashCode() {
	return Objects.hash(city, number, street, zip);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Direccion other = (Direccion) obj;
	return Objects.equals(city, other.city) && number == other.number && Objects.equals(street, other.street)
			&& zip == other.zip;
}

@Override
public String toString() {
	return "Direccion [city=" + city + ", zip=" + zip + ", street=" + street + ", number=" + number + "]";
}


}
