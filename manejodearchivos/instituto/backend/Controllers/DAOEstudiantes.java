package instituto.backend.Controllers;

import instituto.Conections.BDEstudiantes;
import instituto.backend.Models.Estudiante;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAOEstudiantes {
    
    public void crearEstudiante(Estudiante e) {
        if (!existe(e.getDni())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDEstudiantes.getARCHIVO(), true))) {
                bw.write(e.toString());
                bw.newLine();
            } catch (IOException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            } finally {
                System.out.println("Estudiante agregado...");
            }
        } else {
            System.out.println("ERROR: Estudiante ya registrado");
        }
    }

    public boolean existe(int dni) {
        boolean encontrado = false;
        List<Estudiante> lista = leerEstudiantes();
        for (Estudiante e : lista) {
            if (e.getDni() == dni) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    public List<Estudiante> leerEstudiantes() {
        List<Estudiante> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BDEstudiantes.getARCHIVO()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(Estudiante.fromString(linea));
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return lista;
    }

    public void actualizarEstudiante(int dni, Estudiante nuevo) {
        List<Estudiante> lista = leerEstudiantes();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDEstudiantes.getARCHIVO()))) {
            for (Estudiante e : lista) {
                if (e.getDni() == dni) {
                    bw.write(nuevo.toString());
                } else {
                    bw.write(e.toString());
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String buscarPorDni(int dni) {
        String est = "Estudiante no encontrado...";
        List<Estudiante> lista = leerEstudiantes();
        for (Estudiante e : lista) {
            if (e.getDni() == dni) {
                est = e.toString();
            }
        }
        return est;
    }

    public void eliminarEstudiante(int dni) {
        List<Estudiante> lista = leerEstudiantes();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDEstudiantes.getARCHIVO()))) {
            for (Estudiante e : lista) {
                if (e.getDni() != dni) {
                    bw.write(e.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
