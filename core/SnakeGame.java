package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {

    public static final int WIDTH = 15;  //ширина  игрового поля
    public static final int HEIGHT = 15;  //высота

    private Snake snake;  //приватное поле для экземпляр создание змеи
    private Apple apple;  //приватное поле для экземпляр создание змеи
    private int turnDelay; // переменная для количесва времени

    @Override
    public void initialize() {    //переопределили метод для инициализации программы
        setScreenSize(WIDTH, HEIGHT);  // вызвали экран с размером 15 на 15
        // showGrid(false); убирает линии на клетках
        createGame();    //  создает игру
    }

    @Override
    public void onTurn(int x){  //будет вызывать перерисовку поля
        snake.move(apple);   // чтобы змейка перемещалась
        drawScene();  // и карта обновлялась
    }

    @Override
    public void onKeyPress(Key key){   //считываем клавишу
        if (key == Key.LEFT) {
            snake.setDirection(Direction.LEFT);
        } else if (key == Key.RIGHT) {
            snake.setDirection(Direction.RIGHT);
        } else if (key == Key.UP) {
            snake.setDirection(Direction.UP);
        } else if (key == Key.DOWN) {
            snake.setDirection(Direction.DOWN);
        }
    }

    private void createGame(){   //  создает игру
        turnDelay = 300;
        snake = new Snake(WIDTH / 2,HEIGHT / 2); // создали  объект змеи
        Apple apple = new Apple(5,5);
        setTurnTimer(turnDelay);  // считаем время
        drawScene();   // рисует клеточки  и вызывает рисование змеи
        //Apple apple = new Apple(7,7);  //создаем экземпялр яблоко с 7 7  тестовое
       // apple.draw(this);  // вызываем отрисовку яблока


    }

    private void drawScene(){    // рисует клеточки

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.DARKSEAGREEN, "");
            }
        }

        snake.draw(this);  // рисует змею

    }
}
