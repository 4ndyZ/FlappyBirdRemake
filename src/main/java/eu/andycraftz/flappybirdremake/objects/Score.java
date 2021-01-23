package eu.andycraftz.flappybirdremake.objects;

import eu.andycraftz.flappybirdremake.Game;
import eu.andycraftz.flappybirdremake.images.Image;

import java.awt.Graphics;

public class Score {

    private final Game game;

    public Score(Game game) {
        this.game = game;
    }

    // Paint
    public void Paint(Graphics g) {
        String scores = String.valueOf(game.score);
        for (int i = 1; i <= scores.length(); i++) {
            int zahl = Character.getNumericValue(scores.charAt(i-1));
            switch (zahl) {
                case 0:
                    g.drawImage(Image.null_, ((234 - (scores.length() - 1)*16)) + (i - 1) * 32, 50, 32, 50, null);
                    break;
                case 1:
                    g.drawImage(Image.one, ((234 - (scores.length() - 1)*16)) + (i - 1) * 32, 50, 32, 50, null);
                    break;
                case 2:
                    g.drawImage(Image.two, ((234 - (scores.length() - 1)*16)) + (i - 1) * 32, 50, 32, 50, null);
                    break;
                case 3:
                    g.drawImage(Image.three, ((234 - (scores.length() - 1)*16)) + (i - 1) * 32, 50, 32, 50, null);
                    break;
                case 4:
                    g.drawImage(Image.four, ((234 - (scores.length() - 1)*16)) + (i - 1) * 32, 50, 32, 50, null);
                    break;
                case 5:
                    g.drawImage(Image.five, ((234 - (scores.length() - 1)*16)) + (i - 1) * 32, 50, 32, 50, null);
                    break;
                case 6:
                    g.drawImage(Image.six, ((234 - (scores.length() - 1)*16)) + (i - 1) * 32, 50, 32, 50, null);
                    break;
                case 7:
                    g.drawImage(Image.seven, ((234 - (scores.length() - 1)*16)) + (i - 1) * 32, 50, 32, 50, null);
                    break;
                case 8:
                    g.drawImage(Image.eight, ((234 - (scores.length() - 1)*16)) + (i - 1) * 32, 50, 32, 50, null);
                    break;
                case 9:
                    g.drawImage(Image.nine, ((234 - (scores.length() - 1)*16)) + (i - 1) * 32, 50, 32, 50, null);
                    break;
                default:
                    break;
            }
        }
    }
}
