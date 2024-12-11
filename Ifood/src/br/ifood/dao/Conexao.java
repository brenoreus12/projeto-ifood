package br.ifood.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/ifood";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    static {
        try {
            // Registrar o driver JDBC (não é mais estritamente necessário em versões mais recentes do MySQL Connector/J, mas pode ajudar em alguns casos)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC do MySQL não encontrado", e);
        }
    }

    public static Connection conectar() {
        try {
            // Estabelecer a conexão com o banco de dados
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }
}
