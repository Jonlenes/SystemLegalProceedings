package br.com.webservice.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectPostgres {
    private static final String url = "jdbc:postgresql://localhost/SystemProcessos";
    private static final String userName = "Jonlenes";
    private static final String password = "root";
    
    public static Connection getConnection() throws SQLException {
    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
        return DriverManager.getConnection(url, userName, password);
    }
}
