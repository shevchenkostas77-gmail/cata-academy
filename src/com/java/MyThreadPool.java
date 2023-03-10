package com.java;

import java.util.concurrent.*;

public class MyThreadPool {
    public static void main(String[] args)  {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
//        threadPool.execute(new Task());
//        threadPool.shutdown();
//        threadPool.awaitTermination(10000, TimeUnit.SECONDS);
//
//        ExecutorService threadpool2 = Executors.newFixedThreadPool(6);
//        for (int i = 1; i <=6; i++) {
//            threadpool2.execute(new Task());
//        }
//        threadpool2.shutdown();

        ScheduledExecutorService singleScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(7);

//        scheduledThreadPool.schedule(new Task(), 3, TimeUnit.SECONDS);
//        scheduledThreadPool.shutdown();

//        singleScheduledExecutor.scheduleAtFixedRate(new Task(), 3, 5, TimeUnit.SECONDS);
//        singleScheduledExecutor.scheduleWithFixedDelay(new Task(), 3, 3, TimeUnit.SECONDS);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Future<Integer> future = cachedThreadPool.submit(new CallableTask(-5));
        int result = 0;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            cachedThreadPool.shutdown();
        }

        System.out.println(result);
    }
}

