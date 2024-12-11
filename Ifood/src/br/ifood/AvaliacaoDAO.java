import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AvaliacaoDAO {
    public void salvar(Avaliacao avaliacao) {
        String sql = "INSERT INTO Avaliacao (nota, id_pedido, id_restaurante) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar(); // Certifique-se de que a classe Conexao está configurada.
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Preenche os parâmetros da query.
            stmt.setInt(1, avaliacao.getNota());
            stmt.setInt(2, avaliacao.getIdPedido());
            stmt.setInt(3, avaliacao.getIdRestaurante());

            // Executa a query.
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Lida com possíveis erros ao executar a query.
            System.err.println("Erro ao salvar avaliação: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
