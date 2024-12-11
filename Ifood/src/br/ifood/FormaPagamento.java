import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormaPagamento {
    private int id;
    private String descricao;

    // Construtores, getters e setters
    public FormaPagamento(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public FormaPagamento() {}

    // Método para listar formas de pagamento
    public static List<FormaPagamento> listarTodas() {
        List<FormaPagamento> formas = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConexao();
            String sql = "SELECT * FROM Forma_Pagamento";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                FormaPagamento forma = new FormaPagamento(
                    rs.getInt("id"),
                    rs.getString("descricao")
                );
                formas.add(forma);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar formas de pagamento: " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn);
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return formas;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
