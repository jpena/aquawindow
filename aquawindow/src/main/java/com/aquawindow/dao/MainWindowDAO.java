package com.aquawindow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TreeItem;

import com.aquawindow.DataBase;
import com.aquawindow.models.ListMenuItem;

public class MainWindowDAO {
		
	private static MainWindowDAO dao = null;
	private Connection conn = null;	
	
	public MainWindowDAO() {
		conn = DataBase.getConnection();
	}

	public static MainWindowDAO getDAO() {
		if(dao == null){
			return new MainWindowDAO();
		}
		else return dao;
	}

	public List<TreeItem<ListMenuItem>>	getMainTreeMenu() throws Exception {
		
		PreparedStatement sp = null;
		ResultSet cursor = null;
		
		List<TreeItem<ListMenuItem>> lista = new ArrayList<TreeItem<ListMenuItem>>();
		
		try {
			sp = conn.prepareStatement("SELECT * FROM MENU");			
			cursor = sp.getResultSet();
			
			while(cursor.next()){				
				ListMenuItem menu = new ListMenuItem();
				menu.setTitulo(cursor.getString("TITULO"));
				menu.setDescripcion(cursor.getString("DESC"));
				menu.setClase(cursor.getString("CLASE"));				
				lista.add(new TreeItem<ListMenuItem>(menu));				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(sp != null) sp.close();
			if(cursor != null) cursor.close();
		}
		
		return lista;

	}
	
	public void insertarMenu(ListMenuItem nuevoMenu) throws Exception{
		
		PreparedStatement sp = null;
		
		try {
			sp = conn.prepareStatement("INSERT INTO MENU VALUES (?,?,?)");			
			sp.setString(1, nuevoMenu.getTitulo());
			sp.setString(2, nuevoMenu.getDescripcion());
			sp.setString(3, nuevoMenu.getClase());
			
			sp.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(sp != null) sp.close();

		}
		
	}
}
