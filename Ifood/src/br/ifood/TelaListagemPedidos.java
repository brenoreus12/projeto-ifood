public class TelaListagemPedidos {
    public static void listarPedidos() {
        StringBuilder pedidos = new StringBuilder("Pedidos Realizados:\n");
        boolean encontrouPedidos = false;

        String sql = "SELECT p.id, p.data, p.valor, s.nome AS status FROM Pedido p " +
                     "INNER JOIN Status_Entrega s ON p.id_status_entrega = s.id";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int idPedido = rs.getInt("id");
                String data = rs.getString("data");
                double valor = rs.getDouble("valor");
                String status = rs.getString("status");

                pedidos.append("Pedido ID: ").append(idPedido)
                       .append(" | Data: ").append(data)
                       .append(" | Valor: R$ ").append(valor)
                       .append(" | Status: ").append(status).append("\n");

                encontrouPedidos = true;
            }

            if (!encontrouPedidos) {
                pedidos.append("Nenhum pedido encontrado.");
            }

            JOptionPane.showMessageDialog(null, pedidos.toString(), "Listagem de Pedidos", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar pedidos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
