import javax.swing.*;
import java.util.List;

public class TelaFinalizacao {
    public static boolean finalizarPedido(List<String> itensPedido) {
        StringBuilder resumo = new StringBuilder("Resumo do Pedido:\n");

        // Adiciona os itens do pedido ao resumo
        for (String item : itensPedido) {
            resumo.append(item).append("\n");
        }

        resumo.append("\nDeseja finalizar ou cancelar o pedido?");

        // Exibe o diálogo de confirmação com opções de Finalizar, Cancelar e Fechar
        int opcao = JOptionPane.showOptionDialog(
            null,
            resumo.toString(),
            "Finalizar Pedido",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new String[]{"Finalizar", "Cancelar", "Fechar"},
            "Finalizar"
        );

        // Retorna true se o pedido for finalizado
        if (opcao == JOptionPane.YES_OPTION) {
            return true;  // Finalizar
        } else if (opcao == JOptionPane.NO_OPTION) {
            return false;  // Cancelar
        } else {
            // Caso o usuário feche a janela ou clique em "Fechar"
            return false;  // Aqui pode ser uma ação de fechamento ou voltar ao menu anterior
        }
    }
}
