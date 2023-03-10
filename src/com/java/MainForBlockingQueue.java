package com.java;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainForBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue tasks = new BlockingQueue();

        Thread thread1 = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    tasks.getTask().run();
                } catch (InterruptedException e) {
                    System.out.println("Исключение перехвачено");
                    Thread.currentThread().interrupt();
                }
            }
        });


        try (ExecutorService executorService = Executors.newFixedThreadPool(5);
             ExecutorService executorService1 = Executors.newSingleThreadExecutor()) {

            executorService.execute(new Task());
            executorService1.execute(new Task());

            executorService.shutdown();
            executorService1.shutdown();

            System.out.println("executorService = " + executorService.awaitTermination(1, TimeUnit.SECONDS));
            System.out.println("executorService1 = " + executorService1.awaitTermination(1, TimeUnit.SECONDS));
        }


//        Thread.sleep(1000);

//        thread1.interrupt();
//
//        Thread.sleep(1000);
//
//        for (int i = 0; i < 5; i++) {
//            tasks.putTask(new Task());
//        }
//
//        Thread.sleep(3000);
//
//        thread1.interrupt();

        System.out.println("Main thread ends!");
    }
}
