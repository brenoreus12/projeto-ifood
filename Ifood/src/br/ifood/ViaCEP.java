import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ViaCEP {
    private static final String API_URL = "https://viacep.com.br/ws/";

    public static Endereco buscarEndereco(String cep) {
        HttpURLConnection conexao = null;
        BufferedReader br = null;
        
        try {
            URL url = new URL(API_URL + cep + "/json/");
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Content-Type", "application/json");

            // Verifica o código de status HTTP antes de processar a resposta
            int status = conexao.getResponseCode();
            if (status != 200) {
                System.out.println("Erro na requisição: Código de status " + status);
                return null;
            }

            // Usando try-with-resources para garantir o fechamento adequado
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(conexao.getInputStream()))) {
                StringBuilder resposta = new StringBuilder();
                String linha;
                while ((linha = reader.readLine()) != null) {
                    resposta.append(linha);
                }

                JSONObject json = new JSONObject(resposta.toString());
                if (json.has("erro")) {
                    return null; // Retorna nulo se o CEP não for encontrado
                }

                Endereco endereco = new Endereco();
                endereco.setRua(json.optString("logradouro", "Não informado"));
                endereco.setBairro(json.optString("bairro", "Não informado"));
                endereco.setCidade(json.optString("localidade", "Não informado"));
                endereco.setEstado(json.optString("uf", "Não informado"));
                return endereco;
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar endereço pelo CEP: " + e.getMessage());
            return null;
        } finally {
            if (conexao != null) {
                conexao.disconnect();
            }
        }
    }
}
