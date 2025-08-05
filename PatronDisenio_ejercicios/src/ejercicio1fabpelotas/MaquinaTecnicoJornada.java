package ejercicio1fabpelotas;

public class MaquinaTecnicoJornada {
    private Maquina maquina;
    private Tecnico tecnico;
    private Jornada jornada;

    public MaquinaTecnicoJornada(Tecnico tecnico, Maquina maquina, Jornada jornada) {
        this.maquina = maquina;
        this.tecnico = tecnico;
        this.jornada = jornada;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public Jornada getJornada() {
        return jornada;
    }
}
