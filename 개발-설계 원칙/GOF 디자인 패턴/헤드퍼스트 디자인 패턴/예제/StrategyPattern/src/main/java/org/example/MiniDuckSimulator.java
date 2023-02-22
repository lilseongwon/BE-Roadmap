package org.example;

import org.example.client.Duck;
import org.example.client.MallardDuck;

public class MiniDuckSimulator {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.performFly();
        mallard.performQuack();
    }
}