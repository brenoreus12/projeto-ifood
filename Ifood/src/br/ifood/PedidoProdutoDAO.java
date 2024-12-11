import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoProdutoDAO {

    // Adiciona um produto ao pedido, com verificação de duplicidade
    public void adicionarProdutoAoPedido(int idPedido, PedidoProduto pedidoProduto) throws SQLException {
        // Verifica se o produto já foi adicionado ao pedido
        if (produtoJaAdicionado(idPedido, pedidoProduto.getIdProduto())) {
            throw new SQLException("Produto já adicionado ao pedido.");
        }

        String sql = "INSERT INTO Pedido_Produto (id_pedido, id_produto, quantidade) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            stmt.setInt(2, pedidoProduto.getIdProduto());
            stmt.setInt(3, pedidoProduto.getQuantidade());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Falha ao adicionar produto ao pedido.");
            }
        }
    }

    // Verifica se o produto já foi adicionado ao pedido
    private boolean produtoJaAdicionado(int idPedido, int idProduto) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Pedido_Produto WHERE id_pedido = ? AND id_produto = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            stmt.setInt(2, idProduto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Lista todos os produtos de um pedido
    public List<PedidoProduto> listarProdutosDoPedido(int idPedido) {
        List<PedidoProduto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Pedido_Produto WHERE id_pedido = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idPedido);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    PedidoProduto pedidoProduto = new PedidoProduto();
                    pedidoProduto.setId(rs.getInt("id"));
                    pedidoProduto.setIdPedido(rs.getInt("id_pedido"));
                    pedidoProduto.setIdProduto(rs.getInt("id_produto"));
                    pedidoProduto.setQuantidade(rs.getInt("quantidade"));
                    produtos.add(pedidoProduto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}
