package com.java;

import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        Daddy daddy = new Me();
        try {
            daddy.say();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Daddy {
    void say() throws Exception {
        System.out.println("I'm a Daddy");
    }
}
class Me extends Daddy {
    @Override
    void say() throws IOException {
        System.out.println("I'm Me");
    }
}
