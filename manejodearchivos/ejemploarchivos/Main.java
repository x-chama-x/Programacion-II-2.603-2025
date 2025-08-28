package ejemploarchivos;
public class Main {
    public static void main(String[] args) {
        BD.inicializarBD();
        Menu menu = new Menu();
        menu.iniciarMenu();
    }
}
