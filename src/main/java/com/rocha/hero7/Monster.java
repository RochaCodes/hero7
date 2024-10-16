package com.rocha.hero7;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TerminalPosition;

import java.util.Random;

public class Monster extends Element {
    private Random random;

    public Monster(int x, int y) {
        super(x, y);
        random = new Random();
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000")); // Red color
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }

    public Position move() {

        int direction = random.nextInt(4);
        switch (direction) {
            case 0:
                return new Position(position.getX(), position.getY() - 1);
            case 1:
                return new Position(position.getX(), position.getY() + 1);
            case 2:
                return new Position(position.getX() - 1, position.getY());
            case 3:
                return new Position(position.getX() + 1, position.getY());
            default:
                return position; // No movement
        }
    }
}