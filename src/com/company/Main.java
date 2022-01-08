package com.company;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        double arr[] = new double[9000000];

        double[] list = parallelAssignment(arr);

//        synchronized (list){}

        System.out.println();

//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(list[i] + "\t");
//        }
//        System.out.println();

        // The first method to create a thread
        sumList sum  = new sumList(list);
        sortList sort = new sortList(list);
        minList min = new minList(list);
        maxList max = new maxList(list);

        Thread th1 = new Thread(sum);
        Thread th2 = new Thread(sort);
        Thread th3 = new Thread(min);
        Thread th4 = new Thread(max);

        th1.start();
        th2.start();
        th3.start();
        th4.start();

    }
    public static double[] parallelAssignment(double[] list){
            Random rd = new Random(); // creating Random object
            for (int i = 0; i < list.length; i++) {
                list[i] = rd.nextInt(100); // storing random integers in an array
            }
            System.out.print("Assigned "+list.length+ " values to the array");
        return list;
    }
}



class sumList implements Runnable{
    long start = System.currentTimeMillis();
    double[] list;
    double sum;

    sumList(double[]  list){
        this.list = list;
    }
    @Override
    public void run() {
        for (int i=0; i < this.list.length ; i++) {
            sum = sum + list[i];
        }
        long total = System.currentTimeMillis() - start;

        System.out.println("sum = " + sum + " (Time = "+total+" ms )");

    }

}

class sortList implements Runnable{
    long start = System.currentTimeMillis();
    double[] list;
    sortList(double[]  list){
        this.list = list;
    }
    @Override
    public void run() {
        Arrays.sort(this.list);
        long total = System.currentTimeMillis() - start;
        System.out.println("Sorted "+list.length+" elements" + " (Time = "+total+" ms )");
    }
}
class minList implements Runnable{
    long start = System.currentTimeMillis();
    double[] list;
    minList(double[]  list){
        this.list = list;
    }

    @Override
    public void run() {
        double min = list[0];
        double tmp = 0;
//        Arrays.sort(this.list);
        for (int i = 0; i <  this.list.length; i++) {
            if(this.list[i] < min){
                min = this.list[i];
            }
        }
        long total = System.currentTimeMillis() - start;
        System.out.println("min = " + this.list[0]+ " (Time = "+total+" ms )");
    }
}
class maxList implements Runnable{
    long start = System.currentTimeMillis();
    double[] list;
    maxList(double[]  list){
        this.list = list;
    }

    @Override
    public void run() {
        double max = list[0];
        double tmp = 0;
//        Arrays.sort(this.list);
        for (int i = 0; i <  this.list.length; i++) {
            if(this.list[i] > max){
                max = this.list[i];
            }
        }
        long total = System.currentTimeMillis() - start;

        System.out.println("max = " + this.list[this.list.length - 1]+ " (Time = "+total+" ms )");
    }
}








