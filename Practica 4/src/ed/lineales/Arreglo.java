/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo resuelto a estudiantes actuales o potenciales.
 */
package ed.lineales;

/**
 * Arreglo de n-dimensiones
 * @author blackzafiro
 */
public class Arreglo implements IArreglo{
    private int [] arr;
    private int [] delta;
    private int len;


    public Arreglo(int [] ndimensional){
      int dimensions = 1 ;
      for(int i = 0 ; i < ndimensional.length; i++){
        if(ndimensional[i] == 0) throw new IndexOutOfBoundsException("Indice no valido");
          dimensions *= ndimensional[i];
        }
      arr = new int [dimensions];
      delta = new int [ndimensional.length];
      for(int i = 0; i < ndimensional.length; i++){
        delta[i]  = ndimensional[i];
      }
      this.len = dimensions;
      }


      /**
      * Devuelve el elemento que se encuentra en la posicion <code>th</code> en el
      * arreglo multidimensional.
      * @param indices arreglo con los indices del elemento a recuperar.
      * @return el elemento almacenado en la posicion <code>i</code>.
      */
    @Override
    public int obtenerElemento(int [] indices){
      ///aplica polinomio
      //int index;
      //index = obtenerIndice(indices);
      //return this.arr[index];
      if(indices.length <= 0 ) throw new IllegalArgumentException("Arreglo de indices no valido");
      return this.arr[obtenerIndice(indices)];
    }

    /**
    * Asigna un elemento en la posicion <code>th</code> del arreglo multidimensional.
    * @param indices arreglo con los indices donde se almacenara el elemento.
    * @param elem elemento a almacenar.
    */
    @Override
    public void almacenarElemento(int [] indices, int elem){
      //int index;
      //index = obtenerIndice(indices);
      //this.arr[index] =  elem;
      if(indices.length <= 0) throw new IllegalArgumentException("Arreglo de indices no valido");
      this.arr[obtenerIndice(indices)] = elem;
    }

    /**
    * Devuelve la posicion <code>i</code> del elemento en el arreglo de una dimension.
    * @param indices arreglo con los indices donde esta el elemento en el arreglo
    * multidimensional. Se debe cumplir que cada índice es positivo y menor que
    * el tamaño de la dimensión correspondiente.
    * @return la posicion del elemento en el arreglo de una dimension.
    * @throws IndexOutOfBoundsException si alguno de los indices del arreglo no
    * esta dentro del rango.
    */
    @Override
    public int obtenerIndice(int [] indices){
      if(indices.length <= 0) throw new IllegalArgumentException("Arreglo de indices no valido");
      int index  = indices[0];
      for(int i= 1;  i <  indices.length; i++){
        if(indices [i] < 0 || indices [i] > this.delta[i] ) throw new IndexOutOfBoundsException("Indice no valido");
          index = indices[i] + this.delta[i] * index  ;
      }
      return index;
    }

}
