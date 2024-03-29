package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {

    String model;
    Engine engine;

    String[] wheels;

    public Car(String model, Engine engine, String[] wheels) {
        this.model = model;
        this.engine = engine;
        this.wheels = wheels;
    }

    public String getModel() {
        return model;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getWheels() {
        return wheels;
    }

    @Override
    public String toString() {
        return "Car{"
                + "model='" + model + '\''
                + ", engine=" + engine
                + ", wheels=" + Arrays.toString(wheels)
                + '}';
    }
}
