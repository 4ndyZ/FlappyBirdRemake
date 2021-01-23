package eu.andycraftz.flappybirdremake.objects;

import eu.andycraftz.flappybirdremake.FlappyBird;
import eu.andycraftz.flappybirdremake.Game;
import eu.andycraftz.flappybirdremake.images.Image;
import eu.andycraftz.flappybirdremake.sound.Sound;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Bird {

    // Game
    private final Game game;
    // Dimensions
    public int width;
    public int height;
    // Coords
    public int x;
    public int y;
    private final int y2;
    private final int x2;
    // Properties
    public double yvel;
    public double gravity;
    private int jumpDelay;
    // Image
    private java.awt.Image image;
    private java.awt.Image image2;
    private AffineTransform trans;
    private int delay;
    private int delaywait;
    private double rotation;
    public boolean wait;

    public Bird(Game game) {
        // Game
        this.game = game;
        // Dimensions
        width = 45;
        height = 32;
        // Coords
        x = 100;
        y = 150;
        x2 = 227;
        y2 = 180;
        // Properties
        yvel = 0;
        gravity = 0.5;
        jumpDelay = 0;
        // Image
        image = Image.bird2;
        image2 = Image.bird2;
        delay = 1;
        delaywait = 1;
        wait = true;
    }

    // Methods
    public void Move() {
        yvel += gravity;

        if (game.status.Play()) {
            if (jumpDelay > 0) {
                jumpDelay -= 1;
            }

            if (game.space && jumpDelay <= 0) {
                Sound.click.play();
                yvel = -10;
                jumpDelay = 10;
                game.space = false;
            }
        }
        
        if (y + height <= FlappyBird.Height - 80) { // Check so the bird doesn't disappear
            y += (int) yvel;
        }
    }

    public void WingsWait() {
        if (delaywait > 0) {
            delaywait -= 1;
            return;
        }
        delaywait = 4;
        if (image2 == Image.bird1) {
            image2 = Image.bird2;
        } else if (image2 == Image.bird2) {
            image2 = Image.bird3;
        } else if (image2 == Image.bird3) {
            image2 = Image.bird1;
        }
    }

    public void Wings() {
        if (wait) {
            wait = false;
        }
        if (delay > 0) {
            delay -= 1;
            return;
        }
        delay = 3;
        if (image == Image.bird1) {
            image = Image.bird2;
        } else if (image == Image.bird2) {
            image = Image.bird3;
        } else if (image == Image.bird3) {
            image = Image.bird1;
        }
    }

    // Paint  
    public void Paint(Graphics g) {
        if (wait) {
            g.drawImage(image2, x2, y2, null);
            return;
        }
        rotation = (90 * (yvel + 20) / 20) - 90;
        rotation = rotation * Math.PI / 180;

        if (rotation > Math.PI / 2) {
            rotation = Math.PI / 2;
        }

        trans = new AffineTransform();
        trans.translate(x + width / 2, y + height / 2);
        trans.rotate(rotation);
        trans.translate(-width / 2, -height / 2);

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, trans, null);
    }
}
