package com.java;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

public class Repetition {
    public static void main(String[] args) {
        Exchanger<Friends.Action> exchanger = new Exchanger<>();

        List<Friends.Action> friend1Action = new ArrayList<>();
        friend1Action.add(Friends.Action.KAMEN);
        friend1Action.add(Friends.Action.NOJNICI);
        friend1Action.add(Friends.Action.BUMAGA);

        List<Friends.Action> friend2Action = new ArrayList<>();
        friend2Action.add(Friends.Action.NOJNICI);
        friend2Action.add(Friends.Action.BUMAGA);
        friend2Action.add(Friends.Action.NOJNICI);

        Friends friend1 = new Friends("Stas", exchanger, friend1Action);
        Friends friend2 = new Friends("Oleg", exchanger, friend2Action);

    }
}

class Friends extends Thread {
    private final String name;
    private Exchanger<Action> exchanger;
    private List<Action> actions;

    public Friends(String name, Exchanger exchanger, List<Action> actions) {
        this.name = name;
        this.exchanger = exchanger;
        this.actions = actions;
        this.start();
    }

    enum Action { KAMEN, NOJNICI, BUMAGA; }

    public void whoWin(Action myAction, Action friendAction) {
        if ((myAction == Action.KAMEN && friendAction == Action.NOJNICI) ||
                (myAction == Action.NOJNICI && friendAction == Action.BUMAGA) ||
                (myAction == Action.BUMAGA && friendAction == Action.KAMEN)) {
            System.out.println(name + " победил!");
        }
    }

    @Override
    public void run() {
        Action friendAction;
        try {
            for (Action myAction : actions) {
                friendAction = exchanger.exchange(myAction);
                sleep(1500);
                whoWin(myAction, friendAction);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}