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
        long inicio, fin, Tejecucion;//calculo del tiempo de los algoritmos
        Scanner sc = new Scanner(System.in);
        Punto p = new Punto();
        Algoritmos a = new Algoritmos();
        Menus m = new Menus();
        PuntosMin pmin = new PuntosMin();
        ArrayList<Punto> puntos = new ArrayList<>();
        ArrayList<String> ficheros = new ArrayList<>();
        Mostrar mo = new Mostrar();
        Lectura lec1 = new Lectura();
        boolean peorcaso=false;
        // ArrayList<Punto> puntosaleatorios = null;

        Menus menu = new Menus();
        int opcion, talla;

        // pmin = a.DyVe(puntos, 0, puntos.size()-1);
        //1 System.out.println(a.getCont());
        //System.out.println(pmin.getA() + "  " + pmin.getB() + "  " + pmin.getDistancia());
        do {
            opcion = menu.menuPrincipal();

            while (opcion < 0 || opcion > 8) {
                System.out.println("La opcion debe estar entre 0 y 8, intentalo de nuevo");
                opcion = sc.nextInt();
            }
            switch (opcion) {
                case 1 -> {

                    /*ficheros.add("berlin52.tsp");
                    ficheros.add("ch130.tsp");
                    ficheros.add("ch150.tsp");
                    ficheros.add("d493.tsp");
                    ficheros.add("d657.tsp");*/
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
                    int i=500;
                   
                    long Tejecucion1, Tejecucion2,Tejecucion3, Tejecucion4;
                    System.out.println("                Exhaustivo          ExhaustivoPoda         DyVe                DyVe Mejorado");
                    System.out.println("Talla           Tiempo              Tiempo                 Tiempo              Tiempo");
                    
                     while (i <= 5000) {
                        puntos.clear();
                        p.rellenarPuntos(puntos, i, peorcaso);
                        Tejecucion1=mo.aisdh(i, puntos, 1);
                        Tejecucion2=mo.aisdh(i, puntos, 2);
                        Tejecucion3=mo.aisdh(i, puntos, 3);
                        Tejecucion4=mo.aisdh(i, puntos, 4);                                            
                        System.out.printf("%d              %.9f          %.9f       %.9f        %.9f%n", i, Tejecucion1 / 1000000.0, Tejecucion2 / 1000000.0, Tejecucion3 / 1000000.0, Tejecucion4 / 1000000.0);
                        lec1.EscribirDat(0, i, (long) (Tejecucion1 / 1000000.0));
                        lec1.EscribirDat(1, i, (long) (Tejecucion2 / 1000000.0));
                        lec1.EscribirDat(2, i, (long) (Tejecucion3 / 1000000.0));
                        lec1.EscribirDat(3, i, (long) (Tejecucion4 / 1000000.0));
                        i += 500;

                    }

                }
                case 6 -> {
                    if(peorcaso){
                        peorcaso=false;
                    }else{
                        peorcaso=true;
                    }
                }
                case 7 -> {
                }
                case 8 -> {
                }
                case 0 -> {
                }

                default ->
                    throw new AssertionError();
            }
        } while (opcion != 0);

    }
}
