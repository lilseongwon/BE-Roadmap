package org.example.fly;

import org.example.fly.FlyBehavior;

public class FlyNoWay implements FlyBehavior {

    public void fly() {
        System.out.println("저는 못 날아요");
    }
}
