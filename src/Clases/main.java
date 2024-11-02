package Clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author raulp
 */
public class main {
//adri maricon trabaja

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int opcion, talla;
        long inicio, fin, Tejecucion;//calculo del tiempo de los algoritmos
        boolean peorcaso = false;
        Scanner sc = new Scanner(System.in);
        Punto p = new Punto();
        PuntosMin pmin = new PuntosMin();
        Algoritmos a = new Algoritmos();
        Menus m = new Menus();
        ArrayList<Punto> puntos = new ArrayList<>();
        ArrayList<String> ficheros = new ArrayList<>();
        Mostrar mo = new Mostrar();
        Lectura lec1 = new Lectura();

        // ArrayList<Punto> puntosaleatorios = null;
        Menus menu = new Menus();

        // pmin = a.DyVe(puntos, 0, puntos.size()-1);
        //1 System.out.println(a.getCont());
        //System.out.println(pmin.getA() + "  " + pmin.getB() + "  " + pmin.getDistancia());
        do {
            opcion = menu.menuPrincipal(peorcaso);

            while (opcion < 0 || opcion > 8) {
                System.out.println("La opcion debe estar entre 0 y 8, intentalo de nuevo");
                opcion = sc.nextInt();
            }
            switch (opcion) {
                case 1 -> {
                    ficheros = mo.RellenarArrayFicheros();
                    for (int j = 0; j < ficheros.size(); j++) {
                        System.out.println("");
                        System.out.println("Nombre de fichero: " + ficheros.get(j));
                        System.out.println("Estrategia        Punto1          Punto2          distancia                          calculadas          tiempo");
                        for (int i = 1; i < 5; i++) {
                            mo.MostrarExhaustiva(ficheros.get(j), i);

                        }
                    }

                }
                case 2 -> {
                    System.out.println("Introduce la talla deseada: ");
                    talla = sc.nextInt();
                    p.rellenarPuntos(puntos, talla, peorcaso);
                    lec1.EscribirTSP(puntos, talla + ".tsp");

                    mo.MostrarAleatorio(puntos, talla);

                }
                case 3 -> {
                    int opcion3 = -1, i;
                    while (opcion3 != 0) {
                        i = 500;
                        opcion3 = menu.menu3();

                        switch (opcion3) {
                            case 1:

                                System.out.println("Talla           Tiempo");
                                while (i <= 5000) {
                                    puntos.clear();
                                    p.rellenarPuntos(puntos, i, peorcaso);
                                    inicio = System.nanoTime();
                                    mo.Apartado3(i, puntos, 1);
                                    fin = System.nanoTime();
                                    Tejecucion = fin - inicio;
                                    System.out.printf("%d          %.9f%n", i, Tejecucion / 1000000.0);
                                    lec1.EscribirDat(1, i, Tejecucion);
                                    i += 500;

                                }

                                break;
                            case 2:

                                System.out.println("Talla           Tiempo");
                                while (i <= 5000) {
                                    puntos.clear();
                                    p.rellenarPuntos(puntos, i, peorcaso);
                                    inicio = System.nanoTime();
                                    mo.Apartado3(i, puntos, 2);
                                    fin = System.nanoTime();
                                    Tejecucion = fin - inicio;
                                    System.out.printf("%d          %.9f%n", i, Tejecucion / 1000000.0);
                                    lec1.EscribirDat(2, i, Tejecucion);
                                    i += 500;

                                }

                                i = 500;
                                break;
                            case 3:

                                System.out.println("Talla           Tiempo");
                                while (i <= 5000) {
                                    puntos.clear();
                                    p.rellenarPuntos(puntos, i, peorcaso);
                                    inicio = System.nanoTime();
                                    mo.Apartado3(i, puntos, 3);
                                    fin = System.nanoTime();
                                    Tejecucion = fin - inicio;
                                    System.out.printf("%d          %.9f%n", i, Tejecucion / 1000000.0);
                                    lec1.EscribirDat(3, i, Tejecucion);
                                    i += 500;

                                }

                                break;
                            case 4:

                                System.out.println("Talla           Tiempo");
                                while (i <= 5000) {
                                    puntos.clear();
                                    p.rellenarPuntos(puntos, i, peorcaso);
                                    inicio = System.nanoTime();
                                    mo.Apartado3(i, puntos, 4);
                                    fin = System.nanoTime();
                                    Tejecucion = fin - inicio;
                                    System.out.printf("%d          %.9f%n", i, Tejecucion / 1000000.0);
                                    lec1.EscribirDat(4, i, Tejecucion);
                                    i += 500;

                                }

                                break;
                            case 0:

                                break;
                            default:
                                throw new AssertionError();
                        }
                    }
                }
                case 4 -> {
                    int est1, est2, i = 500;
                    long Tejecucion1, Tejecucion2;
                    Algoritmos a1 = new Algoritmos();
                    Algoritmos a2 = new Algoritmos();
                    est1 = menu.menu3();
                    est2 = menu.menu3();

                    System.out.println("                Exhaustivo          ExhaustivoPoda          Exhaustivo          ExhaustivoPoda");
                    System.out.println("Talla           Tiempo              Tiempo                 Distacias           Distancias");
                    while (i <= 5000) {
                        puntos.clear();
                        p.rellenarPuntos(puntos, i, peorcaso);
                        inicio = System.nanoTime();
                        a1 = mo.Apartado3(i, puntos, est1);
                        fin = System.nanoTime();
                        Tejecucion1 = fin - inicio;
                        inicio = System.nanoTime();
                        a2 = mo.Apartado3(i, puntos, est2);
                        fin = System.nanoTime();
                        Tejecucion2 = fin - inicio;
                        System.out.printf("%d              %.9f          %.9f       %d      %d%n", i, Tejecucion1 / 1000000.0, Tejecucion2 / 1000000.0, a1.getCont(), a2.getCont());
                        lec1.EscribirDat(est1, i, (long) (Tejecucion1 / 1000000.0));
                        lec1.EscribirDat(est2, i, (long) (Tejecucion2 / 1000000.0));
                        i += 500;

                    }

                }
                case 5 -> {
                    int i = 500;
                    System.out.println("                Exhaustivo          ExhaustivoPoda         DyVe                DyVe Mejorado");
                    System.out.println("Talla           Tiempo              Tiempo                 Tiempo              Tiempo");
                    while (i <= 5000) {
                        puntos.clear();
                        p.rellenarPuntos(puntos, i, peorcaso);
                        mo.TodasStrat(peorcaso, puntos, i);
                        i += 500;
                    }
                }
                case 6 -> {
                    if (peorcaso) {
                        peorcaso = false;
                    } else {
                        peorcaso = true;
                    }
                }
                case 7 -> {

                    System.out.println("Introduce la talla del tsp");
                    talla = sc.nextInt();
                    puntos.clear();
                    p.rellenarPuntos(puntos, talla, peorcaso);

                    lec1.EscribirTSP(puntos, "Dataset(" + talla + ")");
                }
                case 8 -> {
                    String archivo;
                    int i = 500;
                    System.out.println("Introduce el archivo: ");
                    archivo = sc.next();
                    Lectura lecturatsp = new Lectura(archivo + ".tsp");
                    puntos = lecturatsp.getPuntos();
                    System.out.println("Nombre de fichero: " + archivo + ".tsp");
                    System.out.println("Estrategia        Punto1          Punto2          distancia                          calculadas          tiempo");
                    for (int x = 1; x < 5; x++) {
                        mo.MostrarExhaustiva(archivo + ".tsp", x);

                    }

                }
                case 0 -> {
                }

                default ->
                    throw new AssertionError();
            }
        } while (opcion != 0);

    }
}
