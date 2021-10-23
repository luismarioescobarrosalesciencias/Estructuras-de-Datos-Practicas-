/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo resuelto a estudiantes actuales o potenciales.
 */
package ed.complejidad;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Interfaz que define los métodos para utilizar con las pruebas unitarias.
 * @author veronica
 * @author mindahrelfen
 */
public class UsoComplejidad{


  public static void main(String[] args )throws FileNotFoundException{
    //String ruta= "src/ed/complejidad/";

    String archivo1 = "src/ed/complejidad/pascalRecursivo.dat";
    FileOutputStream archivo_salida_1 = new FileOutputStream(archivo1);
    Complejidad c1 = new Complejidad();
    int res1;
    for(int i =1; i<=10; i++){
        for(int j = 0; j<=i;j++){
          res1 = c1.tPascalRec(i,j);
          IComplejidad.escribeOperaciones(archivo1,i,j,c1.getOperaciones());

        }
      IComplejidad.escribeLineaVacia(archivo1);
    }


      /**
      Renglon columna operaciones,
      1
      1
      hacer esta madre con dos for
      **/


    String archivo2 = "src/ed/complejidad/pascalIterativo.dat";
    FileOutputStream archivo_salida_2 = new FileOutputStream(archivo2);
    Complejidad c2 = new Complejidad();
    int res2;
    for(int i = 0; i<=10;i++){
        for(int j= 0;j<=i;j++){
          res2 =c2.tPascalIt(i,j);
          IComplejidad.escribeOperaciones(archivo2,i,j,c2.getOperaciones());
        }
        IComplejidad.escribeLineaVacia(archivo2);
    }

    String archivo3 = "src/ed/complejidad/fiboRecursivo.dat";
    FileOutputStream archivo_salida_3 = new FileOutputStream(archivo3);
    Complejidad c3 = new Complejidad();
    int res3;
    for (int i = 0; i<=12; i++){
      res3 = c3.fibonacciRec(i);
      IComplejidad.escribeOperaciones(archivo3,i,c3.getOperaciones());
      IComplejidad.escribeLineaVacia(archivo3);
  }


    String archivo4 = "src/ed/complejidad/fiboIterativo.dat";
    FileOutputStream archivo_salida_4 = new FileOutputStream(archivo4);
    int res4;
    Complejidad c4 = new Complejidad();
    for(int i = 0;i<=10;i++){
      res4 =c4.fibonacciIt(i);
      IComplejidad.escribeOperaciones(archivo4,i,c4.getOperaciones());
      IComplejidad.escribeLineaVacia(archivo4);
    }

}
}
