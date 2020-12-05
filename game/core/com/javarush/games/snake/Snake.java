package com.javarush.games.snake;

import src.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    //private static final String HEAD_SIGN = "\uD83D\uDC3A";  // для отрисовки головы
    //private static final String BODY_SIGN = "\uD83D\uDC3A";   // для отрисовки хвоста (туловища)
    private static final String HEAD_SIGN = "\uD83D\uDC3A";  // для отрисовки головы
    private static final String BODY_SIGN = "\uD83D\uDC3A";   // для отрисовки хвоста (туловища)

    public boolean isAlive = true; ////хранение значения жив ли объект

    private Direction direction = Direction.LEFT;  // начальное положение змеи ( движение в лево)



    public List<GameObject> snakeParts = new ArrayList<>();   //Список всех сегментов змейки будем хранить в поле

    public Snake(int x, int y){   //конструктор
        snakeParts.add(new GameObject(x,y));    // три сегмента змейки ниже по началу игры
        snakeParts.add(new GameObject(x + 1,y));
    }

    public void draw(Game game){   //который должен отрисовывать змейку на игровом поле.
        for(int i = 0; i < snakeParts.size(); i++){   //проходимся по всему списку сементов змеи от 0 (хед ) до боди
            GameObject part = snakeParts.get(i);  //берем каждый сегмент
            String sign = (i != 0) ? BODY_SIGN : HEAD_SIGN;  //проверяет голова ли это
            if(isAlive){  //проверяет умерла ли змейка
                game.setCellValueEx(part.x, part.y, Color.NONE,sign , Color.GREEN, 75); // рисуем голову
            }
            else {
                game.setCellValueEx(part.x, part.y, Color.NONE,sign , Color.RED, 75);  // рисуем всё остальное
            }
        }

    }

    public void setDirection(Direction direction) {   //сеттер поля direction — setDirection(Direction), который устанавливает полю класса значение, полученное в качестве параметра.
        if (direction == Direction.UP && this.direction == Direction.DOWN) {
            return;
        } else if (direction == Direction.LEFT && this.direction == Direction.RIGHT) {
            return;
        } else if (direction == Direction.RIGHT && this.direction == Direction.LEFT) {
            return;
        } else if (direction == Direction.DOWN && this.direction == Direction.UP) {
            return;
        }

        if ((this.direction == Direction.LEFT || this.direction == Direction.RIGHT) && snakeParts.get(0).x == snakeParts.get(1).x) {
            return;
        }
        if ((this.direction == Direction.UP || this.direction == Direction.DOWN) && snakeParts.get(0).y == snakeParts.get(1).y) {
            return;
        }


        this.direction = direction;
    }

    public void move(Apple apple, Dead[] dead,Dead[] dead2){    // передвижеие змейки
        GameObject newHead = createNewHead(); //создание новой головы
        if(newHead.x >= SnakeGame.WIDTH   // проверяем вылезла ли голова змеи за пределы поля или нет
                || newHead.x < 0
                || newHead.y >= SnakeGame.HEIGHT
                || newHead.y < 0){
            isAlive = false;  // если да, то делеем ее мертвой
            return;
        }

        if(checkCollision(newHead)){  // вызываем проверку попадания в себя
            isAlive = false;  // умер
            return;
        }
        snakeParts.add(0, newHead);  // добоавлем новую голову
        for(int i = 0; i < 5; i++) {
            if (newHead.x == dead[i].x && newHead.y == dead[i].y) {
                dead[i].isAlive = false;
                removeTail();
            }
        }

        for(int i = 0; i < 5; i++) {
            if (newHead.x == dead2[i].x && newHead.y == dead2[i].y) {
                dead2[i].isAlive = false;
                removeTail();
            }
        }

        if (newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false;
        } else {
            removeTail();
        }
        // удаляем туловище последние
    }

    public  GameObject createNewHead(){  // проверяет куда поворачивает
        GameObject part = snakeParts.get(0);
        if(direction == Direction.LEFT){
            return new GameObject(part.x - 1,part.y);
        }
        else if(direction == Direction.RIGHT){
            return new GameObject(part.x + 1,part.y);
        }
        else if(direction == Direction.UP){
            return new GameObject(part.x ,part.y - 1);
        }
        else {
            return new GameObject(part.x,part.y + 1);
        }
    }

    public void removeTail(){  //удаляет поледниее туловище
        snakeParts.remove(snakeParts.size() - 1);

    }



    public boolean checkCollision(GameObject game){
        // GameObject  = snakeParts.get(0); // голова змеи
        for(int i = 0; i < snakeParts.size(); i++){
            GameObject snakePart = snakeParts.get(i);
            if(game.x == snakePart.x && game.y == snakePart.y) {  // сравниваем координаты
                return true;
            }
        }
        return false;

    }

    public int getLength(){
        return snakeParts.size();


    }  //возвращает количество сегментов змеи для выигрывша
}
