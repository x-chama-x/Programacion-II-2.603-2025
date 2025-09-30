package models;

import connection.ConexionDB;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public void insertar(Estudiante e) {
        String sql = "INSERT INTO estudiante (dni, nombre, apellido, fechaNacimiento) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, e.getDni());
            stmt.setString(2, e.getNombre());
            stmt.setString(3, e.getApellido());
            stmt.setDate(4, Date.valueOf(e.getFechaNacimiento()));
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Estudiante> listar() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";
        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Estudiante e = new Estudiante(
                        rs.getInt("legajo"),
                        rs.getInt("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getDate("fechaNacimiento").toLocalDate()
                );
                lista.add(e);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public void actualizar(Estudiante e) {
        String sql = "UPDATE estudiante SET dni=?, nombre=?, apellido=?, fechaNacimiento=? WHERE legajo=?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, e.getDni());
            stmt.setString(2, e.getNombre());
            stmt.setString(3, e.getApellido());
            stmt.setDate(4, Date.valueOf(e.getFechaNacimiento()));
            stmt.setInt(5, e.getLegajo());
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void eliminar(int legajo) {
        String sql = "DELETE FROM estudiante WHERE legajo=?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, legajo);
            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}