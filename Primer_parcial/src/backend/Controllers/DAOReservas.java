package backend.Controllers;

import Conections.BDReservas;
import backend.Models.Reserva;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAOReservas {

    public void crearReserva(Reserva r) {
        if (puedeRegistrarReserva(r.getSoldadoId(), r.getCodigoDeCuartel())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDReservas.getARCHIVO(), true))) {
                bw.write(r.toString());
                bw.newLine();
            } catch (IOException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            } finally {
                System.out.println("Reserva agregada...");
            }
        } else {
            System.out.println("ERROR: Ya existe una reserva para el soldado " + r.getSoldadoId() + " en el cuartel " + r.getCodigoDeCuartel());
        }
    }

    public boolean existe(int soldadoId, String codigoDeCuartel) {
        boolean encontrado = false;
        List<Reserva> lista = leerReservas();
        for (Reserva r : lista) {
            if (r.getSoldadoId() == soldadoId && r.getCodigoDeCuartel().equals(codigoDeCuartel)) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    public boolean existeReservaPorSoldado(int soldadoId) {
        boolean encontrado = false;
        List<Reserva> lista = leerReservas();
        for (Reserva r : lista) {
            if (r.getSoldadoId() == soldadoId) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    private boolean puedeRegistrarReserva(int soldadoId, String codigoDeCuartel) {
        return !existe(soldadoId, codigoDeCuartel);
    }

    public List<Reserva> leerReservas() {
        List<Reserva> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BDReservas.getARCHIVO()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(Reserva.fromString(linea));
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return lista;
    }

    public void actualizarReserva(int soldadoId, String codigoDeCuartelAnterior, Reserva nueva) {
        List<Reserva> lista = leerReservas();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDReservas.getARCHIVO()))) {
            for (Reserva r : lista) {
                if (r.getSoldadoId() == soldadoId && r.getCodigoDeCuartel().equals(codigoDeCuartelAnterior)) {
                    bw.write(nueva.toString());
                } else {
                    bw.write(r.toString());
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public List<Reserva> obtenerReservasPorCuartel(String codigoDeCuartel) {
        List<Reserva> reservasCuartel = new ArrayList<>();
        List<Reserva> lista = leerReservas();
        for (Reserva r : lista) {
            if (r.getCodigoDeCuartel().equals(codigoDeCuartel)) {
                reservasCuartel.add(r);
            }
        }
        return reservasCuartel;
    }

    public Reserva obtenerReservaPorSoldado(int soldadoId) {
        List<Reserva> lista = leerReservas();
        for (Reserva r : lista) {
            if (r.getSoldadoId() == soldadoId) {
                return r;
            }
        }
        return null;
    }

    public List<Reserva> obtenerReservasPorSoldado(int soldadoId) {
        List<Reserva> reservasSoldado = new ArrayList<>();
        List<Reserva> lista = leerReservas();
        for (Reserva r : lista) {
            if (r.getSoldadoId() == soldadoId) {
                reservasSoldado.add(r);
            }
        }
        return reservasSoldado;
    }

    public void eliminarReserva(int soldadoId, String codigoDeCuartel) {
        List<Reserva> lista = leerReservas();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDReservas.getARCHIVO()))) {
            for (Reserva r : lista) {
                if (!(r.getSoldadoId() == soldadoId && r.getCodigoDeCuartel().equals(codigoDeCuartel))) {
                    bw.write(r.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void eliminarReservasPorSoldado(int soldadoId) {
        List<Reserva> lista = leerReservas();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDReservas.getARCHIVO()))) {
            for (Reserva r : lista) {
                if (r.getSoldadoId() != soldadoId) {
                    bw.write(r.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void eliminarReservasPorCuartel(String codigoDeCuartel) {
        List<Reserva> lista = leerReservas();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDReservas.getARCHIVO()))) {
            for (Reserva r : lista) {
                if (!r.getCodigoDeCuartel().equals(codigoDeCuartel)) {
                    bw.write(r.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
