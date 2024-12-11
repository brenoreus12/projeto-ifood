public class TelaPagamento {
    public static String escolherFormaPagamento() {
        String sql = "SELECT descricao FROM Forma_Pagamento";
        
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<String> formasPagamento = new ArrayList<>();
            while (rs.next()) {
                formasPagamento.add(rs.getString("descricao"));
            }

            if (formasPagamento.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhuma forma de pagamento disponível!", "Erro", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            // Exibe a listagem de formas de pagamento
            String[] opcoes = formasPagamento.toArray(new String[0]);
            String formaEscolhida = (String) JOptionPane.showInputDialog(
                null,
                "Escolha a forma de pagamento:",
                "Forma de Pagamento",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
            );

            // Verifica se o usuário cancelou a seleção
            if (formaEscolhida == null) {
                return null; // Caso o usuário tenha cancelado a seleção
            }

            return formaEscolhida;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar formas de pagamento: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
