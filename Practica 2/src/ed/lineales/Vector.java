/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal,
 * pero no está permitido transferirlo resuelto a estudiantes actuales o potenciales.
 */

package ed.lineales;
import java.lang.Math;

/**
 * Arreglo redimensionable.
 * @author veronica
 * @author mindahrelfen
 */
public class Vector<T> {

    /**
     * Define la capacidad inicial del <code>Vector</code>.
     */
    public static final int INC = 10;

    private Object[] buffer;

    /**
     * Constructor que crea un <code>Vector</code> con capacidad inicial INC.
     */
    public Vector() {
        buffer = new Object[INC];
    }

    /// MÉTODOS DE ACCESO

    /**
     * Devuelve el elemento almacenado en la posición <code>i</code>.
     * @param i el índice del objeto a recuperar.
     * @return el elemento almacenado en la posición <code>i</code>.
     * @throws IndexOutOfBoundsException si
     * <code>!(0 &lt;= i &lt; this.leeCapacidad()) </code>.
     */
    public T lee(int i) {
      if(i>this.leeCapacidad()) throw new IndexOutOfBoundsException("Este indice esta fuera de la capacidad");
      T e = (T)(this.buffer[i]);
      return e;
    }

    /**
     * Devuelve la capacidad actual de este <code>Vector</code>.
     * @return la capacidad actual del <code>Vector</code>.
     */
    public int leeCapacidad() {
    return this.buffer.length;
    }

    /// MÉTODOS DE MANIPULACIÓN

    /**
     * Almacena el elemento <code>e</code> en la posición <code>i</code>.
     * @param i el índice en el cual <code>e</code> será almacenado. Debe cumplirse <code>0 &lt;= i &lt; this.leeCapacidad() </code>.
     * @param e el elemento a almacenar.
     * @throws IndexOutOfBoundsException si <code>!(0 &lt;= i &lt; this.leeCapacidad()) </code>.
     */
    public void asigna(int i, T e) {
     if(i>this.leeCapacidad()) throw new IndexOutOfBoundsException("Este indice esta fuera de la capacidad");
      this.buffer[i]=e ;
    }
    /**
     * Copia los elementos de buffer a un arreglo
     * Si la longitud del arreglo n es menor copiamos hasta n
     * Si arr.length es mayor, copiamos hasta this.leeCapacidad()
     */
    private void copiar(Object[] arr){
      // Como los arreglosd e objetos en java inicializan en 0, no es necesario hacer nada mas a los elementos vacios.
      if(arr.length < this.leeCapacidad()){
        for(int i = 0; i < arr.length;i++){
          arr[i]=this.buffer[i];
        }
      }

      else{
        for(int i = 0; i<this.leeCapacidad();i++){
          arr[i]=this.buffer[i];
        }
      }
    }

    /**
     * Asigna la capacidad del <code>Vector</code>. Si <code>n &lt; this.leeCapacidad()</code> los elementos de
     * <code>n</code> en adelante son descartados.
     * Si <code>n &gt; this.leeCapacidad()</code> se agregan <code>null</code> en los espacios agregados.
     * @param n la nueva capacidad del <code>Vector</code>, debe ser mayor que cero.
     * @throws IllegalSizeException si <code>n &lt; 1</code>.
     */
    public void asignaCapacidad(int n) {
      if(n<1)throw new IllegalSizeException();
      if(n == this.leeCapacidad()) return;
      Object[] temp = new Object[n];
        this.copiar(temp);
        this.buffer = temp;
        return;
        }


    /**
     * Garantiza que el <code>Vector</code> cuente al menos con capacidad para almacenar <code>n</code> elementos.
     * Si <code>n &gt; this.leeCapacidad()</code> el tamaño del <code>Vector</code> es incrementado de tal modo que el requerimiento sea satisfecho con cierta holgura.
     * @param n capacidad mínima que debe tener el <code>Vector</code>,  no puede ser menor a cero.
     */
    public void aseguraCapacidad(int n) {
      if(n< this.leeCapacidad())return;
      int x = this.leeCapacidad();
      /**
      * Forma 1
      while(x <= n){
        x*=2;
      }**/
      int  u= this.leeCapacidad();
      int  div, i,res;

      div = (int)Math.ceil(n/u);
      i = (int) Math.ceil(Math.log((div)) / (Math.log(2)));
      if(i == 0){
        res = 2 * u;
      }else{
        res = (int )((Math.pow(2,i))*u);
      }
      //System.out.println(x);
      //System.out.println(res);
      this.asignaCapacidad(res);
      //System.out.println(this.leeCapacidad());
      }

}
