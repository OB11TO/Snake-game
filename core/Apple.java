package com.javarush.games.snake;

import com.javarush.engine.cell.*;


public class Apple extends GameObject{
    private static final String APPLE_SIGN =  "\uD83C\uDF4E";   //Чтобы отобразить яблоко на игровом поле

    public Apple(int x, int y) {   // переопределяем
        super(x, y);
    }

    public void draw(Game game){  //будет отрисовывать яблоко на игровом поле
        game.setCellValueEx(x, y, Color.RED,APPLE_SIGN , Color.RED, 75);  //


    }
}
