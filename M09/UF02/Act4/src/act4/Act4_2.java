package act4;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Act4_2 {

    static class Bombolla implements Runnable {
        int bombolla;
        int[] arraybombolla;
        
        public Bombolla(int bombolla, int[] arraybombolla) {
            this.bombolla = bombolla;
            this.arraybombolla = arraybombolla;
        }

        public void run() {
            int bombollaRes;
            int[] arrayBombollares;
            
            for (int i = 1; i < arraybombolla.length; i++) {
                for (int k = 0; k < arraybombolla.length - i; k++) {
                    if(arraybombolla[k] > arraybombolla[k+1]){
                        arrayBombollares = arraybombolla.clone();
                        bombollaRes = arraybombolla[k];
                        arraybombolla[k] = arraybombolla[k+1];
                        arraybombolla[k+1] = bombollaRes;
                        System.out.println("La array " + bombolla + (bombolla + 1) + "  " +
                        Arrays.toString(arrayBombollares) + " pasa a ser " + Arrays.toString(arraybombolla));
                    }
                }
            }
            
            System.out.println("La array resultant es " + 
                    Arrays.toString(arraybombolla));
        }   
    }
    
    private static int[] arrayContador = {0,0,0,0};
    
    public static void main(String[] args) {
            int hilos = 4;
            int[] numRandom = new int[20];
            
            Bombolla[] bombolla = new Bombolla[4];
            
            numRandom = Random(numRandom);
            
            System.out.println("Numeros generats: " + Arrays.toString(numRandom));
            
            bombolla = arraybombolles(bombolla, numRandom);
        
            ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) 
                Executors.newFixedThreadPool(4);
        
            threadPoolExecutor = executa(bombolla, threadPoolExecutor);
            threadPoolExecutor.shutdown();
        
            int arrayOrdenada[] = new int[bombolla[0].arraybombolla.length * 4];
        
            ordenarBombolla(arrayOrdenada, bombolla);
     
    }
    
    public static int[] Random(int[] numRandom) {
        
        for (int i = 0; i < numRandom.length; i++) {
            numRandom[i] = (int) (Math.random() * 8 + 1);
        }
        
        return numRandom;
    }
    
    public static Bombolla[] arraybombolles(Bombolla[] arrayBombolla, int[] numRandom) {
        int aux = 0;
        int[] auxArray;
        for (int i = 0; i < arrayBombolla.length; i++) {
            auxArray = new int[numRandom.length / 4];
            for (int k = 0; k < auxArray.length; k++) {
                auxArray[k] = numRandom[aux];
                aux++;
            }
            Bombolla arrBombolla = new Bombolla(i, auxArray);
            arrayBombolla[i] = arrBombolla;
        }
        return arrayBombolla;
    }
    
    public static ThreadPoolExecutor executa(Bombolla[] arrayBombolla, ThreadPoolExecutor threadPoolExecutor) {
     
        for (int i = 0; i < arrayBombolla.length; i++) {
            threadPoolExecutor.execute(arrayBombolla[i]);
        }
        return threadPoolExecutor;
    }
    
    public static void ordenarBombolla(int[] arrayOrdenada, Bombolla[] arrayBombolla) {
        int aux;
        for (int i = 0; i < arrayOrdenada.length; i++) {
            aux = comprovant(arrayBombolla[0].arraybombolla[arrayContador[0]],
                    arrayBombolla[0].arraybombolla[arrayContador[1]],
                    arrayBombolla[0].arraybombolla[arrayContador[2]],
                    arrayBombolla[0].arraybombolla[arrayContador[3]]);
            arrayOrdenada[i] = aux;
            System.out.println(Arrays.toString(arrayContador));
        }
        System.out.println("Array ordenada: " + Arrays.toString(arrayOrdenada) + "\t");
    }
    
    public static int comprovant(int array, int array2, int array3, int array4) {
        
        int[] arrayBombolla = {array, array2, array3, array4};
        boolean comprov = false;
        int min = array;
        int max = 0;
        
        for (int i = 0; i < arrayBombolla.length; i++) {
            if (arrayContador[i] < 4) {
                if (arrayBombolla[i] <= min) {
                    min = arrayBombolla[i];
                    max = i;
                    comprov = true;
                }
            }
        }
        
        if (comprov) {
            arrayContador[max]++;
        }
        
        return min;
    }
}
