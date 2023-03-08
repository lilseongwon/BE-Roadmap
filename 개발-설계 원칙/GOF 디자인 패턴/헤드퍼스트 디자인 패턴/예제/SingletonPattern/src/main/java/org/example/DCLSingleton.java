package org.example;

public class DCLSingleton {
    private volatile static DCLSingleton uniqueInstance;

    private DCLSingleton() {}

    public static DCLSingleton getInstance() {
        synchronized (DCLSingleton.class) {
            if (uniqueInstance == null) {
                uniqueInstance = new DCLSingleton();
            }
        }
        return uniqueInstance;
    }
}
