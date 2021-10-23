
package ed;


import java.util.Iterator;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * Clase que se encarga de generar de manera aleatoria numeros del tamaño del arreglo dado
 * devueltos como String, esto lo hara atravez de un iterador.
 * @author mindahrelfen
 */
public class RandomStringGenerator implements Iterable<String> {

    //Tamaño por defecto
    public static int INIT_SIZE = 16;

    //Guarda los numeros que ya se devolvieron
    protected boolean array[];

    //Si este parametro es true al numero cero se le asignara null como valor.
    protected boolean useNull;

    public RandomStringGenerator(){
        this(true);
    }

    public RandomStringGenerator(boolean b){
        this(INIT_SIZE,b);
    }

    public RandomStringGenerator(int size){
        this(size,true);
    }

    public RandomStringGenerator(int size, boolean b){
        array = new boolean[size];
        useNull = b;
    }

    @Override
    public Iterator<String> iterator(){
        return new RdnStringIterator();
    }

    @Override
    public String toString() {
        String e;
        StringBuilder sb;
        Iterator<String> it = iterator();
        if(!it.hasNext()){
            return "[]";
        }else{
            sb = new StringBuilder();
            sb.append('[');
            while(it.hasNext()){
                e = it.next();
                sb.append(e);
                if(!it.hasNext()){
                    sb.append(']');
                }else{
                    sb.append(", ");
                }
            }
            return sb.toString();
        }
    }

    protected class RdnStringIterator implements Iterator<String>{
        
        protected int size;
        protected Random rnd;
        
        public RdnStringIterator(){
            rnd = new Random();
            size = 0;
            for(int i = 0; i < array.length; i++){
                array[i] = false;
            }
        }
        
        protected int getNextInt(){
            int i;
            do{
                i = rnd.nextInt(array.length);
            }while(array[i]);
            return i;
        }
        
        @Override
        public String next(){
            int i;
            String s;
            if(!hasNext()) throw new NoSuchElementException();
            i = getNextInt();
            if(useNull && i == 0){
                s = null;
            }else{
                s = "" + i;
            }
            array[i] = true;
            size++;
            return s;
        }
        
        @Override
        public boolean hasNext(){
            return size < array.length;
        }
    }

}
