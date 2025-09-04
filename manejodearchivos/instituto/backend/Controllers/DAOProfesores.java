package instituto.backend.Controllers;

import instituto.Conections.BDProfesores;
import instituto.backend.Models.Profesor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAOProfesores {

    public void crearProfesor(Profesor p) {
        if (!existe(p.getDni())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDProfesores.getARCHIVO(), true))) {
                bw.write(p.toString());
                bw.newLine();
            } catch (IOException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            } finally {
                System.out.println("Profesor agregado...");
            }
        } else {
            System.out.println("ERROR: Profesor ya registrado");
        }
    }

    public boolean existe(int dni) {
        boolean encontrado = false;
        List<Profesor> lista = leerProfesores();
        for (Profesor p : lista) {
            if (p.getDni() == dni) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    public List<Profesor> leerProfesores() {
        List<Profesor> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BDProfesores.getARCHIVO()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(Profesor.fromString(linea));
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return lista;
    }

    public void actualizarProfesor(int dni, Profesor nuevo) {
        List<Profesor> lista = leerProfesores();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDProfesores.getARCHIVO()))) {
            for (Profesor p : lista) {
                if (p.getDni() == dni) {
                    bw.write(nuevo.toString());
                } else {
                    bw.write(p.toString());
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String buscarPorDni(int dni) {
        String prof = "Profesor no encontrado...";
        List<Profesor> lista = leerProfesores();
        for (Profesor p : lista) {
            if (p.getDni() == dni) {
                prof = p.toString();
            }
        }
        return prof;
    }

    public void eliminarProfesor(int dni) {
        List<Profesor> lista = leerProfesores();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDProfesores.getARCHIVO()))) {
            for (Profesor p : lista) {
                if (p.getDni() != dni) {
                    bw.write(p.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
