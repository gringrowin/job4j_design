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

    public int getHorse() {
        return horse;
    }

    public int getVolume() {
        return volume;
    }

    public boolean isTurbo() {
        return isTurbo;
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
