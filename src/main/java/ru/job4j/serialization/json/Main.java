package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        /* JSONArray из ArrayList */
        List<String> wheelsList = new ArrayList<>();
        wheelsList.add("first");
        wheelsList.add("second");
        wheelsList.add("third");
        wheelsList.add("fourth");
        JSONArray jsonWheels = new JSONArray(wheelsList);

        /* JSONObject напрямую методом put */
        final Engine engine = new Engine(72, 3500, true);


        final String[] wheels = new String[]{"first", "second", "third", "fourth"};

        final Car car = new Car("Moskvich", engine, wheels);

        JSONObject jsonCar = new JSONObject();
        jsonCar.put("model", car.getModel());
        jsonCar.put("engine", new JSONObject(engine));
        jsonCar.put("wheels", jsonWheels);

        /* Выведем результат в консоль */
        System.out.println(jsonCar.toString());

        /* Преобразуем объект car в json-строку */
        System.out.println(new JSONObject(car).toString());
    }
}
