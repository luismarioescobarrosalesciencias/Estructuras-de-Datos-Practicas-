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
public interface IArreglo{

    /**
    * Devuelve el elemento que se encuentra en la posicion <code>th</code> en el
    * arreglo multidimensional.
    * @param indices arreglo con los indices del elemento a recuperar.
    * @return el elemento almacenado en la posicion <code>i</code>.
    */
    public int obtenerElemento(int [] indices);

    /**
    * Asigna un elemento en la posicion <code>th</code> del arreglo multidimensional.
    * @param indices arreglo con los indices donde se almacenara el elemento.
    * @param elem elemento a almacenar.
    */
    public void almacenarElemento(int [] indices, int elem);

    /**
    * Devuelve la posicion <code>i</code> del elemento en el arreglo de una dimension.
    * @param indices arreglo con los indices donde esta el elemento en el arreglo
    * multidimensional. Se debe cumplir que cada índice es positivo y menor que
    * el tamaño de la dimensión correspondiente.
    * @return la posicion del elemento en el arreglo de una dimension.
    * @throws IndexOutOfBoundsException si alguno de los indices del arreglo no
    * esta dentro del rango.
    */
    public int obtenerIndice(int [] indices);

}
