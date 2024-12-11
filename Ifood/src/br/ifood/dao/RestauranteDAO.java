package br.ifood.dao;

import br.ifood.models.Restaurante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestauranteDAO {
    public List<Restaurante> listarTodos() {
        List<Restaurante> restaurantes = new ArrayList<>();
        String sql = "SELECT * FROM Restaurante";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Restaurante restaurante = new Restaurante(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("endereco")
                );
                restaurantes.add(restaurante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return restaurantes;
    }

    public Restaurante buscarPorId(int id) {
        String sql = "SELECT * FROM Restaurante WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Restaurante(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("endereco")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
