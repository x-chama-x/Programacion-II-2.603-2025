package ejercicio1fabpelotas;

public class Pelota {
    private boolean cosida;
    private boolean inflada;
    private boolean empaquetada;

    public Pelota() {
        this.cosida = false;
        this.inflada = false;
        this.empaquetada = false;
    }

    public void setCosida(boolean cosida) {
        this.cosida = cosida;
    }

    public void setInflada(boolean inflada) {
        this.inflada = inflada;
    }

    public void setEmpaquetada(boolean empaquetada) {
        this.empaquetada = empaquetada;
    }

    public boolean estaCompleta() {
        return cosida && inflada && empaquetada;
    }
}
