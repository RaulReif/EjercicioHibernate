package com.company;

import com.company.models.Ciclista;
import com.company.models.Equipo;
import com.company.models.Palmares;
import com.sun.istack.Nullable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {


        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        int opcion = 0;

        do {

            try {
                System.out.println("******************** MENU ********************\n" +
                        "1 - Crear ciclista\n" +
                        "2 - Crear equipo\n" +
                        "3 - Consultar equipo\n" +
                        "4 - Consultar todos los ciclistas de un equipo\n" +
                        "5 - Consultar todos los ciclistas de un equipo con su palmare\n" +
                        "6 - Consultar un ciclista\n" +
                        "7 - Consultar un ciclista con su palmare\n" +
                        "8 - Actualizar un equipo\n" +
                        "9 - Actualizar un ciclista\n" +
                        "10 - Actualizar el palmare de un ciclista\n" +
                        "11 - Eliminar un ciclista\n" +
                        "12 - Eliminar un equipo\n" +
                        "13 - Salir\n" +
                        "*********************************************");


                opcion = Integer.parseInt(br.readLine());

                Session session = sessionFactory.openSession();
                switch (opcion) {
                    case 1:
                        session.beginTransaction();
                        session.save(crearCiclista(session));
                        session.getTransaction().commit();
                        break;
                    case 2:
                        session.beginTransaction();
                        session.save(crearEquipo());
                        session.getTransaction().commit();
                        break;
                    case 3:
                        System.out.print("Dime el id del equipo que quieras consultar: ");
                        mostrarEquipo(session.get(Equipo.class, Integer.parseInt(br.readLine())));
                        break;
                    case 4:
                        System.out.print("Dime el id del equipo: ");
                        int id4 = Integer.parseInt(br.readLine());
                        Equipo e4 = session.get(Equipo.class, id4);
                        List<Ciclista> listaCiclistas4 = e4.getCiclistas();
                        mostrarCiclistas(listaCiclistas4);
                        break;
                    case 5:
                        System.out.print("Dime el id del equipo: ");
                        int id5 = Integer.parseInt(br.readLine());
                        Equipo e5 = session.get(Equipo.class, id5);
                        List<Ciclista> listaCiclistas5 = e5.getCiclistas();
                        mostrarCiclistasConPalmares(listaCiclistas5);
                        break;
                    case 6:
                        System.out.print("Dime el dorsal del ciclista:");
                        mostrarCiclista(session.get(Ciclista.class, br.readLine()));
                        break;
                    case 7:
                        System.out.print("Dime el dorsal del ciclista:");
                        mostrarCiclistaConPalmare(session.get(Ciclista.class, br.readLine()));
                        break;
                    case 8:
                        System.out.print("Dime el id del equipo a actualizar:");
                        session.beginTransaction();
                        session.update(actualizarEquipo(Integer.parseInt(br.readLine())));
                        session.getTransaction().commit();
                        break;
                    case 9:
                        System.out.print("Dime el dorsal del ciclista a actualizar: ");
                        String dorsal8 = br.readLine();
                        System.out.print("Nombre: ");
                        String nombre8 = br.readLine();
                        System.out.print("Apellidos: ");
                        String apellidos8 = br.readLine();
                        Ciclista c8 = session.get(Ciclista.class, dorsal8);
                        c8.setNombre(nombre8);
                        c8.setApellidos(apellidos8);
                        session.beginTransaction();
                        session.update(c8);
                        session.getTransaction().commit();
                        break;
                    case 10:
                        System.out.println("Dime el dorsal del ciclista");
                        String id10 = br.readLine();
                        System.out.print("Número de vueltas:");
                        int numero_vueltas = Integer.parseInt(br.readLine());
                        System.out.print("Número de maillots:");
                        int numero_maillots = Integer.parseInt(br.readLine());
                        session.beginTransaction();
                        session.update(new Palmares(id10, numero_vueltas, numero_maillots));
                        session.getTransaction().commit();
                        break;
                    case 11:
                        System.out.println("Dime el dorsal del ciclista a eliminar");
                        session.beginTransaction();
                        session.delete(new Ciclista(br.readLine()));
                        session.getTransaction().commit();
                        break;
                    case 12:
                        System.out.println("Dime el id del equipo a eliminar");
                        session.beginTransaction();
                        session.delete(new Equipo(Integer.parseInt(br.readLine())));
                        session.getTransaction().commit();
                        break;
                    default:
                        System.out.println("No has escogido una opción valida");
                }
                session.close();


            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Introduce un número valido correspondiente con una de las opciones del menu");
            }
        } while (opcion != 13);

    }

    @Nullable
    private static Ciclista crearCiclista(Session session) {
        try {
            System.out.print("Dorsal: ");
            String dorsal = br.readLine();
            System.out.print("Nombre: ");
            String nombre = br.readLine();
            System.out.print("Apellidos: ");
            String apellidos = br.readLine();
            Palmares palmares = crearPalmares(dorsal);
            System.out.print("Dime el id del equipo del ciclista: ");
            int id_equipo = Integer.parseInt(br.readLine());
            Equipo equipo = session.get(Equipo.class, id_equipo);
            return new Ciclista(dorsal, nombre, apellidos, palmares, equipo);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void mostrarCiclista(Ciclista c) {
        System.out.println("\nDorsal: " + c.getDorsal());
        System.out.println("Nombre: " + c.getNombre());
        System.out.println("Apellidos: " + c.getApellidos() + "\n");
    }

    private static void mostrarCiclistas(List<Ciclista> lista) {
        for (int i = 0; i < lista.size(); i++) {
            mostrarCiclista(lista.get(i));
        }
    }

    private static void mostrarCiclistaConPalmare(Ciclista c) {
        System.out.println("\n-Dorsal: " + c.getDorsal());
        System.out.println("-Nombre: " + c.getNombre());
        System.out.println("-Apellidos: " + c.getApellidos());
        Palmares palmares = c.getPalmares();
        System.out.println("Datos de palmares ->\n" +
                "-Número de vueltas: " + palmares.getNumero_vueltas() + "\n" +
                "-Número de maillots: " + palmares.getNumero_maillots());
    }

    private static void mostrarCiclistasConPalmares(List<Ciclista> lista) {
        for (int i = 0; i < lista.size(); i++) {
            mostrarCiclistaConPalmare(lista.get(i));
        }
    }

    @Nullable
    private static Palmares crearPalmares(String id) {
        try {
            System.out.println("-- Datos del palamares --");
            System.out.print("Número de vueltas: ");
            int numero_vueltas = Integer.parseInt(br.readLine());
            System.out.print("Número de maillots: ");
            int numero_maillots = Integer.parseInt(br.readLine());
            return new Palmares(id, numero_vueltas, numero_maillots);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    private static Equipo crearEquipo() {
        try {
            System.out.print("Id: ");
            int id = Integer.parseInt(br.readLine());
            System.out.print("Nombre: ");
            String nombre = br.readLine();
            System.out.print("Patrocinador: ");
            String patrocinador = br.readLine();
            return new Equipo(id, nombre, patrocinador);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    private static Equipo actualizarEquipo(int id) {
        try {
            System.out.print("Nombre: ");
            String nombre = br.readLine();
            System.out.print("Patrocinador: ");
            String patrocinador = br.readLine();
            return new Equipo(id, nombre, patrocinador);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void mostrarEquipo(Equipo e) {
        System.out.println("\n-Nombre: " + e.getNombre());
        System.out.println("-Patrocinador: " + e.getPatrocinador());
        System.out.println("-Número de ciclistas: " + e.getCiclistas().size() + "\n");
    }

    private static void mostrarEquipos(List<Equipo> lista) {
        for (int i = 0; i < lista.size(); i++) {
            mostrarEquipo(lista.get(i));
        }
    }
}
