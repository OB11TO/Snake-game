package com.javarush.games.snake;

import src.Color;
import src.Game;

import java.util.Random;


public class Apple extends GameObject{
    private static final String[]  APPLE_SIGN = { "🌕", "🌓", "🌗", "🌘", "\uD83C\uDF14", "\uD83C\uDF16"};  //Чтобы отобразить яблоко на игровом поле


    public boolean isAlive = true; //хранение значения жив ли объект

    public Apple(int x, int y) {   // переопределяем
        super(x, y);
    }

    public void draw(Game game){  //будет отрисовывать яблоко на игровом поле
        Random random = new Random ();
        int pos = random.nextInt(APPLE_SIGN.length);
        String APPLE = APPLE_SIGN[pos];
        game.setCellValueEx(x, y, Color.NONE, APPLE, Color.NONE, 75);  //



    }
}
