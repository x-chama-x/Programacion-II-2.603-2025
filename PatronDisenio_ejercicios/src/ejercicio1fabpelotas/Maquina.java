package ejercicio1fabpelotas;

import ejercicio1fabpelotas.funcionalidades.Funcionalidad;

public class Maquina {
    private Integer ns;
    private String marca;
    private String modelo;
    private boolean estado; // true: en servicio, false: fuera de servicio
    private Funcionalidad funcionalidad;
    private Maquina maquinaReemplazo;

    public Maquina(Integer ns, String marca, String modelo, boolean estado, Funcionalidad funcionalidad) {
        this.ns = ns;
        this.marca = marca;
        this.modelo = modelo;
        this.estado = estado;
        this.funcionalidad = funcionalidad;
        this.maquinaReemplazo = null;
    }

    public void ejecutarFuncion(Pelota pelota) {
        if (estado) { // Solo si está en servicio
            funcionalidad.ejecutarFuncion(pelota); // se delega la ejecución a la funcionalidad
        } else if (maquinaReemplazo != null) {
            System.out.println("Máquina " + ns + " fuera de servicio. Utilizando máquina de reemplazo " + maquinaReemplazo.getNs());
            maquinaReemplazo.ejecutarFuncion(pelota);
        } else {
            System.out.println("La máquina está fuera de servicio y no tiene reemplazo asignado");
        }
    }

    public boolean esOperativa() {
        return estado;
    }

    public Integer getNs() {
        return ns;
    }

    public Funcionalidad getFuncionalidad() {
        return funcionalidad;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void asignarReemplazo(Maquina maquinaReemplazo) {
        if (maquinaReemplazo.esOperativa() &&
            maquinaReemplazo.getFuncionalidad().getClass().equals(this.funcionalidad.getClass())) {
            this.maquinaReemplazo = maquinaReemplazo;
            System.out.println("Máquina " + maquinaReemplazo.getNs() + " asignada como reemplazo de " + this.ns);
        } else {
            System.out.println("La máquina de reemplazo no es compatible o no está operativa");
        }
    }

    public void quitarReemplazo() {
        this.maquinaReemplazo = null;
    }

    public Maquina getMaquinaReemplazo() {
        return maquinaReemplazo;
    }
}
