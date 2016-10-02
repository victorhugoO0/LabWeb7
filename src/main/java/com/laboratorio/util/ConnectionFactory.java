package com.laboratorio.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/bd_sca";
	private static final String USER = "postgres";
	private static final String PASSWORD = "master12";

	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			System.out.println("-----------------------------------------");
			System.out.println("Erro na conexão");
			e.printStackTrace();
		}
		return null;
	}
}
