package com.java;

import java.util.ArrayList;

public class BlockingQueue {
    private final ArrayList<Runnable> tasks = new ArrayList<>();

    public synchronized Runnable getTask() throws InterruptedException {
        while (tasks.isEmpty()) {
            wait();
        }
        Runnable task = tasks.get(0);
        tasks.remove(task);
        return task;
    }

    public synchronized void putTask(Runnable task) {
        tasks.add(task);
        notify();
    }
}