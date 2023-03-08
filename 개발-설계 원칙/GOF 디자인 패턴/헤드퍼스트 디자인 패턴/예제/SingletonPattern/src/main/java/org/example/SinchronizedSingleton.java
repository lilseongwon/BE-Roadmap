package org.example;

public class SinchronizedSingleton {
    private static SinchronizedSingleton uniqueInstance;

    private SinchronizedSingleton() {}

    public static synchronized SinchronizedSingleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SinchronizedSingleton();
        }
        return uniqueInstance;
    }
}
