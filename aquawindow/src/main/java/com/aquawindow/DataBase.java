package com.aquawindow;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBase {
	
	private static Connection conn = null;

	public DataBase() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection(){
		
	
		try {
			if(conn == null){
				conn = DriverManager.getConnection("jdbc:derby:aquabase;create=true;bootPassword=aquabase");
				System.out.println("Connected to "+conn.toString()+".");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return conn;
	}

}
