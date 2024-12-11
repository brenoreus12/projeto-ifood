import java.math.BigDecimal;
import java.sql.Timestamp;

public class Pedido {
    private int id;
    private Timestamp data;
    private int idRestaurante;
    private BigDecimal valor;
    private BigDecimal taxaEntrega;
    private int idStatusEntrega;
    private int idFormaPagamento;
    private String observacoes;
    private BigDecimal troco;
    private int idEndereco;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Timestamp getData() { return data; }
    public void setData(Timestamp data) { this.data = data; }

    public int getIdRestaurante() { return idRestaurante; }
    public void setIdRestaurante(int idRestaurante) { this.idRestaurante = idRestaurante; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public BigDecimal getTaxaEntrega() { return taxaEntrega; }
    public void setTaxaEntrega(BigDecimal taxaEntrega) { this.taxaEntrega = taxaEntrega; }

    public int getIdStatusEntrega() { return idStatusEntrega; }
    public void setIdStatusEntrega(int idStatusEntrega) { this.idStatusEntrega = idStatusEntrega; }

    public int getIdFormaPagamento() { return idFormaPagamento; }
    public void setIdFormaPagamento(int idFormaPagamento) { this.idFormaPagamento = idFormaPagamento; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public BigDecimal getTroco() { return troco; }
    public void setTroco(BigDecimal troco) { this.troco = troco; }

    public int getIdEndereco() { return idEndereco; }
    public void setIdEndereco(int idEndereco) { this.idEndereco = idEndereco; }
}
