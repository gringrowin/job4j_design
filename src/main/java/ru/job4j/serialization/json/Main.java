package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car("Moskvich",
                new Engine(72, 3500, true),
                new String[]{"first", "second", "third", "fourth"});

        final Gson gson = new GsonBuilder().create();
        final String carJson = gson.toJson(car);
        System.out.println(carJson);

        final Car car1 = gson.fromJson(carJson, Car.class);
        System.out.println(car1);
    }
}
