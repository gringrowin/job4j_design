package ru.job4j.serialization.json;

public class Engine {

    int horse;
    int volume;
    boolean isTurbo;

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
