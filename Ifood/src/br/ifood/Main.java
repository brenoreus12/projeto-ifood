import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> produtosAdicionados = new ArrayList<>();

    public static void main(String[] args) {
        // Tela de Boas-vindas
        JFrame telaBoasVindas = criarTelaComComboBox(listarRestaurantes(), "Bem-vindo ao Ifood", 
                "Bem-vindo ao Ifood, por favor escolha um dos restaurantes:", "Escolher Restaurante");
        
        telaBoasVindas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaBoasVindas.getContentPane().setBackground(Color.RED);
        
        // Ação do botão para escolher o restaurante
        JButton botaoEscolher = (JButton) telaBoasVindas.getContentPane().getComponent(2);
        botaoEscolher.addActionListener(e -> {
            String nomeRestauranteEscolhido = (String) ((JComboBox<String>) telaBoasVindas.getContentPane().getComponent(1)).getSelectedItem();
            if (nomeRestauranteEscolhido == null) {
                JOptionPane.showMessageDialog(telaBoasVindas, "Por favor, selecione um restaurante.");
            } else {
                JOptionPane.showMessageDialog(telaBoasVindas, "Restaurante " + nomeRestauranteEscolhido + " escolhido!");
                telaBoasVindas.dispose();
                exibirTelaProdutos(nomeRestauranteEscolhido);
            }
        });

        telaBoasVindas.setVisible(true);
    }

    private static List<String> listarRestaurantes() {
        List<String> restaurantes = new ArrayList<>();
        RestauranteDAO restauranteDAO = new RestauranteDAO();
        try {
            restaurantes = restauranteDAO.listarRestaurantes();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar restaurantes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return restaurantes;
    }

    private static void exibirTelaProdutos(String nomeRestaurante) {
        // Tela de Produtos
        JFrame telaProdutos = criarTelaComComboBox(listarProdutosPorRestaurante(nomeRestaurante), 
                "Produtos - " + nomeRestaurante, "Produtos:", "Adicionar ao Pedido");

        telaProdutos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaProdutos.getContentPane().setBackground(Color.RED);

        // Ação do botão para adicionar produto ao pedido
        JButton botaoAdicionar = (JButton) telaProdutos.getContentPane().getComponent(2);
        botaoAdicionar.addActionListener(e -> {
            String nomeProdutoEscolhido = (String) ((JComboBox<String>) telaProdutos.getContentPane().getComponent(1)).getSelectedItem();
            if (nomeProdutoEscolhido != null) {
                produtosAdicionados.add(nomeProdutoEscolhido);
                JOptionPane.showMessageDialog(telaProdutos, "Produto " + nomeProdutoEscolhido + " adicionado ao pedido!");
                atualizarListaProdutosAdicionados(telaProdutos);
            } else {
                JOptionPane.showMessageDialog(telaProdutos, "Selecione um produto antes de adicionar ao pedido.");
            }
        });

        telaProdutos.setVisible(true);
    }

    private static List<String> listarProdutosPorRestaurante(String nomeRestaurante) {
        List<String> produtos = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        try {
            produtos = produtoDAO.listarProdutosPorRestaurante(nomeRestaurante);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        return produtos;
    }

    private static void atualizarListaProdutosAdicionados(JFrame telaProdutos) {
        StringBuilder lista = new StringBuilder("Produtos no Pedido:\n");
        for (String produto : produtosAdicionados) {
            lista.append(produto).append("\n");
        }
        
        // Exibe a lista atualizada de produtos no pedido
        JOptionPane.showMessageDialog(telaProdutos, lista.toString(), "Produtos no Pedido", JOptionPane.INFORMATION_MESSAGE);
    }

    private static JFrame criarTelaComComboBox(List<String> itens, String titulo, String mensagem, String botaoTexto) {
        JFrame tela = new JFrame(titulo);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setSize(400, 400);

        JLabel label = new JLabel(mensagem, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.WHITE);
        tela.add(label, BorderLayout.NORTH);

        JComboBox<String> combo = new JComboBox<>(itens.toArray(new String[0]));
        tela.add(combo, BorderLayout.CENTER);

        JButton botao = new JButton(botaoTexto);
        tela.add(botao, BorderLayout.SOUTH);

        return tela;
    }
}
