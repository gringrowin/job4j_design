package ru.job4j.serialization.xml.task;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    String model;

    Engine engine;

    @XmlElementWrapper(name = "wheels")
    @XmlElement(name = "wheel")
    String[] wheel;

    public Car() {
    }

    public Car(String model, Engine engine, String[] wheel) {
        this.model = model;
        this.engine = engine;
        this.wheel = wheel;
    }

    @Override
    public String toString() {
        return "Car{"
                + "model='" + model + '\''
                + ", engine=" + engine
                + ", wheels=" + Arrays.toString(wheel)
                + '}';
    }
}
