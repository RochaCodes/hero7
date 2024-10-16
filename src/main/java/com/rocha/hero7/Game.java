package com.rocha.hero7;


import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Game {
    private int x = 10;
    private int y = 10;
    private Screen screen;
    private Hero hero;


    public Game() {
        this.hero = new Hero(10,10);
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            screen = terminalFactory.createScreen();
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen.newTextGraphics());
        screen.refresh();
    }
    private void processKey(KeyStroke key) {
        Position newPosition = null;
        switch (key.getKeyType()) {
            case ArrowUp:
                newPosition = hero.moveUp();
                break;
            case ArrowDown:
                newPosition = hero.moveDown();
                break;
            case ArrowLeft:
                newPosition = hero.moveLeft();
                break;
            case ArrowRight:
                newPosition = hero.moveRight();
                break;
            default:
                break;
        }
        if (newPosition != null) {
            hero.setPosition(newPosition);
        }
    }


    public void run() {
        try {
            while (true) {
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                    screen.close();
                    break;
                }
                if (key.getKeyType() == KeyType.EOF) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
