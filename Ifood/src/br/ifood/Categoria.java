import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private int id;
    private String tipo;
    private String nome;
    private String descricao;

    // Construtores, getters e setters
    public Categoria(int id, String tipo, String nome, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Categoria() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Método para listar todas as categorias
    public static List<Categoria> listarTodas() {
        List<Categoria> categorias = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConexao();
            String sql = "SELECT * FROM Categoria";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria categoria = new Categoria(
                    rs.getInt("id"),
                    rs.getString("tipo"),
                    rs.getString("nome"),
                    rs.getString("descricao")
                );
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar categorias: " + e.getMessage());
        } finally {
            // Fecha a conexão e outros recursos
            Conexao.fecharConexao(conn);
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return categorias;
    }

    @Override
    public String toString() {
        return nome;
    }
}
