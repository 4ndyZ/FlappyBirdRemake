package eu.andycraftz.flappybirdremake.objects;

import eu.andycraftz.flappybirdremake.Game;
import eu.andycraftz.flappybirdremake.images.Image;

import java.awt.Graphics;

public class Foreground {

    // Game
    private final Game game;
    // Image
    private final java.awt.Image image;

    public Foreground(Game game) {
        // Game
        this.game = game;
        // Image
        image = Image.foreground;
    }

    // Paint
    public void Paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
