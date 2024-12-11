import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Promocao {
    private int id;
    private String nome;
    private String codigo;
    private String tipo;
    private double valor;
    private Date validade;

    // Construtores, getters e setters
    public Promocao(int id, String nome, String codigo, String tipo, double valor, Date validade) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.tipo = tipo;
        this.valor = valor;
        this.validade = validade;
    }

    public Promocao() {}

    // Método para listar promoções ativas
    public static List<Promocao> listarAtivas() throws SQLException {
        List<Promocao> promocoes = new ArrayList<>();
        String sql = "SELECT * FROM Promocao WHERE validade >= CURRENT_DATE";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Promocao promocao = new Promocao();
                promocao.setId(rs.getInt("id"));
                promocao.setNome(rs.getString("nome"));
                promocao.setCodigo(rs.getString("codigo"));
                promocao.setTipo(rs.getString("tipo"));
                promocao.setValor(rs.getDouble("valor"));
                promocao.setValidade(rs.getDate("validade"));
                promocoes.add(promocao);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar promoções ativas: " + e.getMessage(), e);
        }

        return promocoes;
    }

    @Override
    public String toString() {
        return nome + " - " + tipo + ": R$ " + valor;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public Date getValidade() { return validade; }
    public void setValidade(Date validade) { this.validade = validade; }
}
