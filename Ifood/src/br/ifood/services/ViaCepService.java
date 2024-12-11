package br.ifood.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URISyntaxException;

public class ViaCepService {
    public static String buscarEnderecoPorCep(String cep) {
        String endpoint = "https://viacep.com.br/ws/" + cep + "/json/";
        try {
            // Usando URI para criar uma URL
            URI uri = new URI(endpoint);
            URL url = uri.toURL();  // Converte URI para URL
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder resposta = new StringBuilder();
            String linha;

            while ((linha = br.readLine()) != null) {
                resposta.append(linha);
            }
            br.close();

            return resposta.toString();
        } catch (URISyntaxException e) {
            e.printStackTrace(); // Trate ou logue a exceção adequadamente
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
