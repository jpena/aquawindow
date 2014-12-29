package com.aquawindow.models;



public class ListMenuItem {
	
	private String titulo;
	private String descripcion;
	private String clase;

	public ListMenuItem() {
		// TODO Auto-generated constructor stub
	}	

	public ListMenuItem(String titulo, String clase) {
		this.titulo = titulo;
		this.clase = clase;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	@Override
	public String toString() {
		return getTitulo();
	}
	
	
}
