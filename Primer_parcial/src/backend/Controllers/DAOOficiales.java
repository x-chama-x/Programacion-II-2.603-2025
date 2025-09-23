package backend.Controllers;

import Conections.BDOficiales;
import backend.Models.Oficial;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAOOficiales {

    private ValidadorDNI validadorDNI;

    public DAOOficiales() {
        this.validadorDNI = new ValidadorDNI();
    }

    public void crearOficial(Oficial o) {
        if (!validadorDNI.esFormatoValido(o.getDni())) {
            System.out.println("ERROR: El formato del DNI no es válido. Debe tener entre 7 y 8 dígitos.");
            return;
        }

        // Obtener listas actuales para validación
        List<Oficial> oficiales = leerOficiales();
        DAOSoldados daoSoldados = new DAOSoldados();
        List<backend.Models.Soldado> soldados = daoSoldados.leerSoldados();

        if (validadorDNI.existeDNI(o.getDni(), soldados, oficiales)) {
            System.out.println("ERROR: El DNI " + o.getDni() + " ya está registrado en el sistema.");
            System.out.println(validadorDNI.obtenerInfoDNI(o.getDni(), soldados, oficiales));
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDOficiales.getARCHIVO(), true))) {
            bw.write(o.toString());
            bw.newLine();
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        } finally {
            System.out.println("Oficial agregado...");
        }
    }

    public boolean existe(String dni) {
        boolean encontrado = false;
        List<Oficial> lista = leerOficiales();
        for (Oficial o : lista) {
            if (o.getDni().equals(dni)) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    public boolean existePorId(int id) {
        boolean encontrado = false;
        List<Oficial> lista = leerOficiales();
        for (Oficial o : lista) {
            if (o.getId() == id) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    private boolean puedeRegistrarOficial(String dni) {
        return !existe(dni);
    }

    public List<Oficial> leerOficiales() {
        List<Oficial> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BDOficiales.getARCHIVO()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(Oficial.fromString(linea));
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return lista;
    }

    public void actualizarOficial(String dni, Oficial nuevo) {
        if (!validadorDNI.esFormatoValido(nuevo.getDni())) {
            System.out.println("ERROR: El formato del DNI no es válido. Debe tener entre 7 y 8 dígitos.");
            return;
        }

        // Si el DNI cambió, verificar que esté disponible
        if (!dni.equals(nuevo.getDni())) {
            List<Oficial> oficiales = leerOficiales();
            DAOSoldados daoSoldados = new DAOSoldados();
            List<backend.Models.Soldado> soldados = daoSoldados.leerSoldados();

            if (validadorDNI.existeDNI(nuevo.getDni(), soldados, oficiales)) {
                System.out.println("ERROR: El DNI " + nuevo.getDni() + " ya está registrado en el sistema.");
                System.out.println(validadorDNI.obtenerInfoDNI(nuevo.getDni(), soldados, oficiales));
                return;
            }
        }

        List<Oficial> lista = leerOficiales();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDOficiales.getARCHIVO()))) {
            for (Oficial o : lista) {
                if (o.getDni().equals(dni)) {
                    bw.write(nuevo.toString());
                } else {
                    bw.write(o.toString());
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void actualizarOficialPorId(int id, Oficial nuevo) {
        if (!validadorDNI.esFormatoValido(nuevo.getDni())) {
            System.out.println("ERROR: El formato del DNI no es válido. Debe tener entre 7 y 8 dígitos.");
            return;
        }

        // Verificar que el DNI esté disponible para esta actualización
        List<Oficial> oficiales = leerOficiales();
        DAOSoldados daoSoldados = new DAOSoldados();
        List<backend.Models.Soldado> soldados = daoSoldados.leerSoldados();

        if (!validadorDNI.esDNIDisponibleParaActualizacion(nuevo.getDni(), soldados, oficiales, "OFICIAL", id)) {
            System.out.println("ERROR: El DNI " + nuevo.getDni() + " ya está registrado en el sistema.");
            System.out.println(validadorDNI.obtenerInfoDNI(nuevo.getDni(), soldados, oficiales));
            return;
        }

        List<Oficial> lista = leerOficiales();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDOficiales.getARCHIVO()))) {
            for (Oficial o : lista) {
                if (o.getId() == id) {
                    bw.write(nuevo.toString());
                } else {
                    bw.write(o.toString());
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public String buscarPorDni(String dni) {
        String resultado = "Oficial no encontrado...";
        List<Oficial> lista = leerOficiales();
        for (Oficial o : lista) {
            if (o.getDni().equals(dni)) {
                resultado = o.toString();
                break;
            }
        }
        return resultado;
    }

    public Oficial obtenerPorDni(String dni) {
        List<Oficial> lista = leerOficiales();
        for (Oficial o : lista) {
            if (o.getDni().equals(dni)) {
                return o;
            }
        }
        return null;
    }

    public Oficial obtenerPorId(int id) {
        List<Oficial> lista = leerOficiales();
        for (Oficial o : lista) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

    public List<Oficial> obtenerOficialesPorRango(String rango) {
        List<Oficial> oficialesRango = new ArrayList<>();
        List<Oficial> lista = leerOficiales();
        for (Oficial o : lista) {
            if (o.getRango().equalsIgnoreCase(rango)) {
                oficialesRango.add(o);
            }
        }
        return oficialesRango;
    }

    public void eliminarOficial(String dni) {
        List<Oficial> lista = leerOficiales();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDOficiales.getARCHIVO()))) {
            for (Oficial o : lista) {
                if (!o.getDni().equals(dni)) {
                    bw.write(o.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void eliminarOficialPorId(int id) {
        List<Oficial> lista = leerOficiales();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDOficiales.getARCHIVO()))) {
            for (Oficial o : lista) {
                if (o.getId() != id) {
                    bw.write(o.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
