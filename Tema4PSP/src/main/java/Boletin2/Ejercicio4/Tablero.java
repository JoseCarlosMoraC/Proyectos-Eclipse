package Boletin2.Ejercicio4;

import java.util.Arrays;

public class Tablero {
	private String[][] tablero;

	public Tablero() {
		super();
		this.tablero = new String[][] {{"Crucero", null, null,null},{null,null,"Entradas",null},{"Masaje",null,null,"1000€"}};
		
	}

	public String[][] getTablero() {
		return tablero;
	}

	public void setTablero(String[][] tablero) {
		this.tablero = tablero;
	}

	@Override
	public String toString() {
		return "Tablero [tablero=" + Arrays.toString(tablero) + "]";
	}


	}