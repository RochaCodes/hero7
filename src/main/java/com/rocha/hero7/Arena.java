package com.rocha.hero7;

import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.util.Random;


public class Arena {
        private int width;
        private int height;
        private Hero hero;
        private List<Wall> walls;
        private List<Coin> coins;
        private List<Monster> monsters;

        public Arena(int width, int height) {
            this.width = width;
            this.height = height;
            this.hero = new Hero(10, 10);
            this.walls = new ArrayList<>();
            this.coins = createCoins();
            this.monsters = createMonsters();
            createWalls();

        }
        private List<Monster> createMonsters() {
            List<Monster> monsters = new ArrayList<>();
            monsters.add(new Monster(5, 5)); 
            monsters.add(new Monster(15, 10));
            return monsters;
        }
        private List<Coin> createCoins() {
            List<Coin> coins = new ArrayList<>();
            Random random = new Random();

            while (coins.size() < 5) {
                int x = random.nextInt(width - 2) + 1;
                int y = random.nextInt(height - 2) + 1;
                Position position = new Position(x, y);


                boolean isValid = true;
                if (position.equals(hero.getPosition())) {
                    isValid = false;
                }
                for (Coin coin : coins) {
                    if (coin.getPosition().equals(position)) {
                        isValid = false;
                        break;
                    }
                }
                if (isValid) {
                    coins.add(new Coin(x, y));
                }
            }
            return coins;
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
        private boolean canMonsterMove(Position position) {
            if (position.getX() < 0 || position.getX() >= width || position.getY() < 0 || position.getY() >= height) {
                return false;
            }

            for (Wall wall : walls) {
                if (wall.getPosition().equals(position)) {
                    return false;
                }
            }
            return true;}

    private void verifyMonsterCollisions() {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(hero.getPosition())) {
                System.out.println("Game Over! You were caught by a monster.");
                System.exit(0);
            }
        }
    }

        private void retrieveCoins() {
            coins.removeIf(coin -> coin.getPosition().equals(hero.getPosition()));
        }

        public void moveHero(Position position) {
            if (canHeroMove(position)) {
                hero.setPosition(position);
                retrieveCoins();
                verifyMonsterCollisions();
            }
        }
        public void moveMonsters() {
            for (Monster monster : monsters) {
                Position newPosition = monster.move();
                if (canMonsterMove(newPosition)) {
                    monster.setPosition(newPosition);
            }
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
            for (Coin coin: coins) {
                coin.draw(graphics);
            }
            for (Monster monster : monsters) {
                monster.draw(graphics);
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