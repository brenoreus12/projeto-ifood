public class TelaProdutos {
    public static List<String> selecionarProdutos(String restaurante) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> produtosSelecionados = new ArrayList<>();

        try {
            conn = Conexao.getConexao();
            String sql = "SELECT p.nome, p.preco FROM Produto p " +
                         "INNER JOIN Restaurante r ON p.id_restaurante = r.id " +
                         "WHERE r.nome = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, restaurante);
            rs = stmt.executeQuery();

            List<String> produtos = new ArrayList<>();
            while (rs.next()) {
                String produto = rs.getString("nome") + " - R$ " + rs.getDouble("preco");
                produtos.add(produto);
            }

            // Exibe a listagem de produtos
            String[] opcoes = produtos.toArray(new String[0]);
            boolean adicionarMais = true;

            while (adicionarMais) {
                String produtoEscolhido = (String) JOptionPane.showInputDialog(
                    null,
                    "Produtos disponíveis no restaurante " + restaurante + ":",
                    "Escolher Produtos",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
                );

                if (produtoEscolhido == null) break;

                // Pergunta a quantidade
                String quantidadeStr = JOptionPane.showInputDialog("Digite a quantidade do produto selecionado:");
                int quantidade = Integer.parseInt(quantidadeStr);

                produtosSelecionados.add(produtoEscolhido + " (Quantidade: " + quantidade + ")");

                // Pergunta se deseja adicionar mais produtos
                int continuar = JOptionPane.showConfirmDialog(
                    null,
                    "Deseja adicionar mais produtos?",
                    "Continuar",
                    JOptionPane.YES_NO_OPTION
                );

                adicionarMais = (continuar == JOptionPane.YES_OPTION);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar produtos: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            Conexao.fecharConexao(conn);
            try {
                if (stmt != null) stmt.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos: " + e.getMessage());
            }
        }

        return produtosSelecionados;
    }
}
