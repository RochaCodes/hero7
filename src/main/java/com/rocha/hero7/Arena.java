package com.rocha.hero7;

import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(10, 10);
        this.walls = new ArrayList<>();
        createWalls();

    }
    private void createWalls() {
        for (int x = 0; x < width; x++) {
            walls.add(new Wall(x, height));
            walls.add(new Wall(x, 0));
        }
        for (int y = 1; y < height - 1; y++) {
            walls.add(new Wall(0, y));
            walls.add(new Wall(width, y));

        }


    }


    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
        }
    }


    private boolean canHeroMove(Position position) {
        if (position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height) {
            return false;
        }
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }
        return true;
    }



    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#8c2d19"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');

        hero.draw(graphics);
        for (Wall wall: walls){
            wall.draw(graphics);
        }
    }

    public void moveUp() {
        moveHero(hero.moveUp());
    }

    public void moveDown() {
        moveHero(hero.moveDown());
    }

    public void moveLeft() {
        moveHero(hero.moveLeft());
    }

    public void moveRight() {
        moveHero(hero.moveRight());
    }
}
