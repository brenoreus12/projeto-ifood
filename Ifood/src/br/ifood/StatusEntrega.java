import java.sql.*;

public class StatusEntrega {
    private int id;
    private String nome;

    // Construtores, getters e setters
    public StatusEntrega(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public StatusEntrega() {}

    // Método para buscar status de entrega pelo ID
    public static String buscarPorId(int idStatus) {
        String status = "";
        String sql = "SELECT nome FROM Status_Entrega WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idStatus);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("nome");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar status de entrega: " + e.getMessage());
            // Retornar um valor default ou lançar uma exceção customizada
            status = "Erro ao buscar status";
        }

        return status;
    }

    @Override
    public String toString() {
        return nome;
    }
}
