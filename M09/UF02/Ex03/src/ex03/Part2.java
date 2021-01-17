/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex03;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author poved
 */
public class Part2 extends RecursiveTask<Long> {

    long numero;
    static long NUM = 34;
    
    public Part2(long numero){
        this.numero=numero;
    }

    @Override
    protected Long compute() {
        double calcul = java.lang.Math.cos(54879854);
        if(numero <= 1) return numero;
        Part2 fib1 = new Part2(numero-1);
        //fib1.fork();
        Part2 fib2 = new Part2(numero-2);
        fib2.fork();
     return fib1.compute()+ fib2.join();
    }

    public static void main(String[] args){
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Calculat:  " + pool.invoke(new Part2(NUM)));
    }

}
