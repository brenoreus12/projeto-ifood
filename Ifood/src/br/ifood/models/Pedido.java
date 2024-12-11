package br.ifood.models;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Restaurante restaurante;
    private List<Produto> produtos;
    private double valorTotal;

    public Pedido(int id, Restaurante restaurante, double valorTotal) {
        this.id = id;
        this.restaurante = restaurante;
        this.produtos = new ArrayList<>();
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
        this.valorTotal += produto.getPreco() + produto.calcularValorAcompanhamentos();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(id).append("\n");
        sb.append("Restaurante: ").append(restaurante.getNome()).append("\n");
        sb.append("Produtos:\n");
        for (Produto p : produtos) {
            sb.append("- ").append(p).append("\n");
        }
        sb.append("Total: R$ ").append(String.format("%.2f", valorTotal));
        return sb.toString();
    }
}
