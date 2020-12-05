package com.javarush.games.snake;

import src.Color;
import src.Game;

import java.util.Random;


public class Dead extends GameObject{
    private static final String[]  DEAD_SIGN = { "\uD83D\uDC3B", "\uD83D\uDC2F", };  //Чтобы отобразить медведя и тигра


    public boolean isAlive = true; //хранение значения жив ли объект

    public Dead(int x, int y) {   // переопределяем
        super(x, y);
    }

    public void draw(Game game){  //будет отрисовывать яблоко на игровом поле


        game.setCellValueEx(x, y, Color.NONE, DEAD_SIGN[0], Color.NONE, 75);


    }
    public void draw2(Game game){  //будет отрисовывать яблоко на игровом поле


        game.setCellValueEx(x, y, Color.NONE, DEAD_SIGN[1], Color.NONE, 75);


    }
}
