/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adria
 */
public class Mostrar {

    List<Punto> puntos;
    long inicio, fin, Tejecucion;
    Punto p = new Punto();
    Algoritmos a = new Algoritmos();
    PuntosMin pmin = new PuntosMin();
    ArrayList<String> estrategias = new ArrayList<>();
    ArrayList<String> ficheros = new ArrayList<>();
    Lectura lec = new Lectura();

    public Mostrar() {

        /*   ficheros.add("berlin52.tsp");
        ficheros.add("ch130.tsp");
        ficheros.add("ch150.tsp");
        ficheros.add("d493.tsp");
        ficheros.add("d657.tsp");*/
        estrategias.add("Exhaustivo");
        estrategias.add("ExhaustivoPoda.tsp");
        estrategias.add("DyVe.tsp");
        estrategias.add("DyVeMejorado.tsp");
    }

    public void MostrarAleatorio(List<Punto> puntos, int talla) {
        inicio = System.nanoTime();
        pmin = a.BusquedaExahustiva(puntos, 0, puntos.size() - 1);
        fin = System.nanoTime();
        Tejecucion = fin - inicio;
        System.out.println("Nombre de fichero: DataSet(" + talla + ")");
        System.out.println("Estrategia        Punto1          Punto2          distancia           calculadas          tiempo");
        System.out.println("Exhaustivo        " + pmin.getA().getId() + "              " + pmin.getB().getId() + "               " + pmin.getDistancia()
                + "               " + a.getCont() + "                 " + Tejecucion / 1_000_000.0);

        a.setCont();
        inicio = System.nanoTime();
        pmin = a.BusquedaPoda(puntos, 0, puntos.size() - 1);
        fin = System.nanoTime();
        Tejecucion = fin - inicio;

        System.out.println("ExhaustivoPoda    " + pmin.getA().getId() + "              " + pmin.getB().getId() + "               " + pmin.getDistancia()
                + "               " + a.getCont() + "                   " + Tejecucion / 1_000_000.0);

        a.setCont();
        inicio = System.nanoTime();
        pmin = a.DyVe(puntos, 0, puntos.size() - 1);
        fin = System.nanoTime();
        Tejecucion = fin - inicio;

        System.out.println("DivideVenceras    " + pmin.getA().getId() + "              " + pmin.getB().getId() + "               " + pmin.getDistancia()
                + "               " + a.getCont() + "                  " + Tejecucion / 1_000_000.0);
        a.setCont();
        inicio = System.nanoTime();
        pmin = a.DyVeMejorado(puntos);
        fin = System.nanoTime();
        Tejecucion = fin - inicio;

        System.out.println("DyV Mejorado      " + pmin.getA().getId() + "              " + pmin.getB().getId() + "               " + pmin.getDistancia()
                + "               " + a.getCont() + "                   " + Tejecucion / 1_000_000.0);

    }

    public ArrayList RellenarArrayFicheros() {
        ficheros.add("berlin52.tsp");
        ficheros.add("ch130.tsp");
        ficheros.add("ch150.tsp");
        ficheros.add("d493.tsp");
        ficheros.add("d657.tsp");
        return ficheros;
    }

    public void RellenarArrayEstrategias() {
        estrategias.add("Exhaustivo");
        estrategias.add("ExhaustivoPoda");
        estrategias.add("DyVe");
        estrategias.add("DyVeMejorado");
    }

    public void MostrarExhaustiva(String file, int tipo) throws FileNotFoundException {

        a.setCont();
        Lectura lec1 = new Lectura(file);
        puntos = lec1.getPuntos();
        inicio = System.nanoTime();

        if (tipo == 1) {
            pmin = a.BusquedaExahustiva(puntos, 0, puntos.size() - 1);
        }
        if (tipo == 2) {
            pmin = a.BusquedaPoda(puntos, 0, puntos.size() - 1);
        }
        if (tipo == 3) {
            pmin = a.DyVe(puntos, 0, puntos.size() - 1);
        }
        if (tipo == 4) {
            pmin = a.DyVeMejorado(puntos);
        }
        fin = System.nanoTime();
        Tejecucion = fin - inicio;
        System.out.println(estrategias.get(tipo - 1) + "        " + pmin.getA().getId() + "              " + pmin.getB().getId() + "               " + pmin.getDistancia()
                + "               " + a.getCont() + "                 " + Tejecucion / 1_000_000.0);

    }

    public Algoritmos Apartado3(int talla, List<Punto> puntos, int estrategia) throws IOException {

        if (estrategia == 1) {
            pmin = a.BusquedaExahustiva(puntos, 0, puntos.size() - 1);
        } else if (estrategia == 2) {
            pmin = a.BusquedaPoda(puntos, 0, puntos.size() - 1);
        } else if (estrategia == 3) {
            pmin = a.DyVe(puntos, 0, puntos.size() - 1);
        } else if (estrategia == 4) {
            pmin = a.DyVeMejorado(puntos);
        }

        return a;
    }

    public long aisdh(int talla, List<Punto> puntos, int estrategia) throws IOException {

        inicio = System.nanoTime();
        Apartado3(talla, puntos, estrategia);
        fin = System.nanoTime();
        Tejecucion = fin - inicio;
        return Tejecucion;

    }
}
