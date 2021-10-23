/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed.complejidad;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ed.Calificador;

/**
 *
 * @author blackzafiro
 */
public class ComplejidadTest extends Calificador{

    protected IComplejidad c;

    @Override
    public void init(){
        c = new Complejidad();
    }

    @BeforeClass
    public static void setUpClass() {
        totalPoints = points = 0;
    }

    /**
     * Test of tPascalRec method, of class Complejidad.
     */
    @Test
    public void testTPascalRec() {
        startTest("tPascalRec",2.0);
        assertEquals(10, c.tPascalRec(5, 2));
        addUp(1.0);
        assertEquals(3, c.tPascalRec(3, 2));
        addUp(1.0);
        passed();
    }
    /**
    * Implementacion : Test que prueba  valores de pascal recursivo, el resultado con el que se compara fue 
      calculado manualmente
    **/
    @Test
    public void tPascal_rec() {
        startTest("Test Pascal Recursivo ",2.0);
        assertEquals(20, c.tPascalRec(6, 3));
        addUp(1.0);
        assertEquals(4, c.tPascalRec(4, 1));
        addUp(1.0);
        passed();
    }
    /**
     * Test of tPascalIt method, of class Complejidad.
     */
    @Test
    public void testTPascalIt() {
        startTest("tPascalIt",2.0);
        assertEquals(10, c.tPascalIt(5, 2));
        addUp(1.0);
        assertEquals(3, c.tPascalIt(3, 2));
        addUp(1.0);
        passed();
    }
    /**
    * Implementacion : Test que prueba  valores de pascal iterativo, el resultado con el que se compara Fue
      calculado manualmente
    **/
    @Test
    public void tPascal_ite() {
        startTest("Test Pascal Iterativo",2.0);
        assertEquals(20, c.tPascalIt(6, 3));
        addUp(1.0);
        assertEquals(35, c.tPascalIt(7, 4));
        addUp(1.0);
        passed();
    }

    /**
     * Test of fibonacciRec method, of class Complejidad.
     */
    @Test
    public void testFibonacciRec() {
        startTest("fibonacciRec",2.0);
        assertEquals(8, c.fibonacciRec(6));
        addUp(1.0);
        assertEquals(21, c.fibonacciRec(8));
        addUp(1.0);
        passed();
    }

    /**
    * Implementacion : Test que prueba  valores de fibonacci recursivo, el resultado con el que se compara fue
      calculado manualmente
    **/
    @Test
    public void tFibon_Rec() {
        startTest("Test fibonacci recursivo",2.0);
        assertEquals(89, c.fibonacciRec(11));
        addUp(1.0);
        assertEquals(144, c.fibonacciRec(12));
        addUp(1.0);
        passed();
    }

    /**
     * Test of fibonacciIt method, of class Complejidad.
     */
    @Test
    public void testFibonacciIt() {
        startTest("fibonacciIt",2.0);
        assertEquals(21, c.fibonacciIt(8));
        addUp(1.0);
        assertEquals(144, c.fibonacciIt(12));
        addUp(1.0);
        passed();
    }

    /**
    * Implementacion : Test que prueba  valores de fibonacci iterativo, el resultado con el que se compara fue
      calculado manualmente
    **/
    @Test
    public void tFibo_It() {
        startTest("Test Fibonacci iterativo",2.0);
        assertEquals(233, c.fibonacciIt(13));
        addUp(1.0);
        assertEquals(377, c.fibonacciIt(14));
        addUp(1.0);
        passed();
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testFibItInvalido(){
        startTest("Cálculo fibonacci valor invalido",0.5);
        try{
            c.fibonacciIt(-5);
        }catch(IndexOutOfBoundsException e){
            addUp(0.5);
            passed();
            throw e;
        }
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testFibRecInvalido(){
        startTest("Cálculo fibonacci valor invalido2",0.5);
        try{
            c.fibonacciRec(-10);
        }catch(IndexOutOfBoundsException e){
            addUp(0.5);
            passed();
            throw e;
        }
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testPascalInvalido(){
        startTest("Cálculo pascal valor invalido",0.5);
        try{
            c.tPascalIt(-5,1);
        }catch(IndexOutOfBoundsException e){
            addUp(0.5);
            passed();
            throw e;
        }
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testPascalRecInvalido(){
        startTest("Cálculo pascal valor invalido2",0.5);
        try{
            c.tPascalRec(-5,1);
        }catch(IndexOutOfBoundsException e){
            addUp(0.5);
            passed();
            throw e;
        }
    }
}
