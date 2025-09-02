package instituto;

import instituto.Conections.BD;
import instituto.frondend.Menu;

public class Main {

    public static void main(String[] args) {
        BD.conectar();
        Menu menu = new Menu();
        menu.IniciarMenu();
    }
    
}
