package com.rocha.hero7;

public class Hero {
    private int x;
    private int y;
    public Hero (int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void moveUp() {
        y = Math.max(0, y -1);

    }
    public void moveDown(int maxHeight) {
        y = Math.min(maxHeight - 1, y + 1);
    }
    public void moveLeft(){
        x = Math.max(0, x - 1);
    }
    public void moveRight(int maxWidth){
        x = Math.min(maxWidth - 1, x + 1);
    }
}
