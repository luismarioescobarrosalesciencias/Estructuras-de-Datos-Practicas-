
package ed;
import java.util.Random;
import java.util.Iterator;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mindahrelfen
 */
public abstract class Calificador{

    public static final int SMALL_RANGE = 4;
    public static final int MEDIUM_RANGE = 16;
    public static final int LARGE_RANGE = 256;

    protected static double points;                 //Puntos acumulados por prueba
    protected static double numberOfPoints;         //Puntos totales por prueba
    protected static double totalPoints;            //Puntos acumulados por todas las pruebas
    protected static double totalEarnedPoints;      //Puntos acumulados por todas las pruebas pasadas
    protected static double numberOfTests;          //Numero total de pruebas
    protected static double passedTests;            //Numero de pruebas pasadas
    protected static RandomStringGenerator rsg;     //Genera numeros aleatorios atravez de su iterador
    protected static Iterator<String> rsgIt;        //Iterador de rsg
    protected static int range;                     //Cantidad de elementos que rsg tendra
    
    protected static boolean printed;
    
    public Calificador(){    
        set(MEDIUM_RANGE);
        init();
    }

    protected void init(){
    }

    protected void set(int r){
        range = r;
        rsg = new RandomStringGenerator(range);
    }

    @BeforeClass
    public static void setUpClass(){
        points = numberOfPoints = totalPoints = numberOfTests = passedTests = 0.0;
        printed = true;
    }

    @AfterClass
    public static void tearDownClass(){
        print(true);
        System.out.println("==============================");
        System.out.println("Test: " + (int)(passedTests) + "/" + (int)(numberOfTests) 
                + "    Points: " + totalEarnedPoints + "/" + totalPoints + "  -->  " + (10.0 * totalEarnedPoints / totalPoints));
        System.out.println("==============================");
    }

    protected final void startTest(String s, double p){
        print(true);
        System.out.print(s + ": ");
        numberOfTests += 1.0;
        numberOfPoints = p;
        totalPoints += p;
        points = 0.0;
        printed = false;
    }

    protected final void startTest(String s,String d, double p){
        print(true);
        System.out.print(s + ": ");
        System.out.println(d + ".");
        numberOfTests += 1.0;
        numberOfPoints = p;
        totalPoints += p;
        points = 0.0;
        printed = false;
    }

    protected final void startTest(String c, String s, String d, double p){
        print(true);
        System.out.print("(" + c + ")");
        System.out.print(s + ": ");
        System.out.println(d + ".");
        numberOfTests += 1.0;
        numberOfPoints = p;
        totalPoints += p;
        points = 0.0;
        printed = false;
    }
    
    protected final void addUp(double d){
        totalEarnedPoints += d;
        points += d;
    }

    protected final void passed(){
        print(false);
        System.out.println("\tPassed.");
        passedTests += 1.0;
    }
    
    protected static final void print(boolean p){
        String s;
        if(!printed){
            s = "[" + points + "/" + numberOfPoints + "]";
            if(p){
                System.out.println(s);
            }else{
                System.out.print(s);
            }
        }
        printed = true;
    }
}
