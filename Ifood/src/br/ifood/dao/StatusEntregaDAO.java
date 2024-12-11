package br.ifood.dao;

import java.sql.*;

public class StatusEntregaDAO {

    public void atualizarStatus(int idPedido, int idStatusEntrega) {
        String sql = "UPDATE Pedido SET id_status_entrega = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idStatusEntrega);
            stmt.setInt(2, idPedido);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
