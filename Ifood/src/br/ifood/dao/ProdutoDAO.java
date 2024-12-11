package br.ifood.dao;

import br.ifood.models.Produto;
import br.ifood.models.Acompanhamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // Método para listar todos os produtos de um restaurante
    public List<Produto> listarProdutosPorRestaurante(int idRestaurante) {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto WHERE id_restaurante = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRestaurante);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Corrigido: agora passando 4 parâmetros para o construtor do Produto
                    Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getString("descricao")  // Adicionando a descrição
                    );
                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    // Método para listar acompanhamentos de um produto
    public List<Acompanhamento> listarAcompanhamentosPorProduto(int idProduto) {
        List<Acompanhamento> acompanhamentos = new ArrayList<>();
        String sql = """
            SELECT a.id, a.nome, a.valor 
            FROM Acompanhamento a
            JOIN Produto_Acompanhamento pa ON a.id = pa.id_acompanhamento
            WHERE pa.id_produto = ?
        """; 

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Corrigido: agora passando 3 parâmetros para o construtor do Acompanhamento
                    Acompanhamento acompanhamento = new Acompanhamento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("valor")
                    );
                    acompanhamentos.add(acompanhamento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return acompanhamentos;
    }
}
