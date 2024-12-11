import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class TelaStatusEntrega {
    public static void exibirStatusEntrega(int idPedido) {
        String sql = "SELECT s.nome, h.datetime FROM Historico_Entrega h " +
                     "INNER JOIN Status_Entrega s ON h.id_status_entrega = s.id " +
                     "WHERE h.id_pedido = ? ORDER BY h.datetime DESC";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            try (ResultSet rs = stmt.executeQuery()) {
                StringBuilder status = new StringBuilder("Status da Entrega:\n");

                // Definindo um formato de data amigável
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                boolean encontrouStatus = false;
                while (rs.next()) {
                    String nomeStatus = rs.getString("nome");
                    Timestamp timestamp = rs.getTimestamp("datetime");

                    // Verifica se há um resultado e formata a data
                    if (timestamp != null) {
                        String dataFormatada = sdf.format(timestamp);
                        status.append(nomeStatus).append(" - ").append(dataFormatada).append("\n");
                        encontrouStatus = true;
                    }
                }

                if (!encontrouStatus) {
                    status.append("Nenhuma atualização de status encontrada.");
                }

                JOptionPane.showMessageDialog(null, status.toString(), "Status da Entrega", JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar status da entrega: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
