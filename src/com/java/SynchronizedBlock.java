package com.java;

public class SynchronizedBlock {
    static int a;
    static void incrementMethod() {
//        synchronized (SynchronizedBlock.class) {
            a++;
            System.out.print(a + " ");
//        }
    }

    public static void main(String[] args) {
        new MyThread();

        for(int i = 0; i < 15; i++) {
            incrementMethod();
        }
    }
}

class MyThread extends Thread {
    MyThread() {
        this.start();
    }

    @Override
    public void run() {
        for(int i = 0; i < 15; i++) {
            SynchronizedBlock.incrementMethod();
        }
    }
}

