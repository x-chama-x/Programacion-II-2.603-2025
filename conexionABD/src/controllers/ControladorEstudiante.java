package controllers;

import models.Estudiante;
import models.EstudianteDAO;
import views.VistaEstudiante;
import java.time.LocalDate;
import java.util.Scanner;

public class ControladorEstudiante {
    private EstudianteDAO dao;
    private VistaEstudiante vista;

    public ControladorEstudiante() {
        dao = new EstudianteDAO();
        vista = new VistaEstudiante();
    }

    public void iniciar() {
        int opcion;
        do {
            opcion = vista.menu();
            Scanner sc = vista.getScanner();
            switch (opcion) {
                case 1 -> {
                    sc.nextLine();
                    System.out.print("DNI: ");
                    int dni = sc.nextInt(); sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Fecha Nacimiento (YYYY-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(sc.nextLine());
                    dao.insertar(new Estudiante(dni, nombre, apellido, fecha));
                }
                case 2 -> dao.listar().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Legajo a actualizar: ");
                    int legajo = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo DNI: ");
                    int dni = sc.nextInt(); sc.nextLine();
                    System.out.print("Nuevo Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Nuevo Apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Nueva Fecha (YYYY-MM-DD): ");
                    LocalDate fecha = LocalDate.parse(sc.nextLine());
                    dao.actualizar(new Estudiante(legajo, dni, nombre, apellido, fecha));
                }
                case 4 -> {
                    System.out.print("Legajo a eliminar: ");
                    int legajo = sc.nextInt();
                    dao.eliminar(legajo);
                }
                case 5 -> {
                    System.out.print("Ingrese legajo a buscar: ");
                    int legajo = sc.nextInt();
                    Estudiante estudiante = dao.buscarPorLegajo(legajo);
                    if (estudiante != null) {
                        System.out.println("Estudiante encontrado: " + estudiante);
                    } else {
                        System.out.println("Estudiante no encontrado.");
                    }
                }
            }
        } while (opcion != 0);
    }
}
