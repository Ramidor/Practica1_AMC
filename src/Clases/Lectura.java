package Clases;

import java.io.*;
import java.util.*;

/**
 *
 * @author raulp
 */
public class Lectura {
    BufferedReader reader;
    BufferedWriter writer;
    ArrayList<Punto> puntos;
    
    public Lectura(String file) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
        puntos = new ArrayList<Punto>();
        
        try {
            Leer();
            Cerrar();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e);
        }
    }

    public Lectura() {
    }

   
    public ArrayList<Punto> getPuntos() {
        return puntos;
    }
    
    void Leer() throws IOException {
        String line = reader.readLine();
        
        while (!line.contains("NODE_COORD_SECTION")) {
            line = reader.readLine();
        }
        
        while (!"EOF".equals(line = reader.readLine())) {            
            String[] data = line.split(" ");
            puntos.add(new Punto((int)Double.parseDouble(data[0]),Double.parseDouble(data[1]), Double.parseDouble(data[2])));
        }
    }
    
    void Cerrar() throws IOException {
        reader.close();
    }  
    
    public void Escrito(List<Punto> puntos) {
        for (int i = 0; i < puntos.size(); i++) {
            System.out.println("x: " + puntos.get(i).getX()
            + "   y: " + puntos.get(i).getY());
        }
    }
    
    public void EscribirTSP(List<Punto> puntos, String file) throws IOException{
        
        File archivo = new File(file + ".tsp");
        
        FileWriter escribir = new FileWriter(archivo, false);
        
        escribir.write("NAME: " + file + "\n");
        escribir.write("COMMENT: " + "\n");
        escribir.write("TYPE: TSP" + "\n");
        escribir.write("DIMENSION: " + puntos.size() + "\n");
        escribir.write("EDGE_WEIGHT_TYPE: EUC_2D" + "\n");
        escribir.write("NODE_COORD_SECTION" + "\n");
        
        for (int i = 0; i < puntos.size(); i++) {
            escribir.write(puntos.get(i).getId() + " " + puntos.get(i).getX() + " " + puntos.get(i).getY() + "\n");
        }
        escribir.write("EOF");
        
        escribir.close();
    }
    public void EscribirDat(int estrategia,int talla,long Tejecucion) throws IOException{
        Mostrar mo=new Mostrar();
        File archivo = new File(mo.estrategias.get(estrategia-1) + ".dat");
        
        FileWriter escribir = new FileWriter(archivo, false);
        
       escribir.write(talla + "          " + Tejecucion);
        
        
        escribir.close();
    }
}
