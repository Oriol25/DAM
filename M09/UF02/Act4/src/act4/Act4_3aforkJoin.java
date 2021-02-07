/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act4;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author poved
 */
public class Act4_3aforkJoin extends RecursiveTask<Long> {
    int a;
    int b;
    
    public Act4_3aforkJoin (int a, int b) {
        this.a = a;
        this.b = b;
    }

    private int operacio(int i, int b) {
        if(i == 0){
            return 1;
        } else {
            return operacio(a - 1, b) * b;
        }
    }

    @Override
    protected Long compute() {
        if (a == 0){
            return (long) 1;
        } else {
            Act4_3aforkJoin act = new Act4_3aforkJoin(a - 1, b);
            act.fork();
            return act.join() * b;
        }
    }
    
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(new Act4_3aforkJoin(4,8)));
    }
}
