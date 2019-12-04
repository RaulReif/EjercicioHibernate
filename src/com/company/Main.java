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
                        "7 - Actualizar un equipo\n" +
                        "8 - Actualizar un ciclista\n" +
                        "9 - Actualizar el palmare de un ciclista\n" +
                        "10 - Eliminar un ciclista\n" +
                        "11 - Eliminar un equipo\n" +
                        "12 - Salir\n" +
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
                        List<Equipo> listaEquipos4 = session.createQuery("SELECT e FROM equipo e WHERE id = " + id4, Equipo.class).getResultList();
                        List<Ciclista> listaCiclistas4 = listaEquipos4.get(0).getCiclistas();
                        mostrarCiclistas(listaCiclistas4);
                        break;
                    case 5:
                        mostrarEquipos(session.createQuery("SELECT e FROM Equipo e", Equipo.class).getResultList());
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    default:
                        System.out.println("No has escogido una opción valida");
                }
                session.close();


            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Introduce un número valido correspondiente con una de las opciones del menu");
            }
        } while (opcion != 5);

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

    private static void mostrarCiclistas(List<Ciclista> lista){
        for (int i = 0; i < lista.size(); i++) {
            Ciclista c = lista.get(i);
            System.out.println("Dorsal: " + c.getDorsal());
            System.out.println("Nombre: " + c.getNombre());
            System.out.println("Apellidos: " + c.getApellidos() + "\n");
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

    private static void mostrarEquipo(Equipo e){
        System.out.println("-Nombre: " + e.getNombre());
        System.out.println("-Patrocinador: " + e.getPatrocinador());
        System.out.println("-Número de ciclistas: " + e.getCiclistas().size() + "\n");
    }

    private static void mostrarEquipos(List<Equipo> lista){
        for (int i = 0; i < lista.size(); i++) {
            mostrarEquipo(lista.get(i));
        }
    }
}
