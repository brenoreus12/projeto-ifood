package br.ifood.main;

import br.ifood.dao.RestauranteDAO;
import br.ifood.dao.ProdutoDAO;
import br.ifood.dao.AcompanhamentoDAO;
import br.ifood.models.Restaurante;
import br.ifood.models.Produto;
import br.ifood.models.Acompanhamento;
import br.ifood.models.Pedido;
import br.ifood.threads.AtualizarStatusEntregaThread;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        RestauranteDAO restauranteDAO = new RestauranteDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        AcompanhamentoDAO acompanhamentoDAO = new AcompanhamentoDAO();

        // Listar restaurantes
        List<Restaurante> restaurantes = restauranteDAO.listarTodos();
        if (restaurantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum restaurante disponível.");
            return;
        }

        String[] opcoesRestaurantes = restaurantes.stream()
                .map(Restaurante::getNome)
                .toArray(String[]::new);
        int escolhaRestaurante = JOptionPane.showOptionDialog(
                null,
                "Escolha um restaurante:",
                "Restaurantes",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoesRestaurantes,
                opcoesRestaurantes[0]
        );

        if (escolhaRestaurante == -1) {
            JOptionPane.showMessageDialog(null, "Operação cancelada.");
            return;
        }

        Restaurante restauranteEscolhido = restaurantes.get(escolhaRestaurante);

        // Listar produtos do restaurante escolhido
        List<Produto> produtos = produtoDAO.listarProdutosPorRestaurante(restauranteEscolhido.getId());
        if (produtos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum produto disponível para este restaurante.");
            return;
        }

        Pedido pedido = new Pedido(1, restauranteEscolhido, 0);

        while (true) {
            String[] opcoesProdutos = produtos.stream()
                    .map(Produto::getNome)
                    .toArray(String[]::new);
            int escolhaProduto = JOptionPane.showOptionDialog(
                    null,
                    "Escolha um produto para adicionar ao pedido:",
                    "Produtos",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoesProdutos,
                    opcoesProdutos[0]
            );

            if (escolhaProduto == -1) break;

            Produto produtoEscolhido = produtos.get(escolhaProduto);

            // Escolher acompanhamentos
            List<Acompanhamento> acompanhamentos = acompanhamentoDAO.buscarPorProduto(produtoEscolhido.getId());
            if (!acompanhamentos.isEmpty()) {
                String[] opcoesAcompanhamentos = acompanhamentos.stream()
                        .map(Acompanhamento::getNome)
                        .toArray(String[]::new);
                boolean adicionarMais = true;

                while (adicionarMais) {
                    int escolhaAcompanhamento = JOptionPane.showOptionDialog(
                            null,
                            "Escolha um acompanhamento para este produto:",
                            "Acompanhamentos",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            opcoesAcompanhamentos,
                            opcoesAcompanhamentos[0]
                    );

                    if (escolhaAcompanhamento == -1) break;

                    Acompanhamento acompanhamentoEscolhido = acompanhamentos.get(escolhaAcompanhamento);
                    produtoEscolhido.adicionarAcompanhamento(acompanhamentoEscolhido);

                    int continuar = JOptionPane.showConfirmDialog(
                            null,
                            "Deseja adicionar mais acompanhamentos?",
                            "Acompanhamentos",
                            JOptionPane.YES_NO_OPTION
                    );
                    adicionarMais = (continuar == JOptionPane.YES_OPTION);
                }
            }

            pedido.adicionarProduto(produtoEscolhido);

            int continuar = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja adicionar mais produtos?",
                    "Pedido",
                    JOptionPane.YES_NO_OPTION
            );

            if (continuar == JOptionPane.NO_OPTION) break;
        }

        // Exibir resumo do pedido
        JOptionPane.showMessageDialog(null, pedido.toString());

        // Lista de status e intervalo
        List<Integer> statusSequence = List.of(1, 2, 3);  // Exemplo de status (defina conforme sua lógica)
        int intervalo = 5000;  // Intervalo de 5 segundos

        // Atualizar status do pedido (thread simulando entregas)
        AtualizarStatusEntregaThread threadEntrega = new AtualizarStatusEntregaThread(pedido.getId(), statusSequence, intervalo);
        threadEntrega.start();

        JOptionPane.showMessageDialog(null, "Pedido finalizado! Acompanhe a entrega.");
    }
}
