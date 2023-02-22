package org.example.client;

import org.example.fly.FlyBehavior;
import org.example.quack.QuackBehavior;

public abstract class Duck {
    FlyBehavior flyBehavior;
    QuackBehavior quackBehavior;

    public Duck() {}
    public abstract void display();

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("모든 오리는 물에 뜹니다. 가짜 오리도 뜨죠");
    }

}
