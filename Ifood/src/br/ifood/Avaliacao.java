package br.ifood;

public class Avaliacao {
    private int id;
    private int idCliente;
    private int idProduto; // ou idRestaurante, dependendo do seu sistema
    private double nota; // nota da avaliação, pode ser de 1 a 5 ou de 1 a 10
    private String comentario;

    // Construtor
    public Avaliacao(int idCliente, int idProduto, double nota, String comentario) {
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.nota = nota;
        this.comentario = comentario;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", idProduto=" + idProduto +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
