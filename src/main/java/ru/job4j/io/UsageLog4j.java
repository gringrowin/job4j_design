package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte a = 1;
        short b = 2;
        int c = 3;
        long d = 4L;
        float e = 5.1f;
        double f = 6.2;
        char g = '7';
        boolean h = true;


        LOG.debug("User info one : {}, two : {}, three : {}, four : {}, five : {}, six : {}, seven : {}, bool : {}",
                a, b, c, d, e, f, g, h);
    }
}
