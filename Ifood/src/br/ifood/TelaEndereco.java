import org.json.JSONObject;
import javax.swing.*;

public class TelaEndereco {
    public static JSONObject cadastrarEndereco() {
        String cep;
        JSONObject endereco = null;

        while (endereco == null) {
            cep = JOptionPane.showInputDialog("Digite o CEP (somente números):");
            
            // Verifica se o CEP tem 8 caracteres numéricos
            if (cep != null && cep.matches("\\d{8}")) {
                endereco = ViaCepAPI.consultarCep(cep);

                if (endereco != null) {
                    String numero = JOptionPane.showInputDialog("Digite o número da casa:");
                    endereco.put("numero", numero);

                    JOptionPane.showMessageDialog(
                        null,
                        "Endereço cadastrado com sucesso:\n" +
                        "Rua: " + endereco.getString("logradouro") + "\n" +
                        "Bairro: " + endereco.getString("bairro") + "\n" +
                        "Cidade: " + endereco.getString("localidade") + "\n" +
                        "Estado: " + endereco.getString("uf") + "\n" +
                        "Número: " + numero
                    );
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, "CEP inválido! Tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "CEP inválido! O formato correto é 8 dígitos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        return endereco;
    }
}
