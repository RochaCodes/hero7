package com.rocha.hero7;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TerminalPosition;

public abstract class Element {
    protected Position position;

    public Element(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    public abstract void draw(TextGraphics graphics);
}