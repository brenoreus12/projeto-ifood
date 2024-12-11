package br.ifood.models;

import java.util.List;
import java.util.ArrayList;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private String descricao;  // Campo descricao adicionado
    private List<Acompanhamento> acompanhamentos;  // Lista de acompanhamentos

    // Construtor atualizado para incluir a descrição
    public Produto(int id, String nome, double preco, String descricao) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para obter a lista de acompanhamentos
    public List<Acompanhamento> getAcompanhamentos() {
        return acompanhamentos;
    }

    // Método para definir a lista de acompanhamentos
    public void setAcompanhamentos(List<Acompanhamento> acompanhamentos) {
        this.acompanhamentos = acompanhamentos;
    }

    // Método setter para descrição
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Método para calcular o valor total dos acompanhamentos (se necessário)
    public double calcularValorAcompanhamentos() {
        double total = 0.0;
        if (acompanhamentos != null) {
            for (Acompanhamento acompanhamento : acompanhamentos) {
                total += acompanhamento.getValor();
            }
        }
        return total;
    }

    // Adicionando o método para adicionar acompanhamentos
    public void adicionarAcompanhamento(Acompanhamento acompanhamento) {
        if (acompanhamentos == null) {
        acompanhamentos = new ArrayList<>();
    }
        acompanhamentos.add(acompanhamento);
    }

}
