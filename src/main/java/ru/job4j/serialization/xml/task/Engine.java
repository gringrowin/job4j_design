package ru.job4j.serialization.xml.task;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "engine")
public class Engine {

    @XmlAttribute
    int horse;
    @XmlAttribute
    int volume;
    @XmlAttribute
    boolean isTurbo;

    public Engine() {
    }

    public Engine(int horse, int volume, boolean isTurbo) {
        this.horse = horse;
        this.volume = volume;
        this.isTurbo = isTurbo;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "horse=" + horse
                + ", volume=" + volume
                + ", isTurbo=" + isTurbo
                + '}';
    }
}
