package MondoDB.Models;

import java.util.Objects;

public class Puntuacion {
private double score;
private String type;

public Puntuacion(double score, String type) {
	super();
	this.score = score;
	this.type = type;
}

public double getScore() {
	return score;
}

public void setScore(double score) {
	this.score = score;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

@Override
public int hashCode() {
	return Objects.hash(score, type);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Puntuacion other = (Puntuacion) obj;
	return Double.doubleToLongBits(score) == Double.doubleToLongBits(other.score) && Objects.equals(type, other.type);
}

@Override
public String toString() {
	return "Puntuacion [score=" + score + ", type=" + type + "]";
}

}
