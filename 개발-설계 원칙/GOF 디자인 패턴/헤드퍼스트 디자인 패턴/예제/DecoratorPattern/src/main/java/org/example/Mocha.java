package org.example;

public class Mocha extends CondimentDecorator {
    
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }
    
    public String getDescription() {
        return beverage.getDescription() + ", 모카";
    }
    
    public double cost() {
        return beverage.cost() + .20;
    }
}
