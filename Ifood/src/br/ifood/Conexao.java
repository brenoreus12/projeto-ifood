import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    // URL de conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/ifood";
    // Credenciais de acesso ao banco
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    // Método para conectar ao banco de dados
    public static Connection conectar() throws SQLException {
        try {
            // Estabelecendo a conexão com o banco de dados
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            // Caso haja erro na conexão, lança uma exceção com a mensagem detalhada
            throw new SQLException("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
