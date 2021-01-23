package eu.andycraftz.flappybirdremake.objects;

import eu.andycraftz.flappybirdremake.Game;
import eu.andycraftz.flappybirdremake.images.Image;

import java.awt.Graphics;

public class Pipe {

    // Game
    private final Game game;
    // Dimensions
    public int width;
    public int height;
    // Coords
    public int x;
    public int y;
    // Direction
    public Direction direction;

    public enum Direction {
        UP, DOWN
    }
    // Speed
    public int speed = 3;

    public Pipe(Direction direction, Game game) {
        // Game
        this.game = game;
        // Direction
        this.direction = direction;
        // Dimensions
        width = 66;
        height = 400;
        // Coords
        x = 500 + 2;
        if (direction == Direction.UP) {
            y = -(int) (Math.random() * 120) - (height / 2);
        }
    }
    
    // Methods
    public void Reset() {
        // Dimensions
        width = 66;
        height = 400;
        // Coords
        x = 500 + 2;
        if (direction == Direction.UP) {
            y = -(int) (Math.random() * 120) - (height / 2);
        }
    }

    public void Move() {
        x -= speed;
    }

    public boolean Touch(int x, int y, int width, int height) {
        if ((x + width - 2 > this.x) && (x + 2 < this.x + this.width)) {
            if ((this.direction == Direction.UP) && (y < this.y + this.height)) {
                return true;
            } else if ((this.direction == Direction.DOWN) && (y + height > this.y)) {
                return true;
            }
        }
        return false;
    }

    // Paint
    public void Paint(Graphics g) {
        if (direction == Direction.UP) {
            g.drawImage(Image.toppipe, x, y, null);
        } else if (direction == Direction.DOWN) {
            g.drawImage(Image.buttompipe, x, y, null);
        }
    }
}
