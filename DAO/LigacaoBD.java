package com.classes.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.classes.model.User;
 
public class LigacaoBD {
    private static final String URL = "jdbc:mariadb://localhost:3307/archive";
    private static final String UTILIZADOR = "archive_user";
    private static final String PASSWORD = "user123";
   
    public static Connection obterLigacao() throws SQLException {
        return DriverManager.getConnection(URL, UTILIZADOR, PASSWORD);
    }
    public static void main(String[] args) {
        try (Connection conn = obterLigacao()) {
            System.out.println("Ligação OK: " + conn.getMetaData().getDatabaseProductName());
          
            
        } catch (SQLException e) {
            System.err.println("Falha: " + e.getMessage());
        }
    }
}
