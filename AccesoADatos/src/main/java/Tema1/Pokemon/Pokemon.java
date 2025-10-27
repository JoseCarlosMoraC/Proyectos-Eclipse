package Tema1.Pokemon;
import java.util.Arrays;
public class Pokemon {

	private int id;
	private String nombre, tipo, evoluciona_a;
	private String []habilidadPrincipal;
	private float altura_m, peso_kg;
	
	public Pokemon(int id, String nombre, String tipo, float altura_m,
			float peso_kg, String[] habilidadPrincipal, String evoluciona_a) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.habilidadPrincipal = habilidadPrincipal;
		this.evoluciona_a = evoluciona_a;
		this.altura_m = altura_m;
		this.peso_kg = peso_kg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String[] getHabilidadPrincipal() {
		return habilidadPrincipal;
	}
	public void setHabilidadPrincipal(String[] habilidadPrincipal) {
		this.habilidadPrincipal = habilidadPrincipal;
	}
	public String getEvoluciona_a() {
		return evoluciona_a;
	}
	public void setEvoluciona_a(String evoluciona_a) {
		this.evoluciona_a = evoluciona_a;
	}
	public float getAltura_m() {
		return altura_m;
	}
	public void setAltura_m(float altura_m) {
		this.altura_m = altura_m;
	}
	public float getPeso_kg() {
		return peso_kg;
	}
	public void setPeso_kg(float peso_kg) {
		this.peso_kg = peso_kg;
	}
	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", evoluciona_a=" + evoluciona_a
				+ ", habilidadPrincipal=" + Arrays.toString(habilidadPrincipal) + ", altura_m=" + altura_m
				+ ", peso_kg=" + peso_kg + "]";
	}
	
	
	
	
}