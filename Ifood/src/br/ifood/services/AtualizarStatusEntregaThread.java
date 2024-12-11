package br.ifood.threads;

import br.ifood.dao.StatusEntregaDAO;
import br.ifood.dao.PedidoDAO;
import java.util.List;

public class AtualizarStatusEntregaThread extends Thread {

    private int idPedido;
    private List<Integer> statusSequence;  // Sequência de status, por exemplo: [1, 2, 3]
    private int intervalo;  // Intervalo entre as mudanças de status, em milissegundos

    public AtualizarStatusEntregaThread(int idPedido, List<Integer> statusSequence, int intervalo) {
        this.idPedido = idPedido;
        this.statusSequence = statusSequence;
        this.intervalo = intervalo;
    }

    @Override
    public void run() {
        StatusEntregaDAO statusEntregaDAO = new StatusEntregaDAO();
        for (int statusId : statusSequence) {
            try {
                // Atualiza o status do pedido
                statusEntregaDAO.atualizarStatus(idPedido, statusId);
                System.out.println("Pedido " + idPedido + " atualizado para o status " + statusId);
                Thread.sleep(intervalo);  // Espera o intervalo de tempo antes de atualizar novamente
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
