import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusEntregaDAO {

    // Exceção personalizada para erros de DAO
    public static class DAOException extends Exception {
        public DAOException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public List<StatusEntrega> listarTodos() throws DAOException {
        List<StatusEntrega> statusList = new ArrayList<>();
        String sql = "SELECT * FROM Status_Entrega";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                StatusEntrega status = new StatusEntrega();
                status.setId(rs.getInt("id"));
                status.setNome(rs.getString("nome"));
                statusList.add(status);
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao listar status de entrega", e);
        }
        return statusList;
    }

    public StatusEntrega buscarPorId(int id) throws DAOException {
        StatusEntrega status = null;
        String sql = "SELECT * FROM Status_Entrega WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    status = new StatusEntrega();
                    status.setId(rs.getInt("id"));
                    status.setNome(rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            throw new DAOException("Erro ao buscar status de entrega pelo ID", e);
        }
        return status;
    }

    // Método para realizar inserção de novos status
    public void inserirStatus(StatusEntrega status) throws DAOException {
        String sql = "INSERT INTO Status_Entrega (nome) VALUES (?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao inserir status de entrega", e);
        }
    }

    // Método para atualizar status
    public void atualizarStatus(StatusEntrega status) throws DAOException {
        String sql = "UPDATE Status_Entrega SET nome = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status.getNome());
            stmt.setInt(2, status.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro ao atualizar status de entrega", e);
        }
    }
}
