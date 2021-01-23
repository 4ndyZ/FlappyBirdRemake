package eu.andycraftz.flappybirdremake;

import eu.andycraftz.flappybirdremake.images.Image;
import eu.andycraftz.flappybirdremake.stats.Stats;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Flappy Bird Remake
 *
 * @version 1.0
 * @author AndyZ (AndyCraftz)
 */
public class FlappyBird extends JFrame {

    public static int Width = 500;
    public static int Height = 500;

    public FlappyBird() {
        // Game
        add(new Game());
        setTitle("Flappy Bird");
        setIconImage(Image.icon);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(FlappyBird.Width, FlappyBird.Height);
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        // Window Look
        String look = "";
        if (!System.getProperty("os.name").toLowerCase().contains("win")) {
            look = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        } else {
            look = "javax.swing.plaf.metal.MetalLookAndFeel";
        }
        try {
            UIManager.setLookAndFeel(look);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException err) {
        }
        // Main Thread
        new Thread(() -> {
            //Stats.Main();
            JFrame flappybird = new FlappyBird();
            flappybird.setVisible(true);
        }, "Main").start();
    }
}