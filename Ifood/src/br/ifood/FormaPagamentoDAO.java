import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormaPagamentoDAO {
    public List<FormaPagamento> listarTodas() {
        List<FormaPagamento> formasPagamento = new ArrayList<>();
        String sql = "SELECT * FROM Forma_Pagamento";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FormaPagamento formaPagamento = new FormaPagamento();
                formaPagamento.setId(rs.getInt("id"));
                formaPagamento.setDescricao(rs.getString("descricao"));
                formasPagamento.add(formaPagamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return formasPagamento;
    }
}
