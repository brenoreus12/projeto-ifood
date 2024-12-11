import java.sql.*;

public class EnderecoDAO {
    // Método para salvar um novo endereço no banco de dados
    public int salvar(Endereco endereco) {
        String sql = "INSERT INTO Endereco (rua, bairro, cidade, estado, numero, complemento, ponto_referencia, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();  // Obtendo a conexão com o banco de dados
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  // Preparando a instrução SQL

            // Atribuindo os parâmetros para o PreparedStatement
            stmt.setString(1, endereco.getRua());
            stmt.setString(2, endereco.getBairro());
            stmt.setString(3, endereco.getCidade());
            stmt.setString(4, endereco.getEstado());
            stmt.setString(5, endereco.getNumero());
            stmt.setString(6, endereco.getComplemento());
            stmt.setString(7, endereco.getPontoReferencia());
            stmt.setString(8, endereco.getCep());

            // Executando o comando SQL
            stmt.executeUpdate();

            // Obtendo a chave gerada (ID) do novo endereço
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);  // Retornando o ID gerado
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Exibindo o erro em caso de falha
        }
        return -1;  // Retorna -1 caso não consiga salvar
    }

    // Método para buscar um endereço pelo ID
    public Endereco buscarPorId(int id) {
        Endereco endereco = null;
        String sql = "SELECT * FROM Endereco WHERE id = ?";

        try (Connection conn = Conexao.conectar();  // Obtendo a conexão com o banco de dados
             PreparedStatement stmt = conn.prepareStatement(sql)) {  // Preparando a consulta SQL

            // Definindo o parâmetro do ID na consulta
            stmt.setInt(1, id);

            // Executando a consulta
            ResultSet rs = stmt.executeQuery();

            // Se o endereço for encontrado, preenche os atributos
            if (rs.next()) {
                endereco = new Endereco();
                endereco.setId(rs.getInt("id"));
                endereco.setRua(rs.getString("rua"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setPontoReferencia(rs.getString("ponto_referencia"));
                endereco.setCep(rs.getString("cep"));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Exibindo o erro em caso de falha
        }
        return endereco;  // Retorna o objeto Endereco ou null se não encontrado
    }
}
