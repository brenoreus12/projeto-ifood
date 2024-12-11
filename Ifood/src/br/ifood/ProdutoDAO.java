import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // Método auxiliar para preencher um produto a partir do ResultSet
    private Produto mapearProduto(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getInt("id"));
        produto.setNome(rs.getString("nome"));
        produto.setDescricao(rs.getString("descricao"));
        produto.setPreco(rs.getBigDecimal("preco"));
        produto.setIdCategoria(rs.getInt("id_categoria"));
        produto.setIdRestaurante(rs.getInt("id_restaurante"));
        return produto;
    }

    public List<Produto> listarPorRestaurante(int idRestaurante) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto WHERE id_restaurante = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRestaurante);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    produtos.add(mapearProduto(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public Produto buscarPorId(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM Produto WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    produto = mapearProduto(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }

    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE Produto SET nome = ?, descricao = ?, preco = ?, id_categoria = ?, id_restaurante = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setBigDecimal(3, produto.getPreco());
            stmt.setInt(4, produto.getIdCategoria());
            stmt.setInt(5, produto.getIdRestaurante());
            stmt.setInt(6, produto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirProduto(int idProduto) {
        String sql = "DELETE FROM Produto WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> buscarProdutoPorNome(String nome) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto WHERE nome LIKE ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + nome + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    produtos.add(mapearProduto(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}
