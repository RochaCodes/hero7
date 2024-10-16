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


    public Game() {
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
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
    }
    private void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowUp:
                y = Math.max(0, y - 1);
                break;
            case ArrowDown:
                y = Math.min(screen.getTerminalSize().getRows() - 1, y + 1);
                break;
            case ArrowLeft:
                x = Math.max(0, x - 1);
                break;
            case ArrowRight:
                x = Math.min(screen.getTerminalSize().getColumns() - 1, x + 1);
                break;
            default:
                break;
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
