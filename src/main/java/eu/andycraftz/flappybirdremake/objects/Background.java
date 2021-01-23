package eu.andycraftz.flappybirdremake.objects;

import eu.andycraftz.flappybirdremake.Game;

import eu.andycraftz.flappybirdremake.images.Image;

import eu.andycraftz.utils.DateFormatter;

import java.awt.Graphics;

import java.text.SimpleDateFormat;

import java.util.Date;

public class Background {

    // Game
    private final Game game;
    // Image
    private final java.awt.Image image;

    public Background(Game game) {
        // Game
        this.game = game;
        // Background
        DateFormatter datef = new DateFormatter(new SimpleDateFormat("HH"));
        String zeit = datef.toString(new Date());
        int i = Integer.parseInt(zeit);
        if (i < 6 || i > 18) {
            image = Image.backgroundnight;
        } else {
            image = Image.background;
        }
    }

    // Paint
    public void Paint(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }
}
