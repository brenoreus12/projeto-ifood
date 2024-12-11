import java.sql.*;

public class Endereco {
    private int id;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String numero;
    private String complemento;
    private String pontoReferencia;
    private String cep;

    // Construtores, getters e setters
    public Endereco(String rua, String bairro, String cidade, String estado, String numero, String complemento, String pontoReferencia, String cep) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.numero = numero;
        this.complemento = complemento;
        this.pontoReferencia = pontoReferencia;
        this.cep = cep;
    }

    public Endereco() {}

    // Método para salvar o endereço no banco de dados
    public boolean salvar() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexao.getConexao();  // Conexão com o banco
            String sql = "INSERT INTO Endereco (rua, bairro, cidade, estado, numero, complemento, ponto_referencia, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);  // Preparação da consulta SQL

            // Definindo os parâmetros no PreparedStatement
            stmt.setString(1, this.rua);
            stmt.setString(2, this.bairro);
            stmt.setString(3, this.cidade);
            stmt.setString(4, this.estado);
            stmt.setString(5, this.numero);
            stmt.setString(6, this.complemento);
            stmt.setString(7, this.pontoReferencia);
            stmt.setString(8, this.cep);

            // Executando a atualização e verificando se houve alterações
            int affectedRows = stmt.executeUpdate();

            // Se houver linhas afetadas, pega o ID gerado
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        this.id = generatedKeys.getInt(1);  // Define o ID do endereço inserido
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Erro ao salvar endereço: " + e.getMessage());  // Exibindo erro caso ocorra
        } finally {
            Conexao.fecharConexao(conn);  // Fechando a conexão com o banco
            try {
                if (stmt != null) stmt.close();  // Fechando o PreparedStatement
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());  // Exibindo erro ao fechar recursos
            }
        }

        return false;  // Caso não tenha dado certo, retorna false
    }

    @Override
    public String toString() {
        return rua + ", " + numero + " - " + bairro + ", " + cidade + " - " + estado;  // Representação do endereço
    }
}
