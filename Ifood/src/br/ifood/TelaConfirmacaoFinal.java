import javax.swing.*;
import java.util.List;

public class TelaConfirmacaoFinal {
    public static boolean confirmarPedido(List<String> itensPedido, String endereco, String formaPagamento) {
        StringBuilder resumo = new StringBuilder("Resumo do Pedido:\n");
        
        // Adiciona itens do pedido com numeração
        for (int i = 0; i < itensPedido.size(); i++) {
            resumo.append(i + 1).append(". ").append(itensPedido.get(i)).append("\n");
        }

        resumo.append("\nEndereço: ").append(endereco);
        resumo.append("\nForma de Pagamento: ").append(formaPagamento);
        resumo.append("\n\nDeseja finalizar o pedido?");

        // Tenta exibir a janela de confirmação
        try {
            int opcao = JOptionPane.showConfirmDialog(
                null,
                resumo.toString(),
                "Finalizar Pedido",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );

            return opcao == JOptionPane.YES_OPTION; // Retorna true se o pedido for finalizado
        } catch (Exception e) {
            // Em caso de erro, exibe uma mensagem de erro
            JOptionPane.showMessageDialog(null, "Erro ao tentar confirmar o pedido. Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return false; // Retorna false em caso de erro
        }
    }
}
