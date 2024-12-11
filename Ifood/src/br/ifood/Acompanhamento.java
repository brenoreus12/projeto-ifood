import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Acompanhamento {
    private int id;
    private String nome;
    private String descricao;
    private double valor;

    // Construtores, getters e setters
    public Acompanhamento(int id, String nome, String descricao, double valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Acompanhamento() {}

    // Método para listar todos os acompanhamentos
    public static List<Acompanhamento> listarTodos() {
        List<Acompanhamento> acompanhamentos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConexao(); // Certifique-se de que a classe `Conexao` está implementada corretamente.
            String sql = "SELECT * FROM Acompanhamento";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Acompanhamento acompanhamento = new Acompanhamento(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("descricao"),
                    rs.getDouble("valor")
                );
                acompanhamentos.add(acompanhamento);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar acompanhamentos: " + e.getMessage());
        } finally {
            Conexao.fecharConexao(conn); // Garanta que este método fecha adequadamente a conexão.
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return acompanhamentos;
    }

    @Override
    public String toString() {
        return nome + " - R$ " + valor;
    }
}
