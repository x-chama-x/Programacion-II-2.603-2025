package ejercicio2tiendaprodtec.menu;

public class MenuContexto {
    private EstrategiaMenu estrategia;

    public void setEstrategia(EstrategiaMenu estrategia) {
        this.estrategia = estrategia;
    }

    public void mostrarMenu() {
        estrategia.mostrarMenu();
    }

    public void ejecutarOpcion(int opcion) {
        estrategia.ejecutarOpcion(opcion);
    }

}
