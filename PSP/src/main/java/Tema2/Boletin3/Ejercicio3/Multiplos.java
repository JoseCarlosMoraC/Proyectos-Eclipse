package Tema2.Boletin3.Ejercicio3;

import java.util.Objects;

public class Multiplos implements Runnable{
private int num;

	public int getNum() {
	return num;
}

public void setNum(int num) {
	this.num = num;
}

	@Override
public String toString() {
	return "Multiplos [num=" + num + "]";
}

	@Override
public int hashCode() {
	return Objects.hash(num);
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Multiplos other = (Multiplos) obj;
	return num == other.num;
}

	public Multiplos(int num) {
	super();
	this.num = num;
}
	public void hacerMultiplos() {
		for (int i = 0; i <= 10; i++) {
			System.out.println(num*i);
			}
		 try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void run() {
		this.hacerMultiplos();
		
	}

}
