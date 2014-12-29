package com.aquawindow.views.menu;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MenuPresenter implements Initializable {

	@FXML private String titulo;
	@FXML private String descripcion;
	@FXML private String clase;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

	}
	
	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getClase() {
		return clase;
	}

}
