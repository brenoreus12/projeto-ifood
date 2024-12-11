import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TelaInicial {
    public static String selecionarRestaurante() {
        String sql = "SELECT nome FROM Restaurante";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<String> restaurantes = new ArrayList<>();
            while (rs.next()) {
                restaurantes.add(rs.getString("nome"));
            }

            // Verifica se há restaurantes disponíveis
            if (restaurantes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum restaurante encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            // Exibe a listagem de restaurantes
            String[] opcoes = restaurantes.toArray(new String[0]);
            String escolhido = (String) JOptionPane.showInputDialog(
                null,
                "Bem-vindo ao Ifood, por favor escolha um dos restaurantes:",
                "Escolher Restaurante",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            // Se o usuário cancelar a seleção, retorna null
            if (escolhido == null) {
                return null;
            }

            return escolhido;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar restaurantes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
