
package ed.lineales;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ed.Calificador;

/**
 *
 * @author blackzafiro
 * @author mindahrelfen
 */
public class ArregloTest extends Calificador{

    private static Arreglo a;
    private static Arreglo b;

    @Override
    public void init(){
        a = new Arreglo(new int [] {4,5});
        b = new Arreglo(new int [] {3,5,2});
    }

    @Test
    public void testBidimensional() {
        startTest("2-dimensiones",4.0);
        int count = 1;
        for(int i=0;i<4;i++){
            for (int j=0;j<5;j++){
                a.almacenarElemento(new int [] {i,j},count);
                count++;
            }
        }
        int [] indices = {3,2};
        int result = a.obtenerElemento(indices);
        assertEquals(result,18);
        addUp(4.0);
        passed();
    }

    @Test
    public void testTridimensional(){
        startTest("3-dimensiones",4.0);
        int count = 1;
        for(int i=0;i<3;i++){
            for (int j=0;j<5;j++) {
                for (int k=0;k<2;k++) {
                    b.almacenarElemento(new int [] {i,j,k},count);
                    count++;
                }
            }
        }
        int [] indices = {2,3,0};
        int result = b.obtenerElemento(indices);
        assertEquals(result,27);
        addUp(4.0);
        passed();
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testThrow1(){
        startTest("Obtener índice en almacenarElemento - excepción",1.0);
        try{
            b.almacenarElemento(new int [] {0,10,2},100);
        }catch(IndexOutOfBoundsException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testThrow2(){
        startTest("Obtener índice en obtenerElemento - excepción",1.0);
        try{
            b.obtenerElemento(new int [] {0,10,2});
        }catch(IndexOutOfBoundsException e){
            addUp(1.0);
            passed();
            throw e;
        }
    }
}
