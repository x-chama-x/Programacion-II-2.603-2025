package backend.Controllers;

import Conections.BDCuarteles;
import backend.Models.Cuartel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAOCuarteles {

    public void crearCuartel(Cuartel c) {
        if (puedeRegistrarCuartel(c.getCodigo())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDCuarteles.getARCHIVO(), true))) {
                bw.write(c.toString());
                bw.newLine();
            } catch (IOException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            } finally {
                System.out.println("Cuartel agregado...");
            }
        } else {
            System.out.println("ERROR: Ya existe un cuartel con el código " + c.getCodigo());
        }
    }

    public boolean existe(int codigo) {
        boolean encontrado = false;
        List<Cuartel> lista = leerCuarteles();
        for (Cuartel c : lista) {
            if (c.getCodigo() == codigo) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    public boolean existePorSector(String sector) {
        boolean encontrado = false;
        List<Cuartel> lista = leerCuarteles();
        for (Cuartel c : lista) {
            if (c.getSector().equalsIgnoreCase(sector)) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    private boolean puedeRegistrarCuartel(int codigo) {
        return !existe(codigo);
    }

    public List<Cuartel> leerCuarteles() {
        List<Cuartel> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BDCuarteles.getARCHIVO()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(Cuartel.fromString(linea));
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return lista;
    }

    public void actualizarCuartel(int codigo, Cuartel nuevo) {
        List<Cuartel> lista = leerCuarteles();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDCuarteles.getARCHIVO()))) {
            for (Cuartel c : lista) {
                if (c.getCodigo() == codigo) {
                    bw.write(nuevo.toString());
                } else {
                    bw.write(c.toString());
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public String buscarPorCodigo(int codigo) {
        String resultado = "Cuartel no encontrado...";
        List<Cuartel> lista = leerCuarteles();
        for (Cuartel c : lista) {
            if (c.getCodigo() == codigo) {
                resultado = c.toString();
                break;
            }
        }
        return resultado;
    }

    public Cuartel obtenerPorCodigo(int codigo) {
        List<Cuartel> lista = leerCuarteles();
        for (Cuartel c : lista) {
            if (c.getCodigo() == codigo) {
                return c;
            }
        }
        return null;
    }

    public Cuartel obtenerPorSector(String sector) {
        List<Cuartel> lista = leerCuarteles();
        for (Cuartel c : lista) {
            if (c.getSector().equalsIgnoreCase(sector)) {
                return c;
            }
        }
        return null;
    }

    public List<Cuartel> obtenerCuartelesPorSector(String sector) {
        List<Cuartel> cuartelesSector = new ArrayList<>();
        List<Cuartel> lista = leerCuarteles();
        for (Cuartel c : lista) {
            if (c.getSector().toLowerCase().contains(sector.toLowerCase())) {
                cuartelesSector.add(c);
            }
        }
        return cuartelesSector;
    }

    public void eliminarCuartel(int codigo) {
        List<Cuartel> lista = leerCuarteles();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDCuarteles.getARCHIVO()))) {
            for (Cuartel c : lista) {
                if (c.getCodigo() != codigo) {
                    bw.write(c.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
