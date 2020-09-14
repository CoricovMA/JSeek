package org.jseek.util;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Util {

    public static Color getRandColor(){
        ThreadLocalRandom thrd = ThreadLocalRandom.current();
        int first = thrd.nextInt(0, 256);
        int second = thrd.nextInt(0, 256);
        int third = thrd.nextInt(0, 256);

        return new Color(first, second, third);
    }

    public static String checkUrl(String givenUrl){
        return givenUrl.replaceAll(" ", "%20");
    }
}
