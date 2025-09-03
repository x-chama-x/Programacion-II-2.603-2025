package instituto.backend.Controllers;

import instituto.Conections.BDMaterias;
import instituto.backend.Models.Materia;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MateriasDAO {

    public void crearMateria(Materia m) {
        if (!existe(m.getCodigo())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDMaterias.getARCHIVO(), true))) {
                bw.write(m.toString());
                bw.newLine();
            } catch (IOException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            } finally {
                System.out.println("Materia agregada...");
            }
        } else {
            System.out.println("ERROR: Materia ya registrada");
        }
    }

    public boolean existe(int codigo) {
        boolean encontrado = false;
        List<Materia> lista = leerMaterias();
        for (Materia m : lista) {
            if (m.getCodigo() == codigo) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    public List<Materia> leerMaterias() {
        List<Materia> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BDMaterias.getARCHIVO()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(Materia.fromString(linea));
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return lista;
    }

    public void actualizarMateria(int codigo, Materia nuevo) {
        List<Materia> lista = leerMaterias();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDMaterias.getARCHIVO()))) {
            for (Materia m : lista) {
                if (m.getCodigo() == codigo) {
                    bw.write(nuevo.toString());
                } else {
                    bw.write(m.toString());
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String buscarPorCodigo(int codigo) {
        String mat = "Materia no encontrada...";
        List<Materia> lista = leerMaterias();
        for (Materia m : lista) {
            if (m.getCodigo() == codigo) {
                mat = m.toString();
            }
        }
        return mat;
    }

    public void eliminarMateria(int codigo) {
        List<Materia> lista = leerMaterias();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDMaterias.getARCHIVO()))) {
            for (Materia m : lista) {
                if (m.getCodigo() != codigo) {
                    bw.write(m.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
