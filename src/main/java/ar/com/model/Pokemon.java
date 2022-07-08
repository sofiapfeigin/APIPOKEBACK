package ar.com.model;

public class Pokemon {
	
	private int Id;
	private String foto;
	private String nombre;
	private String tipo;
	private double peso;
	private String habilidades;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
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
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public String getHabilidades() {
		return habilidades;
	}
	public void setHabilidades(String habilidades) {
		this.habilidades = habilidades;
	}
	
	
	


}
