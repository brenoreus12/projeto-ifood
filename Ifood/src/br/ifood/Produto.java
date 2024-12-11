import java.math.BigDecimal;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int idCategoria;
    private int idRestaurante;

    // Construtor com todos os campos
    public Produto(int id, String nome, String descricao, BigDecimal preco, int idCategoria, int idRestaurante) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        setPreco(preco);  // Usa o setter para garantir que o preço não seja negativo
        this.idCategoria = idCategoria;
        this.idRestaurante = idRestaurante;
    }

    // Construtor sem id, útil para inserções
    public Produto(String nome, String descricao, BigDecimal preco, int idCategoria, int idRestaurante) {
        this.nome = nome;
        this.descricao = descricao;
        setPreco(preco);  // Usa o setter para garantir que o preço não seja negativo
        this.idCategoria = idCategoria;
        this.idRestaurante = idRestaurante;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { 
        // Garantindo que o preço não seja negativo
        if (preco.compareTo(BigDecimal.ZERO) >= 0) {
            this.preco = preco;
        } else {
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
    }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public int getIdRestaurante() { return idRestaurante; }
    public void setIdRestaurante(int idRestaurante) { this.idRestaurante = idRestaurante; }

    // Método toString para representar o objeto como string
    @Override
    public String toString() {
        return "Produto: " + nome + " - " + descricao + ", Preço: R$" + preco.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    // Método para validar a categoria e o restaurante
    public boolean validarProduto() {
        // Aqui você pode adicionar lógica para validar se a categoria e restaurante existem
        // Por exemplo, verificando no banco de dados ou em alguma lista de categorias e restaurantes válidos.
        return idCategoria > 0 && idRestaurante > 0;
    }
}
