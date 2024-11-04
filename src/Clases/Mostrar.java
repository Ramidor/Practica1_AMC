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

    public void MostrarExhaustiva(ArrayList<Punto> puntos2, int tipo) throws FileNotFoundException {

        a.setCont();

        inicio = System.nanoTime();

        if (tipo == 1) {
            pmin = a.BusquedaExahustiva(puntos2, 0, puntos2.size() - 1);
        }
        if (tipo == 2) {
            pmin = a.BusquedaPoda(puntos2, 0, puntos2.size() - 1);
        }
        if (tipo == 3) {
            pmin = a.DyVe(puntos2);
        }
        if (tipo == 4) {
            pmin = a.DyVeMejorado(puntos2);
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
            pmin = a.DyVe(puntos);
        } else if (estrategia == 4) {
            pmin = a.DyVeMejorado(puntos);
        }

        return a;
    }

    public long CompararStrats(int talla, List<Punto> puntos, int estrategia) throws IOException {

        inicio = System.nanoTime();
        Apartado3(talla, puntos, estrategia);
        fin = System.nanoTime();
        Tejecucion = fin - inicio;
        return Tejecucion;

    }

    public void TodasStrat(boolean peorcaso, List<Punto> puntos, int i) throws IOException {

        long Tejecucion1, Tejecucion2, Tejecucion3, Tejecucion4;

        Tejecucion1 = CompararStrats(i, puntos, 1);
        Tejecucion2 = CompararStrats(i, puntos, 2);
        Tejecucion3 = CompararStrats(i, puntos, 3);
        Tejecucion4 = CompararStrats(i, puntos, 4);
        System.out.printf("%d              %.9f          %.9f       %.9f        %.9f%n", i, Tejecucion1 / 1000000.0, Tejecucion2 / 1000000.0, Tejecucion3 / 1000000.0, Tejecucion4 / 1000000.0);
        lec.EscribirDat(1, i, (long) (Tejecucion1 / 1000000.0));
        lec.EscribirDat(2, i, (long) (Tejecucion2 / 1000000.0));
        lec.EscribirDat(3, i, (long) (Tejecucion3 / 1000000.0));
        lec.EscribirDat(4, i, (long) (Tejecucion4 / 1000000.0));

    }

    void MostrarExhaustiva(ArrayList<Punto> puntos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void Caso3(List<Punto> puntos, boolean peorcaso, int estrategia) throws IOException {
        int i = 500;
        puntos.clear();
        System.out.println("Talla           Tiempo");
        while (i <= 5000) {
            puntos.clear();
            p.rellenarPuntos(puntos, i, peorcaso);
            Tejecucion = CompararStrats(i, puntos, estrategia);
            System.out.printf("%d          %.9f%n", i, Tejecucion / 1000000.0);
            lec.EscribirDat(estrategia, i, Tejecucion);
            i += 500;
        }
    }
}
