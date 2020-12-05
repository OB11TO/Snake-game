package com.javarush.games.snake;

import src.*;

public class SnakeGame extends Game {

    public static final int WIDTH = 50; //ширина
    public static final int HEIGHT = 35;  //высота

    private Dead dead1; //поле для создания смерти
    private Dead[] dead2 = new Dead[5]; //поле для создания смерти


    private Dead[] dead = new Dead[5];


    private Snake snake;  // поля для создания змеи
    private Apple apple;  // поля для создания яблоко


    private int turnDelay; // поле для подсчета времени змеи

    private boolean isGameStopped; //поле для завершения игры
    private static final int GOAL = 13;  //значание для змеи

    private int score;    // рекорд

    @Override
    public void initialize() {  // главный метод
        setScreenSize(WIDTH, HEIGHT);  // рисует экран ( открывает его)
        createGame();
    }



    private void createGame() {  // начало игры
        score = 0;
        setScore(score);
        turnDelay = 300;  // временной промежуток - скорость змеи
        setTurnTimer(turnDelay);  //вызываем фукцию для скорости
        snake = new Snake(WIDTH / 2, HEIGHT / 2); // расположение змеи
        createNewApple();

        createNewDead();

        isGameStopped = false;
        drawScene();  // функия отрисовки
    }

    private void drawScene() { //рисует все, что на экране
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                setCellValueEx(i, j, Color.BLACK, "");

            }
        }
        if(snake.snakeParts.size()==1){
            gameOver2();
        }
        // dead.draw(this); // рисует медведя и тигра
        snake.draw(this);  //рисует змею

        for(int i = 0; i < 5; i++){
            dead[i].draw(this);
            dead2[i].draw2(this);
        }
       /* dead1.draw(this);
        dead2.draw(this);
        dead3. draw(this);
        dead4.draw(this);
        dead5.draw(this);
        dead6.draw(this);
        dead7.draw(this);
        dead8.draw(this);
        dead9.draw(this);
        dead10.draw(this);
        dead11.draw(this);
        dead12.draw(this);
        dead13.draw(this);*/

        apple.draw(this);  // рисует яблоко


    }



    @Override
    public void onTurn(int step) { //функция для перерисовки поля
        snake.move(apple,dead, dead2);
        if (!apple.isAlive) {
            createNewApple();
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
        }
        for(int i = 0; i < 5; i++) {
            if (!dead[i].isAlive || !dead2[i].isAlive) {
                createNewDead();
                turnDelay -= 20;
                setTurnTimer(turnDelay);
            }
        }
           /* int i = snake.snakeParts.size();
            snake.snakeParts.remove(i);
            snake.snakeParts.remove(i-1);*/



        if(!snake.isAlive){  //проверка на проигрыш
            gameOver();
        }
        if(snake.getLength() > GOAL){    // проверкаа на выигрыш
            win();
        }
        drawScene();  //отрисовка
    }

    @Override
    public void onKeyPress(Key key) {  // обработчик нажатий
        if (key == Key.LEFT) {
            snake.setDirection(Direction.LEFT);
        } else if (key == Key.RIGHT) {
            snake.setDirection(Direction.RIGHT);
        } else if (key == Key.UP) {
            snake.setDirection(Direction.UP);
        } else if (key == Key.DOWN) {
            snake.setDirection(Direction.DOWN);
        }
        else if(key == Key.SPACE && isGameStopped){
            createGame();
        }
    }

    private void createNewApple(){  //метод для генерации новых яблок
        Apple newApple;
        do {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            newApple = new Apple(x, y);
        } while (snake.checkCollision(newApple));
        apple = newApple;
    }

    private void createNewDead(){  //метод для генерации новых убийц
        Dead[] newDead = new Dead[5];
        Dead[] newDead2 = new Dead[5];
        for(int i = 0; i < 5; i++) {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            newDead[i] = new Dead(x, y);
            dead[i] = newDead[i];

        }

        for(int i = 0; i < 5; i++) {
            int x = getRandomNumber(WIDTH);
            int y = getRandomNumber(HEIGHT);
            newDead2[i] = new Dead(x, y);
            dead2[i] = newDead2[i];

        }

        /*dead1 = newDead[0];
        dead2 = newDead[1];
        dead3 = newDead[2];
        dead4 = newDead[3];
        dead5 = newDead[4];
        dead6 = newDead[5];
        dead7 = newDead[6];
        dead8 = newDead[7];
        dead9 = newDead[8];
        dead10 = newDead[9];
        dead11 = newDead[10];
        dead12 = newDead[11];
        dead13 = newDead[12];*/

    }

    private void gameOver(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "САБОТАЖ В КОМАНДЕ", Color.RED, 35);

    }

    private void win(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "ТЫ АЛЬФА!!!", Color.YELLOW, 55);
    }
    public  void gameOver2(){

        isGameStopped = true;
        showMessageDialog(Color.BLACK, "ИСПУГАЛСЯ И УБЕЖАЛ", Color.RED, 35);

    }
}