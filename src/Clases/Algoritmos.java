package Clases;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author raulp
 */
public class Algoritmos {

    private PuntosMin p = new PuntosMin();
    private static int cont = 0;

    public static double distancia(Punto a, Punto b) {
        double x = Math.abs(a.getX() - b.getX());
        double y = Math.abs(a.getY() - b.getY());

        return (double) Math.sqrt((x * x) + (y * y));
    }

    public PuntosMin getPuntosMin() {
        return p;
    }

    public int getCont() {
        return cont;
    }

    public void setCont() {
        cont = 0;
    }

    public void incrementaCont() {

    }

    public PuntosMin BusquedaExahustiva(List<Punto> puntos, int inicio, int fin) {
        p.punt(puntos.get(0), puntos.get(1));
        double dmin = distancia(puntos.get(0), puntos.get(1));
        for (int i = inicio; i <= fin; i++) {
            for (int j = i + 1; j <= fin; j++) {
                double d = distancia(puntos.get(i), puntos.get(j));
                cont++;
                if (d < dmin) {
                    dmin = d;
                    p.punt(puntos.get(i), puntos.get(j));
                }
            }
        }
        return p;
    }

    public PuntosMin BusquedaExahustiva11(List<Punto> puntos, int inicio, int fin) {
        double dmin = distancia(puntos.get(0), puntos.get(1));
        cont++;
        p.punt(puntos.get(0), puntos.get(1));
        for (int i = inicio; i < fin; i++) {
            for (int j = i + 1; j < fin && j <= i + 11; j++) {
                double d = distancia(puntos.get(i), puntos.get(j));
                cont++;
                if (d < dmin) {
                    dmin = d;
                    p.punt(puntos.get(i), puntos.get(j));
                }
            }
        }
        return p;
    }

    
    public static PuntosMin BusquedaPoda(List<Punto> puntos, int inicio, int fin) {
        //cont = 0;
        quicksort(puntos, 0, fin);
        double dmin = distancia(puntos.get(0), puntos.get(1));
        cont++;
        PuntosMin p = new PuntosMin(puntos.get(0), puntos.get(1));
        for (int i = inicio; i < fin; i++) {
            int j = i + 1;
            while ((j < fin) && ((puntos.get(j).getX() - puntos.get(i).getX()) < dmin)) {
                double d = distancia(puntos.get(i), puntos.get(j));
                cont++;
                if (d < dmin) {
                    dmin = d;
                    p.punt(puntos.get(i), puntos.get(j));
                }
                j++;
            }
        }
        return p;
    }
    //MODIFICACION EN CLASE
    /*public static PuntosMin BusquedaPoda(List<Punto> puntos, int inicio, int fin) {
        quicksort(puntos, 0, puntos.size() - 1); //ordenamos el fichero por la cordenada x
        double dmin = distancia(puntos.get(0), puntos.get(1));
        cont++;
        PuntosMin p = new PuntosMin(puntos.get(0), puntos.get(1));
        for (int i = inicio; i < fin; i++) {
            for (int j = i + 1; j < fin && j <= i + 11; j++) {
                if ((puntos.get(j).getX() - puntos.get(i).getX()) < dmin) {
                    double d = distancia(puntos.get(i), puntos.get(j));
                    cont++;
                    if (d < dmin) {
                        dmin = d;
                        p.punt(puntos.get(i), puntos.get(j));
                    }
                } else {
                    j = fin; //o ponemos break y al carajo
                }
            }
        }
        return p;
    }*/

    //i de izq y d de derch
    public static PuntosMin DyVe(List<Punto> puntos, int i, int d) {
        quicksort(puntos, 0, puntos.size() - 1);
        int n = d - i + 1;

        // Caso base: Si el número de puntos es menor a 3, usar el método de búsqueda directa.
        if (n < 3) {
            return BusquedaPoda(puntos, i, d);
        }
        // Dividir el conjunto en dos mitades
        int mitad = (i + d) / 2;
        PuntosMin izq = DyVe(puntos, i, mitad);
        PuntosMin der = DyVe(puntos, mitad + 1, d);
        PuntosMin p = new PuntosMin();
        // Distancias mínimas de las mitades izquierda y derecha
        double di = izq.getDistancia();
        cont++;
        double dd = der.getDistancia();
        cont++;

        double dmin;
        if (di <= dd) {
            dmin = di;
            p = izq;
        } else {
            dmin = dd;
            p = der;
        }

        // Crear una lista de puntos dentro de la franja central
        List<Punto> franja = new ArrayList<>();
        for (int k = i; k <= d; k++) {
            if (Math.abs(puntos.get(k).getX() - puntos.get(mitad).getX()) < dmin) {
                franja.add(puntos.get(k));
            }
        }

        // Ordenar los puntos de la franja por coordenada Y para facilitar comparaciones verticales
        //franja.sort(Comparator.comparingDouble(Punto::getY));
        // Comprobar las distancias entre los puntos dentro de la franja central
        
        
        for (int c = 0; c < franja.size(); c++) {
            // Comparar solo con los próximos puntos en Y, ya que están ordenados
            for (int e = c + 1; e < franja.size() && (franja.get(e).getX() - franja.get(c).getX()) < dmin; e++) {
                double distanciaActual = distancia(franja.get(c), franja.get(e));
                cont++;
                if (distanciaActual < dmin) {
                    dmin = distanciaActual;
                    p = new PuntosMin(franja.get(c), franja.get(e));
                }
            }
        }

        return p; // Retornar el resultado con la menor distancia encontrada
    }


    public PuntosMin DyVeMejorado(List<Punto> puntos) {
        quicksort(puntos, 0, puntos.size() - 1);
        return DyVeMejorado(puntos, 0, puntos.size() - 1);
    }

    public PuntosMin DyVeMejorado(List<Punto> puntos, int i, int d) {
        int n = (d - i + 1);

        if (n <= 3) {
            PuntosMin izq = new PuntosMin(), der = new PuntosMin();
            int mitad = (i + d) / 2;
            izq = DyVe(puntos, i, mitad);
            der = DyVe(puntos, mitad + 1, d);

            double di = izq.getDistancia();
            double dd = der.getDistancia();
            double dmin, dis;
            if (di <= dd) {
                dmin = di;
                p = izq;
            } else {
                dmin = dd;
                p = der;
            }

            int a, b;
            for (a = mitad; a >= 1; a--) {
                if ((puntos.get(mitad + 1).getX() - puntos.get(a).getX()) > dmin) {
                    break;
                }
            }

            for (b = mitad + 1; b <= d; b++) {
                if ((puntos.get(b).getX() - puntos.get(mitad).getX()) > dmin) {
                    break;
                }
            }
            List<Punto> puntosAux = null;
            int j, k;
            for (j = a + 1, k = 0; j <= b - 1; j++, k++) {
                puntosAux.add(puntos.get(j));
            }

            if (puntosAux.size() > 2) {
                quicksorty(puntosAux, 0, puntosAux.size() - 1);
            }

            PuntosMin franja = BusquedaExahustiva11(puntosAux, 0, k - 1);

            if (franja.getDistancia() < p.getDistancia()) {
                p = franja;
            }

        } else {
            p = BusquedaPoda(puntos, i, d);
        }

        return p;
    }

    /*public PuntosMin DyVmejorado(List<Punto> puntos, int i, int d) {
        quicksort(puntos, i, d);
        if ((d - i - 1) < 3) {
            int mitad = (i + d) / 2;
            PuntosMin izq = DyVmejorado(puntos, i, mitad);
            PuntosMin der = DyVmejorado(puntos, mitad + 1, d);

            PuntosMin minpar = izq;

            if (der.getDistancia() < minpar.getDistancia()) {
                minpar = der;
            }
            //cont++;
            //izq T[i,m]
            int a = mitad;
            while ((a >= i) && ((puntos.get(mitad + 1).getX() - puntos.get(a).getX()) < minpar.getDistancia())) {
                a--;
                cont++;
            }
            //Franja izq T[a+1,m]
            //dcha T[m+1,d]
            int b = mitad + 1;
            while ((b <= d) && (puntos.get(b).getX() - puntos.get(mitad).getX()) < minpar.getDistancia()) {
                b++;
                //cont++;
            }
    
    
    
            //Franja dcha T[m+1,b-1]
            ArrayList<Punto> puntosAux = new ArrayList<Punto>();
            int j, k;
            for (j = a + 1, k = 0; j <= b - 1; j++, k++) {
                puntosAux.add(puntos.get(j));
            }
            if (puntosAux.size() > 2) {
                quicksorty(puntosAux, 0, puntosAux.size() - 1);
            }

            PuntosMin franja = BusquedaExahustiva11(puntosAux, 0, k - 1);

            if (franja.getDistancia() < minpar.getDistancia()) {
                minpar = franja;
            }
            //cont++;

            return minpar;
        } else {
            return BusquedaExahustiva(puntos, i, d);

        }
    }*/
    public static void quicksort(List<Punto> puntos, int izq, int der) {

        // tomamos primer elemento como pivote
        Punto pivote = puntos.get(izq);
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        Punto aux;

        while (i < j) {                          // mientras no se crucen las búsquedas                                   
            while ((puntos.get(i).getX() <= pivote.getX()) && (i < j)) {
                i++; // busca elemento mayor que pivote
            }
            while (puntos.get(j).getX() > pivote.getX()) {
                j--;           // busca elemento menor que pivote
            }
            if (i < j) {                        // si no se han cruzado                      
                aux = puntos.get(i);                      // los intercambia
                puntos.set(i, puntos.get(j));
                puntos.set(j, aux);
            }
        }

        puntos.set(izq, puntos.get(j));      // se coloca el pivote en su lugar de forma que tendremos                                    
        puntos.set(j, pivote);      // los menores a su izquierda y los mayores a su derecha
        if (izq < j - 1) {
            quicksort(puntos, izq, j - 1);          // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            quicksort(puntos, j + 1, der);          // ordenamos subarray derecho
        }
    }

    public static void quicksorty(List<Punto> puntos, int izq, int der) {

        // tomamos primer elemento como pivote
        Punto pivote = puntos.get(izq);
        int i = izq;         // i realiza la búsqueda de izquierda a derecha
        int j = der;         // j realiza la búsqueda de derecha a izquierda
        Punto aux;

        while (i < j) {                          // mientras no se crucen las búsquedas                                   
            while ((puntos.get(i).getY() <= pivote.getY()) && (i < j)) {
                i++; // busca elemento mayor que pivote
            }
            while (puntos.get(j).getY() > pivote.getY()) {
                j--;           // busca elemento menor que pivote
            }
            if (i < j) {                        // si no se han cruzado                      
                aux = puntos.get(i);                      // los intercambia
                puntos.set(i, puntos.get(j));
                puntos.set(j, aux);
            }
        }

        puntos.set(izq, puntos.get(j));      // se coloca el pivote en su lugar de forma que tendremos                                    
        puntos.set(j, pivote);      // los menores a su izquierda y los mayores a su derecha

        if (izq < j - 1) {
            quicksort(puntos, izq, j - 1);          // ordenamos subarray izquierdo
        }
        if (j + 1 < der) {
            quicksort(puntos, j + 1, der);          // ordenamos subarray derecho
        }
    }

    public void rellenarPuntos(ArrayList<Punto> p, int n, boolean mismax) {
        int num, den;
        double x, y, aux1;
        Random r = new Random(System.currentTimeMillis());
        if (mismax) { //PEOR CASO
            for (int i = 0; i < n; i++) {
                aux1 = r.nextDouble(7, 1007);
                y = aux1 / ((double) i + 1 + i * 0.100); //aux2; //+(i/3.0);
                num = r.nextInt(0, 3);
                y += ((i % 500) - num * (r.nextInt(0, 100)));
                //y = Math.abs(y);
                x = 1;
                p.add(new Punto(i + 1, x, y));
            }
        } else { //CASO MEDIO
            for (int i = 0; i < n; i++) {
                num = r.nextInt(1, 4000); //genera un número aleatorio entre 1 y 4000
                den = r.nextInt(17) + 7; //genera un aleatorio entre 7 y 17
                x = num / ((double) den + 0.37); //division con decimales
                y = (r.nextInt(1, 4000)) / ((double) (r.nextInt(7, 17)) + 0.37);
                p.add(new Punto(i + 1, x, y));
            }
        }
    }
}
