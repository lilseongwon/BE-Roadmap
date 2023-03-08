package org.example;

public class OriginalSingleton {
    private static OriginalSingleton uniqueInstance;

    private OriginalSingleton() {}

    public static OriginalSingleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new OriginalSingleton();
        }
        return uniqueInstance;
    }
}
