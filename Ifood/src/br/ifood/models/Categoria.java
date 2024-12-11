package br.ifood.models;

public class Categoria {
    private int id;
    private String tipo;
    private String nome;
    private String descricao;

    public Categoria(int id, String tipo, String nome, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
