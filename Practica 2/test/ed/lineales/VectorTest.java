/*
 * Código utilizado para el curso de Estructuras de Datos.
 * Se permite consultarlo para fines didácticos en forma personal.
 */
package ed.lineales;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ed.Calificador;
import java.util.Random;

/**
 * Batería de pruebas unitarias para la clase <code>Vector</code>.
 * @author veronica
 * @author mindahrelfen
 */
public class VectorTest extends Calificador{

    private Random intGenerator;
    protected Vector<Object> instance;

    @Override
    public void init(){
        intGenerator = new Random();
    }

    @BeforeClass
    public static void setUpClass() {
        totalPoints = points = 0;
    }

    /**
     * Test of lee method, of class Vector.
     */
    @Test
    public void testLee() {
        int i;
        Object expResult, result;
        startTest("lee de vector vacío",1.0);
        i = intGenerator.nextInt(Vector.INC);
        instance = new Vector<Object>();
        expResult = null;
        result = instance.lee(i);
        assertEquals(expResult, result);
        addUp(1.0);
        passed();
    }

    /**
     * Test of leeCapacidad method, of class Vector.
     */
    @Test
    public void testLeeCapacidad() {
        int expResult, result;
        startTest("leeCapacidad",1.0);
        instance = new Vector<Object>();
        expResult = Vector.INC;
        result = instance.leeCapacidad();
        assertEquals(expResult, result);
        addUp(1.0);
        passed();
    }

    /**
     * Test of asignaCapacidad y leeCapacidad method, of class Vector.
     */
    @Test
    public void testAsignaLeeCapacidad1() {
        int expResult, result;
        startTest("leeCapacidad tras redimensionar",0.7);
        instance = new Vector<Object>();
        expResult = intGenerator.nextInt(1000) * Vector.INC;
        instance.asignaCapacidad(expResult);
        result = instance.leeCapacidad();
        assertEquals(expResult, result);
        addUp(0.7);
        passed();
    }

    /**
     * Test of asignaCapacidad y leeCapacidad method, of class Vector.
     */
    @Test(expected=IllegalSizeException.class)
    public void testAsignaCapacidad() {
        int index;
        startTest("Asigna capacidad - valor inválido",0.3);
        instance = new Vector<Object>();
        index = 0;
        try {
            instance.asignaCapacidad(index);
        } catch (IllegalSizeException e) {
            addUp(0.3);
            passed();
            throw e;
        }
    }

    /**
     * Test of asigna y lee method, of class Vector.
     */
    @Test
    public void testAsignaLee() {
        int i;
        String e;
        Vector<String> instance;
        startTest("asigna y lee - dentro de INC",1.0);
        i = intGenerator.nextInt(Vector.INC);
        e = "Palabra";
        instance = new Vector<String>();
        instance.asigna(i, e);
        assertEquals(instance.lee(i), e);
        addUp(1.0);
        passed();
    }

    /**
     * Test of aseguraCapacidad y leeCapacidad method, of class Vector.
     */
    @Test
    public void testAseguraLeeCapacidad() {
        int n;
        startTest("aseguraLeeCapacidad - sin cambios",2.0);
        n = intGenerator.nextInt(Vector.INC);
        instance = new Vector<Object>();
        instance.aseguraCapacidad(n);
        assertEquals(Vector.INC, instance.leeCapacidad());
        addUp(2.0);
        passed();
    }

    /**
     * Test of aseguraCapacidad y leeCapacidad method, of class Vector.
     */
    @Test
    public void testAseguraLeeCapacidad1() {
        int n;
        startTest("aseguraLeeCapacidad - con incremento",2.0);
        n = Vector.INC + 1 + intGenerator.nextInt(10000);
        instance = new Vector<Object>();
        instance.aseguraCapacidad(n);
        assertTrue(n < instance.leeCapacidad());
        addUp(2.0);
        passed();
    }

    /**
     * Test of aseguraCapacidad y leeCapacidad method, of class Vector.
     */
    @Test
    public void testAseguraLeeCapacidad2() {
        int n;
        startTest("aseguraLeeCapacidad - con incremento, caso frontera",2.0);
        n = Vector.INC + 1;
        instance = new Vector<Object>();
        instance.aseguraCapacidad(n);
        assertTrue(n < instance.leeCapacidad());
        addUp(2.0);
        passed();
    }
}
