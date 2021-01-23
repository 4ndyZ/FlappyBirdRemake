package eu.andycraftz.flappybirdremake.images;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

    // Images for objects
    public static final java.awt.Image background = new Image("background.png").image;
    public static final java.awt.Image backgroundnight = new Image("backgroundnight.png").image;
    public static final java.awt.Image bird1 = new Image("bird1.png").image;
    public static final java.awt.Image bird2 = new Image("bird2.png").image;
    public static final java.awt.Image bird3 = new Image("bird3.png").image;
    public static final java.awt.Image foreground = new Image("foreground.png").image;
    public static final java.awt.Image toppipe = new Image("toppipe.png").image;
    public static final java.awt.Image buttompipe = new Image("buttompipe.png").image;
    // Others
    public static final java.awt.Image logo = new Image("logo.png").image;
    public static final java.awt.Image icon = new Image("icon.png").image;
    public static final java.awt.Image gameover = new Image("gameover.png").image;
    public static final java.awt.Image play = new Image("play.png").image;
    public static final java.awt.Image ok = new Image("ok.png").image;
    // Stats
    public static final java.awt.Image stats = new Image("stats.png").image;
    public static final java.awt.Image stats_top = new Image("stats2.png").image;
    public static final java.awt.Image stats_null = new Image("stats1.png").image;
    public static final java.awt.Image stats_bronze = new Image("statsbronze.png").image;
    public static final java.awt.Image stats_silber = new Image("statssilber.png").image;
    public static final java.awt.Image stats_gold = new Image("statsgold.png").image;
    // Numbers
    public static final java.awt.Image null_ = new Image("0.png").image;
    public static final java.awt.Image one = new Image("1.png").image;
    public static final java.awt.Image two = new Image("2.png").image;
    public static final java.awt.Image three = new Image("3.png").image;
    public static final java.awt.Image four = new Image("4.png").image;
    public static final java.awt.Image five = new Image("5.png").image;
    public static final java.awt.Image six = new Image("6.png").image;
    public static final java.awt.Image seven = new Image("7.png").image;
    public static final java.awt.Image eight = new Image("8.png").image;
    public static final java.awt.Image nine = new Image("9.png").image;

    // Image
    private java.awt.Image image;

    private Image(String name) {
        try {
            this.image = ImageIO.read(getClass().getClassLoader().getResource(name));
        } catch (NumberFormatException | IOException err) {
            err.printStackTrace();
        }
    }
}
