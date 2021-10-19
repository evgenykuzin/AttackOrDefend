package com.mygdx.game.resources;

import java.util.Random;

public class Utils {
    private static boolean chance(int percent){
        Random random = new Random();
        int r = 100/percent;
        return random.nextInt(r) == 0;
    }
}
