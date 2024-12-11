package br.ifood.dao;

import br.ifood.models.Acompanhamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AcompanhamentoDAO {

    // Buscar acompanhamentos por produto
    public List<Acompanhamento> buscarPorProduto(int idProduto) {
        List<Acompanhamento> acompanhamentos = new ArrayList<>();
        String sql = "SELECT a.id, a.nome, a.valor FROM Acompanhamento a " +
                     "JOIN Produto_Acompanhamento pa ON a.id = pa.id_acompanhamento " +
                     "WHERE pa.id_produto = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Acompanhamento acompanhamento = new Acompanhamento(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("valor")
                );
                acompanhamentos.add(acompanhamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return acompanhamentos;
    }
}
