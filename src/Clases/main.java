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
                    ficheros=mo.RellenarArrayFicheros();
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
                    Lectura lec1 = new Lectura();
                    p.rellenarPuntos(puntos, talla, true);
                    lec1.EscribirTSP(puntos, talla + ".tsp");

                    mo.MostrarAleatorio(puntos, talla);

                }
                case 3 -> {
                    int opcion3 = -1,i;
                    while(opcion3!=0){
                        i=500;
                    opcion3 = menu.menu3();
                    Lectura lec1 = new Lectura();

                    switch (opcion3) {
                        case 1:
                            
                            System.out.println("Talla           Tiempo");
                            while (i <= 5000) {
                                mo.Apartado3(i, puntos, 1);
                                i += 500;
                            }
                            
                            break;
                        case 2:
                            
                            System.out.println("Talla           Tiempo");
                            while (i <= 5000) {
                                
                                mo.Apartado3(i, puntos, 2);
                                i += 500;
                                
                            }
                            
                            i=500;
                            break;
                        case 3:
                            
                            System.out.println("Talla           Tiempo");
                            while (i <= 5000) {
                                mo.Apartado3(i, puntos, 3);
                                i += 500;
                            }
                           
                            break;
                        case 4:
                           
                            System.out.println("Talla           Tiempo");
                            while (i <= 5000) {
                                mo.Apartado3(i, puntos, 4);
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
                }
                case 5 -> {
                }
                case 6 -> {
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
