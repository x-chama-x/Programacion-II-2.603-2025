import Conections.BD;
import frontend.Menu;

public class Main {
    public static void main(String[] args) {
        BD.conectar();
        Menu menu = new Menu();
        menu.IniciarMenu();
    }
}