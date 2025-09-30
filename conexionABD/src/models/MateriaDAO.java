package models;

import connection.ConexionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {

    public void insertar(Materia m) {
        String sql = "INSERT INTO materia (nombreMateria) VALUES (?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNombreMateria());
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Materia> listar() {
        List<Materia> lista = new ArrayList<>();
        String sql = "SELECT * FROM materia";
        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Materia m = new Materia(
                        rs.getInt("codigo"),
                        rs.getString("nombreMateria")
                );
                lista.add(m);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Materia m) {
        String sql = "UPDATE materia SET nombreMateria=? WHERE codigo=?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, m.getNombreMateria());
            stmt.setInt(2, m.getCodigo());
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void eliminar(int codigo) {
        String sql = "DELETE FROM materia WHERE codigo=?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Materia buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM materia WHERE codigo = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Materia(
                        rs.getInt("codigo"),
                        rs.getString("nombreMateria")
                );
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null; // Si no encuentra la materia
    }
}
