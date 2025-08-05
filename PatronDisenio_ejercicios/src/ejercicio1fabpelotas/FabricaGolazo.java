package ejercicio1fabpelotas;

import ejercicio1fabpelotas.funcionalidades.CapazDeCoser;
import ejercicio1fabpelotas.funcionalidades.CapazDeEmpaquetar;
import ejercicio1fabpelotas.funcionalidades.CapazDeInflar;
import ejercicio1fabpelotas.procesos.Cosido;
import ejercicio1fabpelotas.procesos.Empaquetado;
import ejercicio1fabpelotas.procesos.Inflado;

import java.util.ArrayList;

public class FabricaGolazo {
    private ArrayList<Planta> plantas;
    private ArrayList<Administrador> administradores;
    private ArrayList<Pelota> pelotasFabricadas;
    private ArrayList<MaquinaTecnicoJornada> asignaciones; // las registra el administrador
    private static FabricaGolazo instance;

    private FabricaGolazo() {
        this.plantas = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.pelotasFabricadas = new ArrayList<>();
        this.asignaciones = new ArrayList<>();
        inicializarPlanta();
    }

    public static synchronized FabricaGolazo getInstance() {
        if (instance == null) {
            instance = new FabricaGolazo();
        }
        return instance;
    }

    private void inicializarPlanta() {
        // Crear una planta inicial
        Planta plantaPrincipal = new Planta("Azul", 1000.0);

        // Crear máquinas con sus funcionalidades
        Maquina maquinaCosedora = new Maquina(1, "CoseMaster", "X2000", true, new CapazDeCoser());
        Maquina maquinaInfladora = new Maquina(2, "AirPro", "I3000", true, new CapazDeInflar());
        Maquina maquinaEmpaquetadora = new Maquina(3, "PackPro", "P1000", true, new CapazDeEmpaquetar());

        // Agregar máquinas a la planta
        plantaPrincipal.agregarMaquina(maquinaCosedora);
        plantaPrincipal.agregarMaquina(maquinaInfladora);
        plantaPrincipal.agregarMaquina(maquinaEmpaquetadora);

        // Crear procesos
        ArrayList<Maquina> maquinasCosido = new ArrayList<>();
        maquinasCosido.add(maquinaCosedora);

        Cosido procesoCosido = new Cosido("Cosido", 3, "Cosido de pelota", maquinasCosido);
        Inflado procesoInflado = new Inflado("Inflado", 2, "Inflado de pelota", maquinaInfladora);
        Empaquetado procesoEmpaquetado = new Empaquetado("Empaquetado", 1, "Empaquetado de pelota", maquinaEmpaquetadora);

        // Agregar procesos a la planta
        plantaPrincipal.agregarProceso(procesoCosido);
        plantaPrincipal.agregarProceso(procesoInflado);
        plantaPrincipal.agregarProceso(procesoEmpaquetado);

        // Agregar la planta a la fábrica
        plantas.add(plantaPrincipal);
    }

    public void fabricarPelotas(int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            Pelota pelota = fabricarPelota();
            if (pelota.estaCompleta()) {
                pelotasFabricadas.add(pelota);
            }
        }
    }

    private Pelota fabricarPelota() {
        // Por ahora usamos la primera planta, pero podría elegirse según criterios específicos
        if (!plantas.isEmpty()) {
            return plantas.get(0).fabricarPelota();
        }
        return null;
    }

    public int getCantidadPelotasFabricadas() {
        return pelotasFabricadas.size();
    }

    public void registrarAsignacion(MaquinaTecnicoJornada asignacion) {
        if (asignacion != null) {
            asignaciones.add(asignacion);
        }
    }

    public void removerAsignacion(MaquinaTecnicoJornada asignacion) {
        if (asignacion != null) {
            asignaciones.remove(asignacion);
        }
    }

    public ArrayList<MaquinaTecnicoJornada> getAsignaciones() {
        return asignaciones;
    }

    public void agregarAdministrador(Administrador admin) {
        if (admin != null) {
            administradores.add(admin);
        }
    }

    public ArrayList<Planta> getPlantas() {
        return plantas;
    }
}
