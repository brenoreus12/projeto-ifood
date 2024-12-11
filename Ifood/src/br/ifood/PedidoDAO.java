import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    
    // Método auxiliar para mapear ResultSet para Pedido
    private Pedido mapearPedido(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getInt("id"));
        pedido.setData(rs.getTimestamp("data").toLocalDateTime());
        pedido.setIdRestaurante(rs.getInt("id_restaurante"));
        pedido.setValor(rs.getBigDecimal("valor"));
        pedido.setTaxaEntrega(rs.getBigDecimal("taxa_entrega"));
        pedido.setIdStatusEntrega(rs.getInt("id_status_entrega"));
        pedido.setIdFormaPagamento(rs.getInt("id_forma_pagamento"));
        pedido.setObservacoes(rs.getString("observacoes"));
        pedido.setTroco(rs.getBigDecimal("troco"));
        pedido.setIdEndereco(rs.getInt("id_endereco"));
        return pedido;
    }

    public int criar(Pedido pedido) {
        String sql = "INSERT INTO Pedido (data, id_restaurante, valor, taxa_entrega, id_forma_pagamento, observacoes, troco, id_endereco) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1, Timestamp.valueOf(pedido.getData()));
            stmt.setInt(2, pedido.getIdRestaurante());
            stmt.setBigDecimal(3, pedido.getValor());
            stmt.setBigDecimal(4, pedido.getTaxaEntrega());
            stmt.setInt(5, pedido.getIdFormaPagamento());
            stmt.setString(6, pedido.getObservacoes());
            stmt.setBigDecimal(7, pedido.getTroco());
            stmt.setInt(8, pedido.getIdEndereco());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void atualizarStatusPedido(int idPedido, int novoStatus) {
        String sql = "UPDATE Pedido SET id_status_entrega = ? WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novoStatus);
            stmt.setInt(2, idPedido);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                pedidos.add(mapearPedido(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public void excluirPedido(int idPedido) {
        String sql = "DELETE FROM Pedido WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pedido> listarPedidosPorCliente(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido WHERE id_cliente = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(mapearPedido(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }

    public List<Pedido> listarPedidosPendentes() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido WHERE id_status_entrega = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, 1); // Assuming '1' means "Pendente" status
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(mapearPedido(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos;
    }
}
