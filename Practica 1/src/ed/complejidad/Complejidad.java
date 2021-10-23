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
public class Complejidad implements IComplejidad{
    private long contador;
    /**
     * Devuelve el número de operaciones acumuladas desde la última vez
     * que se reinició el contador.
     * @return número de operaciones.
     */
     public long getOperaciones(){
       return this.contador;
     }
    @Override
      public long leeContador(){
      return contador;
    }

    @Override
    public int tPascalRec(int ren, int col){
      if( ren < 0 || col < 0) throw new IndexOutOfBoundsException();
      contador = 1;
      return pascalAux(ren, col);
    }
    private int pascalAux(int ren, int col){
      contador ++;
      if( ren == col || col == 0) return  1; //Esta linea de codigo no puede ir en el metodo principal, se necesita aqui
      return pascalAux(ren - 1,col - 1) + pascalAux(ren - 1,col);
    }

    /**
     * Metodo para calcular, iterativamente, el elemento en la fila
     * <code>i</code> y la columna <code>j</code> del triangulo de Pascal
     * @param ren el numero de fila
     * @param col el numero de columna
     * @return El elemento en la i-esima fila y la j-esima columna del triangulo
     * de Pascal.
     * @throws IndexOutOfBoundsException Si los indices <code>i</code> o
     * <code>j</code> son inválidos
     */
    @Override
    public int tPascalIt(int ren, int col){
      if( ren < 0 || col < 0) throw new IndexOutOfBoundsException();
      int [][] triangulo = new int[ren+1][col+1];
      contador=1;
      if(ren == col || col ==0 ) return 1;
      for(int i = 0;i <= ren;i++){
        triangulo[i] = new int  [i+1];
        triangulo[i][0] = 1; // j= 0
        triangulo[i][i] = 1; // j=i
        if(i >= 2){
          for (int j=1;j < i;j++){
            contador++;
            triangulo[i][j] = triangulo[i - 1][j - 1] + triangulo[ i-1 ][j];
          }
        }
      }
      //System.out.println(triangulo[ren ][col]);
      return triangulo[ren ][col ];
    }

    /**
     * Devuelve el n-esimo elemento, calculado de forma recursiva,
     * de la sucesion de Fibonacci
     * @param n el indice del elemento que se desea calcular
     * @return el n-esimo elemento de la sucesion de Fibonacci
     * @throws IndexOutOfBoundsException Si el valor de <code>n</code>es inválido
     */
    @Override
    public int fibonacciRec(int n){
      contador = 1;
      if(n < 0 ) throw new IndexOutOfBoundsException();
      //if(n == 0) return 0;
      //return fibo_aux(n);
      return n == 0 ? 0 : fibo_aux(n);
    }
    private int fibo_aux(int n){
    contador++;
    if(n <= 1) return n;
    else return fibo_aux(n - 1) + fibo_aux(n - 2); //Si n = 2, entonces la funcion recibe como parametro a 0, considerar ese caso.
  }
    /**
     * Devuelve el n-esimo elemento, calculado de forma iterativa,
     * de la sucesion de Fibonacci
     * @param n el indice del elemento que se desea calcular
     * @return el n-esimo elemento de la sucesiond de Fibonacci
     * @throws IndexOutOfBoundsException Si el valor de <code>n</code>es inválido
     */
    @Override
    public int fibonacciIt(int n){
      if(n<0) throw new IndexOutOfBoundsException();
      int aux;
      int anterior = 0;
      int actual = 1;
      int i = 1;
      while(i < n){
        aux = anterior;
        anterior = actual;
        actual = anterior + aux;
        i++;
      }
      contador = i;
      return actual;
    }

}
