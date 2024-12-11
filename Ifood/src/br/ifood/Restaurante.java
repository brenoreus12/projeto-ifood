public class Restaurante {
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String categorias;
    private String horarioFuncionamento;
    private String historicoRetirada;

    // Constructor with all fields
    public Restaurante(int id, String nome, String endereco, String telefone, 
                       String categorias, String horarioFuncionamento, String historicoRetirada) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.categorias = categorias;
        this.horarioFuncionamento = horarioFuncionamento;
        this.historicoRetirada = historicoRetirada;
    }

    // Constructor without ID (for object creation without immediate DB use)
    public Restaurante(String nome, String endereco, String telefone, 
                       String categorias, String horarioFuncionamento, String historicoRetirada) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.categorias = categorias;
        this.horarioFuncionamento = horarioFuncionamento;
        this.historicoRetirada = historicoRetirada;
    }

    // Getters and Setters with validation
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) {
        if (telefone != null && telefone.matches("\\d{4}-\\d{4}")) {
            this.telefone = telefone;
        } else {
            throw new IllegalArgumentException("Telefone deve estar no formato XXXX-XXXX");
        }
    }

    public String getCategorias() { return categorias; }
    public void setCategorias(String categorias) { this.categorias = categorias; }

    public String getHorarioFuncionamento() { return horarioFuncionamento; }
    public void setHorarioFuncionamento(String horarioFuncionamento) {
        if (horarioFuncionamento != null && !horarioFuncionamento.isEmpty()) {
            this.horarioFuncionamento = horarioFuncionamento;
        } else {
            throw new IllegalArgumentException("Horário de funcionamento não pode ser vazio");
        }
    }

    public String getHistoricoRetirada() { return historicoRetirada; }
    public void setHistoricoRetirada(String historicoRetirada) { this.historicoRetirada = historicoRetirada; }

    // toString method for easy representation
    @Override
    public String toString() {
        return "Restaurante{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", endereco='" + endereco + '\'' +
               ", telefone='" + telefone + '\'' +
               ", categorias='" + categorias + '\'' +
               ", horarioFuncionamento='" + horarioFuncionamento + '\'' +
               ", historicoRetirada='" + historicoRetirada + '\'' +
               '}';
    }
}
