package com.rocha.hero7;

import com.googlecode.lanterna.graphics.TextGraphics;

public class Arena {
    private int width;
    private int height;
    private Hero hero;

    // Constructor initializes the arena size and places the hero inside it
    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(10, 10); // Start position can be adjusted as needed
    }

    // Method to move the hero to a new position
    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
        }
    }


    private boolean canHeroMove(Position position) {
        return position.getX() >= 0 && position.getX() < width &&
                position.getY() >= 0 && position.getY() < height;
    }


    public void draw(TextGraphics graphics) {
        hero.draw(graphics);
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
