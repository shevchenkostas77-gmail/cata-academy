package com.java;

import java.util.concurrent.TimeUnit;

public class SleepInThread {
    public static void main(String[] args) throws InterruptedException {
        Thread threadExample = new Thread(new JoinClass());
        threadExample.start();
//        Thread.sleep(1000);
        threadExample.interrupt();
    }
}
class JoinClass implements Runnable {

    @Override
    public void run() {
        System.out.println("run.." + Thread.currentThread().isInterrupted());
        System.out.println("Thread.interrupted() 1 " + Thread.interrupted());
//        System.out.println("Thread.interrupted() 2 " + Thread.interrupted());
        System.out.println("end while.." + Thread.currentThread().isInterrupted());
        try {
            Thread.sleep(3000);
            System.out.println("try..");
        } catch (InterruptedException e) {
            System.out.println("catch..");
        }
        System.out.println("end run.." + Thread.currentThread().isInterrupted());
    }
}
