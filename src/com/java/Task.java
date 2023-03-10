package com.java;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    @Override
    public void run() {
        System.out.println("The beginning of the task... " + "THREAD - " + Thread.currentThread().getName() +
                ", task - " + this);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException in method run CLASS \"Task\"");
            Thread.currentThread().interrupt();
        }
        System.out.println("The task was completed! " + "THREAD - " + Thread.currentThread().getName() +
                ", task - " + this);
        System.out.println();
    }
}
