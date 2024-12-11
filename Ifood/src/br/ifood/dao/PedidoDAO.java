package br.ifood.dao;

import br.ifood.models.*;

import java.sql.*;
import java.util.List;

public class PedidoDAO {

    public Pedido criarPedido(Restaurante restaurante, List<Produto> produtos) {
        String sqlPedido = "INSERT INTO Pedido (data, id_restaurante, valor, taxa_entrega) VALUES (NOW(), ?, ?, ?)";
        String sqlPedidoProduto = "INSERT INTO Pedido_Produto (id_pedido, id_produto, quantidade) VALUES (?, ?, ?)";
        String sqlPedidoProdutoAcompanhamento = "INSERT INTO Pedido_Produto_Acompanhamento (id_pedido_produto, id_acompanhamento) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar()) {
            conn.setAutoCommit(false);

            // Criar pedido
            PreparedStatement stmtPedido = conn.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            stmtPedido.setInt(1, restaurante.getId());
            stmtPedido.setDouble(2, calcularValorTotal(produtos));
            stmtPedido.setDouble(3, 5.00); // Taxa de entrega fixa
            stmtPedido.executeUpdate();

            ResultSet rsPedido = stmtPedido.getGeneratedKeys();
            if (rsPedido.next()) {
                int idPedido = rsPedido.getInt(1);

                // Adicionar produtos ao pedido
                for (Produto produto : produtos) {
                    PreparedStatement stmtPedidoProduto = conn.prepareStatement(sqlPedidoProduto, Statement.RETURN_GENERATED_KEYS);
                    stmtPedidoProduto.setInt(1, idPedido);
                    stmtPedidoProduto.setInt(2, produto.getId());
                    stmtPedidoProduto.setInt(3, 1); // Quantidade fixa como exemplo
                    stmtPedidoProduto.executeUpdate();

                    ResultSet rsPedidoProduto = stmtPedidoProduto.getGeneratedKeys();
                    if (rsPedidoProduto.next()) {
                        int idPedidoProduto = rsPedidoProduto.getInt(1);

                        // Adicionar acompanhamentos do produto ao pedido
                        for (Acompanhamento acompanhamento : produto.getAcompanhamentos()) {
                            PreparedStatement stmtPedidoProdutoAcompanhamento = conn.prepareStatement(sqlPedidoProdutoAcompanhamento);
                            stmtPedidoProdutoAcompanhamento.setInt(1, idPedidoProduto);
                            stmtPedidoProdutoAcompanhamento.setInt(2, acompanhamento.getId());
                            stmtPedidoProdutoAcompanhamento.executeUpdate();
                        }
                    }
                }

                conn.commit();
                return new Pedido(idPedido, restaurante, calcularValorTotal(produtos));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private double calcularValorTotal(List<Produto> produtos) {
        return produtos.stream().mapToDouble(p -> p.getPreco() + p.calcularValorAcompanhamentos()).sum();
    }
}
