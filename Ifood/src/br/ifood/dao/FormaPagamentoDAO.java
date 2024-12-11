package br.ifood.dao;

import br.ifood.models.FormaPagamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FormaPagamentoDAO {

    // Método para listar formas de pagamento
    public List<FormaPagamento> listarFormasPagamento() {
        List<FormaPagamento> formasPagamento = new ArrayList<>();
        String sql = "SELECT * FROM Forma_Pagamento";

        try (Connection conn = Conexao.conectar(); 
             Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                FormaPagamento formaPagamento = new FormaPagamento(
                    rs.getInt("id"),
                    rs.getString("descricao")
                );
                formasPagamento.add(formaPagamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return formasPagamento;
    }
}
