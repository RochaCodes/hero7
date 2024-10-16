package com.rocha.hero7;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.TerminalPosition;

public class Coin extends Element {
    public Coin(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFD700"));
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "C");
    }
}