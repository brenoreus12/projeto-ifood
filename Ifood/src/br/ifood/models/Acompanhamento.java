package br.ifood.models;

public class Acompanhamento {
    private int id;
    private String nome;
    private String descricao;  // Adicionando uma descrição, caso seja útil
    private double valor;

    // Construtor atualizado
    public Acompanhamento(int id, String nome, double valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    // Método para definir a descrição
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Método para definir o valor
    public void setValor(double valor) {
        this.valor = valor;
    }
}
