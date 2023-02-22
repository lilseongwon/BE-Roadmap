package org.example.quack;

public class MuteQuack implements QuackBehavior {

    public void quack() {
        System.out.println("<< 조용~ >>");
    }
}
