package controllers;

import models.Materia;
import models.MateriaDAO;
import views.VistaMateria;
import java.util.Scanner;

public class ControladorMateria {
    private MateriaDAO dao;
    private VistaMateria vista;

    public ControladorMateria() {
        dao = new MateriaDAO();
        vista = new VistaMateria();
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = vista.menu();
            Scanner sc = vista.getScanner();
            switch (opcion) {
                case 1 -> {
                    sc.nextLine();
                    System.out.print("Nombre Materia: ");
                    String nombre = sc.nextLine();
                    dao.insertar(new Materia(nombre));
                }
                case 2 -> dao.listar().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Código a actualizar: ");
                    int codigo = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo Nombre: ");
                    String nombre = sc.nextLine();
                    dao.actualizar(new Materia(codigo, nombre));
                }
                case 4 -> {
                    System.out.print("Código a eliminar: ");
                    int codigo = sc.nextInt();
                    dao.eliminar(codigo);
                }
            }
        } while (opcion != 0);
    }
}