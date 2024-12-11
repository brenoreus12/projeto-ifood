import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestauranteDAO {

    // Exceção personalizada para erros de DAO
    public static class DAOException extends Exception {
        public DAOException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public List<Restaurante> listarTodos() throws DAOException {
        List<Restaurante> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM Restaurante";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Restaurante restaurante = new Restaurante();
                restaurante.setId(rs.getInt("id"));
                restaurante.setNome(rs.getString("nome"));
                restaurante.setEndereco(rs.getString("endereco"));
                restaurante.setTelefone(rs.getString("telefone"));
                restaurante.setCategorias(rs.getString("categorias"));
                restaurante.setHorarioFuncionamento(rs.getString("horario_funcionamento"));
                restaurante.setHistoricoRetirada(rs.getString("historico_retirada"));
                restaurantes.add(restaurante);
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao listar restaurantes", e);
        }
        return restaurantes;
    }

    public Restaurante buscarPorId(int id) throws DAOException {
        Restaurante restaurante = null;
        String sql = "SELECT * FROM Restaurante WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    restaurante = new Restaurante();
                    restaurante.setId(rs.getInt("id"));
                    restaurante.setNome(rs.getString("nome"));
                    restaurante.setEndereco(rs.getString("endereco"));
                    restaurante.setTelefone(rs.getString("telefone"));
                    restaurante.setCategorias(rs.getString("categorias"));
                    restaurante.setHorarioFuncionamento(rs.getString("horario_funcionamento"));
                    restaurante.setHistoricoRetirada(rs.getString("historico_retirada"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar restaurante pelo ID", e);
        }
        return restaurante;
    }

    public void atualizarRestaurante(Restaurante restaurante) throws DAOException {
        String sql = "UPDATE Restaurante SET nome = ?, endereco = ?, telefone = ?, categorias = ?, horario_funcionamento = ?, historico_retirada = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, restaurante.getNome());
            stmt.setString(2, restaurante.getEndereco());
            stmt.setString(3, restaurante.getTelefone());
            stmt.setString(4, restaurante.getCategorias());
            stmt.setString(5, restaurante.getHorarioFuncionamento());
            stmt.setString(6, restaurante.getHistoricoRetirada());
            stmt.setInt(7, restaurante.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar restaurante", e);
        }
    }

    public List<Restaurante> buscarRestaurantePorCategoria(String categoria) throws DAOException {
        List<Restaurante> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM Restaurante WHERE categorias LIKE ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + categoria + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Restaurante restaurante = new Restaurante();
                    restaurante.setId(rs.getInt("id"));
                    restaurante.setNome(rs.getString("nome"));
                    restaurante.setEndereco(rs.getString("endereco"));
                    restaurante.setTelefone(rs.getString("telefone"));
                    restaurante.setCategorias(rs.getString("categorias"));
                    restaurante.setHorarioFuncionamento(rs.getString("horario_funcionamento"));
                    restaurante.setHistoricoRetirada(rs.getString("historico_retirada"));
                    restaurantes.add(restaurante);
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar restaurantes por categoria", e);
        }
        return restaurantes;
    }
}
