package instituto.frondend;

import instituto.backend.Controllers.MateriasDAO;
import instituto.backend.Models.Materia;
import java.util.Scanner;

public class MenuMateria implements MenuConector {

    private final MateriasDAO dao;
    private int op;
    private final Scanner sc = new Scanner(System.in);

    public MenuMateria() {
        dao = new MateriasDAO();
    }

    public void IniciarMenu() {
        do {
            System.out.println("******* MENÚ MATERIAS *******");
            System.out.println(" 1. Lista materias.");
            System.out.println(" 2. Agregar materia.");
            System.out.println(" 3. Actualizar materia.");
            System.out.println(" 4. Buscar materia.");
            System.out.println(" 5. Eliminar materia.");
            System.out.println(" 0. SALIR.");
            System.out.print(" **** OPCIÓN: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> listar();
                case 2 -> agregar();
                case 3 -> actualizar();
                case 4 -> buscar();
                case 5 -> eliminar();
            }
        } while (op != 0);
    }

    public void listar(){
        System.out.println("LISTA DE MATERIAS...");
        System.out.println("Código:\t\tNombre:");
        for(Materia m: dao.leerMaterias()){
            System.out.println(m.getCodigo()+"\t\t"+ m.getNombre());
        }
    }

    public void agregar(){
        System.out.print("Ingrese código: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        dao.crearMateria(new Materia(codigo, nombre));
    }

    public void actualizar(){
        System.out.print("Ingrese código: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese nuevo nombre: ");
        String nombre = sc.nextLine();
        dao.actualizarMateria(codigo, new Materia(codigo, nombre));
    }

    public void buscar(){
        System.out.print("Ingrese código: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.println(dao.buscarPorCodigo(codigo));
    }

    public void eliminar(){
        System.out.print("Ingrese código: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        dao.eliminarMateria(codigo);
    }
}
