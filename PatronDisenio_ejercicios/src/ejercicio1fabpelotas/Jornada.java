package ejercicio1fabpelotas;

import java.util.Date;

public class Jornada {
    private Date feInicio;
    private Date feFin;
    private String turno;

    public Jornada(Date feInicio, Date feFin, String turno) {
        this.feInicio = feInicio;
        this.feFin = feFin;
        this.turno = turno;
    }

    public String getTurno() {
        return turno;
    }

    public Date getFecInicio() {
        return feInicio;
    }

    public Date getFecFin() {
        return feFin;
    }
}
