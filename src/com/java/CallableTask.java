package com.java;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {

    int f;

    CallableTask(int f) {
        this.f = f;
    }

    @Override
    public Integer call() throws IllegalArgumentException {
        if (f <= 0) {
            throw new IllegalArgumentException("Вы ввели не верное число!");
        }
        int result = 1;
        for (int i = 2; i <= f; i++) {
            result *= i;
        }
        return result;
    }
}
