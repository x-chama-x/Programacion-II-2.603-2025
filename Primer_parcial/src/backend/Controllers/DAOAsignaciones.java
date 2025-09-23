package backend.Controllers;

import Conections.BDAsignaciones;
import backend.Models.Asignacion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAOAsignaciones {

    public void crearAsignacion(Asignacion a) {
        if (puedeRegistrarAsignacion(a.getOficialId(), a.getSoldadoId())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDAsignaciones.getARCHIVO(), true))) {
                bw.write(a.toString());
                bw.newLine();
            } catch (IOException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            } finally {
                System.out.println("Asignación agregada...");
            }
        } else {
            System.out.println("ERROR: Ya existe una asignación entre el oficial " + a.getOficialId() + " y el soldado " + a.getSoldadoId());
        }
    }

    public boolean existe(int oficialId, int soldadoId) {
        boolean encontrado = false;
        List<Asignacion> lista = leerAsignaciones();
        for (Asignacion a : lista) {
            if (a.getOficialId() == oficialId && a.getSoldadoId() == soldadoId) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    public boolean existeAsignacionPorSoldado(int soldadoId) {
        boolean encontrado = false;
        List<Asignacion> lista = leerAsignaciones();
        for (Asignacion a : lista) {
            if (a.getSoldadoId() == soldadoId) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    public boolean existeAsignacionPorOficial(int oficialId) {
        boolean encontrado = false;
        List<Asignacion> lista = leerAsignaciones();
        for (Asignacion a : lista) {
            if (a.getOficialId() == oficialId) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    private boolean puedeRegistrarAsignacion(int oficialId, int soldadoId) {
        return !existe(oficialId, soldadoId);
    }

    public List<Asignacion> leerAsignaciones() {
        List<Asignacion> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BDAsignaciones.getARCHIVO()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(Asignacion.fromString(linea));
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return lista;
    }

    public void actualizarAsignacion(int oficialIdAnterior, int soldadoId, Asignacion nueva) {
        List<Asignacion> lista = leerAsignaciones();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDAsignaciones.getARCHIVO()))) {
            for (Asignacion a : lista) {
                if (a.getOficialId() == oficialIdAnterior && a.getSoldadoId() == soldadoId) {
                    bw.write(nueva.toString());
                } else {
                    bw.write(a.toString());
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public List<Asignacion> obtenerAsignacionesPorOficial(int oficialId) {
        List<Asignacion> asignacionesOficial = new ArrayList<>();
        List<Asignacion> lista = leerAsignaciones();
        for (Asignacion a : lista) {
            if (a.getOficialId() == oficialId) {
                asignacionesOficial.add(a);
            }
        }
        return asignacionesOficial;
    }

    public Asignacion obtenerAsignacionPorSoldado(int soldadoId) {
        List<Asignacion> lista = leerAsignaciones();
        for (Asignacion a : lista) {
            if (a.getSoldadoId() == soldadoId) {
                return a;
            }
        }
        return null;
    }

    public List<Integer> obtenerSoldadosPorOficial(int oficialId) {
        List<Integer> soldados = new ArrayList<>();
        List<Asignacion> lista = leerAsignaciones();
        for (Asignacion a : lista) {
            if (a.getOficialId() == oficialId) {
                soldados.add(a.getSoldadoId());
            }
        }
        return soldados;
    }

    public Integer obtenerOficialPorSoldado(int soldadoId) {
        List<Asignacion> lista = leerAsignaciones();
        for (Asignacion a : lista) {
            if (a.getSoldadoId() == soldadoId) {
                return a.getOficialId();
            }
        }
        return null;
    }

    public void eliminarAsignacion(int oficialId, int soldadoId) {
        List<Asignacion> lista = leerAsignaciones();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDAsignaciones.getARCHIVO()))) {
            for (Asignacion a : lista) {
                if (!(a.getOficialId() == oficialId && a.getSoldadoId() == soldadoId)) {
                    bw.write(a.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void eliminarAsignacionesPorSoldado(int soldadoId) {
        List<Asignacion> lista = leerAsignaciones();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDAsignaciones.getARCHIVO()))) {
            for (Asignacion a : lista) {
                if (a.getSoldadoId() != soldadoId) {
                    bw.write(a.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void eliminarAsignacionesPorOficial(int oficialId) {
        List<Asignacion> lista = leerAsignaciones();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDAsignaciones.getARCHIVO()))) {
            for (Asignacion a : lista) {
                if (a.getOficialId() != oficialId) {
                    bw.write(a.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public int contarSoldadosPorOficial(int oficialId) {
        int contador = 0;
        List<Asignacion> lista = leerAsignaciones();
        for (Asignacion a : lista) {
            if (a.getOficialId() == oficialId) {
                contador++;
            }
        }
        return contador;
    }
}
