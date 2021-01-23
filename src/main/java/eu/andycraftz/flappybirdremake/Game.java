package eu.andycraftz.flappybirdremake;

import eu.andycraftz.flappybirdremake.images.Image;

import eu.andycraftz.flappybirdremake.objects.Background;
import eu.andycraftz.flappybirdremake.objects.Bird;
import eu.andycraftz.flappybirdremake.objects.Foreground;
import eu.andycraftz.flappybirdremake.objects.Pipe;
import eu.andycraftz.flappybirdremake.objects.Pipes;
import eu.andycraftz.flappybirdremake.objects.Score;

import eu.andycraftz.flappybirdremake.sound.Sound;

import eu.andycraftz.flappybirdremake.stats.TopUser;
import eu.andycraftz.flappybirdremake.stats.User;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Game extends JPanel {

    // Status
    public Status status;
    // Thread
    Thread t;
    // Bird
    private Bird bird;
    // Background
    private Background background;
    // Foreground
    private Foreground foreground;
    // Pipes
    private Pipes pipes;
    // User
    //private final User user;
    //private final TopUser topuser;
    // Score
    public int score;
    private Score scorecounter;
    // Keys
    public boolean space;
    // Bolean
    private boolean restart;
    // Button
    private final JButton stats;
    private final JButton play;
    private final JButton ok;
    //
    
    public Game() {
        // Focusierbar setzen (sonst geht allen anschein nach der KeyListener nicht)
        setFocusable(true);
        // Status
        status = new Status();
        status.Update(Status.Typ.WAIT);
        // Thread
        t = new Thread(new Runner(this), "Game");
        // Listener
        addKeyListener(new TypeL());
        addMouseListener(new MouseL());
        // Bird
        bird = new Bird(this);
        // Background
        background = new Background(this);
        //Foreground
        foreground = new Foreground(this);
        // Pipes
        pipes = new Pipes(this);
        // User
        //user = new User();
        //user.setName(System.getProperty("user.name"));
        //topuser = new TopUser();
        // Score
        scorecounter = new Score(this);
        score = 0; // Start score
        // Keys
        space = false;
        // Boolean
        restart = false;
        // Button
        ok = new JButton(new ImageIcon(Image.ok));
        ok.setBorder(BorderFactory.createEmptyBorder());
        ok.setContentAreaFilled(false);
        ok.setVisible(false);
        ok.setLayout(null);
        ok.setDoubleBuffered(true);
        ok.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                status.Update(Status.Typ.WAIT);
                Sound.swooshing.play();
            }
        });
        play = new JButton(new ImageIcon(Image.play));
        play.setBorder(BorderFactory.createEmptyBorder());
        play.setContentAreaFilled(false);
        play.setVisible(false);
        play.setLayout(null);
        play.setDoubleBuffered(true);
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (status.Wait()) {
                    reset();
                    status.Update(Status.Typ.PLAY);
                }
                Sound.swooshing.play();
            }
        });
        stats = new JButton(new ImageIcon(Image.stats));
        stats.setBorder(BorderFactory.createEmptyBorder());
        stats.setContentAreaFilled(false);
        stats.setVisible(false);
        stats.setLayout(null);
        stats.setDoubleBuffered(true);
        stats.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                status.Update(Status.Typ.STATS);
                Sound.swooshing.play();
            }
        });
        add(stats);
        add(play);
        add(ok);
        // Start Timer
        t.start();
    }

    public void reset() {
        if (!restart) {
            return;
        }
        // Bird
        bird = new Bird(this);
        // Background
        background = new Background(this);
        // Foreground
        foreground = new Foreground(this);
        // Pipes
        pipes = new Pipes(this);
        // Score
        scorecounter = new Score(this);
        score = 0; // Start score
        // Keys
        space = false;
        // Boolean
        restart = false;
    }

    // Methods
    @Override
    protected void paintComponent(Graphics g) {
        background.Paint(g);
        if (status.Wait()) {
            play.setBounds(73, 300, play.getIcon().getIconWidth(), play.getIcon().getIconHeight());
            stats.setBounds(323, 300, stats.getIcon().getIconWidth(), stats.getIcon().getIconHeight());
            if (ok.isVisible()) {
                ok.setVisible(false);
            }
            if (!stats.isVisible()) {
                stats.setVisible(true);
            }
            if (!play.isVisible()) {
                play.setVisible(true);
            }
            g.drawImage(Image.logo, 161, 50, null);
            bird.Paint(g);
        } else if (status.Play()) {
            if (ok.isVisible()) {
                ok.setVisible(false);
            }
            if (stats.isVisible()) {
                stats.setVisible(false);
            }
            if (play.isVisible()) {
                play.setVisible(false);
            }
            pipes.Paint(g);
            scorecounter.Paint(g);
            bird.Paint(g);
        } else if (status.GameOver()) {
            ok.setBounds(210, 300, ok.getIcon().getIconWidth(), ok.getIcon().getIconHeight());
            pipes.Paint(g);
            bird.Paint(g);
            if (score >= 100) {
                g.drawImage(Image.stats_gold, 137, 160, null);
            } else if (score >= 50) {
                g.drawImage(Image.stats_silber, 137, 160, null);
            } else if (score >= 25) {
                g.drawImage(Image.stats_bronze, 137, 160, null);
            } else {
                g.drawImage(Image.stats_null, 137, 160, null);
            }
            g.drawImage(Image.gameover, 161, 50, null);
            g.setFont(new Font("TimesRoman", Font.BOLD, 15));
            g.setColor(Color.decode("#fc7858"));
            g.drawString("MEDAL", 160, 190);
            g.drawString("BEST", 250, 190);
            g.drawString("SCORE", 250, 230);
            g.setFont(new Font("TimesRoman", Font.BOLD, 12));
            g.setColor(Color.decode("#fc7858"));
            //g.drawString(String.valueOf(user.getScore()), 260, 210);
            g.drawString(String.valueOf(score), 260, 250);
            if (!ok.isVisible()) {
                ok.setVisible(true);
            }
            if (stats.isVisible()) {
                stats.setVisible(false);
            }
            if (play.isVisible()) {
                play.setVisible(false);
            }
        } else if (status.Stats()) {
            ok.setBounds(210, 300, ok.getIcon().getIconWidth(), ok.getIcon().getIconHeight());
            /*if (user.getScore() >= 100) {
                g.drawImage(Image.stats_gold, 137, 50, null);
            } else if (user.getScore() >= 50) {
                g.drawImage(Image.stats_silber, 137, 50, null);
            } else if (user.getScore() >= 25) {
                g.drawImage(Image.stats_bronze, 137, 50, null);
            } else {
                g.drawImage(Image.stats_nichts, 137, 50, null);
            }*/
            g.setFont(new Font("TimesRoman", Font.BOLD, 15));
            g.setColor(Color.decode("#fc7858"));
            g.drawString("MEDAL", 160, 80);
            g.drawString("BEST", 250, 80);
            g.setFont(new Font("TimesRoman", Font.BOLD, 12));
            g.setColor(Color.decode("#fc7858"));
            //g.drawString(String.valueOf(user.getScore()), 260, 100);
            g.drawImage(Image.stats_top, 137, 175, null);
            /*if (topuser.getTopUser().isEmpty()) {
                g.setFont(new Font("TimesRoman", Font.BOLD, 15));
                g.setColor(Color.decode("#fc7858"));
                g.drawString("TOP:", 150, 200);
                g.drawString("/", 165, 200 + 210);
            } else {
                int i = 1;
                for (User users : topuser.getTopUser()) {
                    g.setFont(new Font("TimesRoman", Font.BOLD, 12));
                    g.setColor(Color.decode("#fc7858"));
                    g.drawString("TOP:", 150, 200);
                    g.drawString("[" + i + "] " + users.getName() + " - " + users.getScore(), 165, 200 + i * 20);
                    i++;
                }
            }*/
            if (!ok.isVisible()) {
                ok.setVisible(true);
            }
            if (stats.isVisible()) {
                stats.setVisible(false);
            }
            if (play.isVisible()) {
                play.setVisible(false);
            }
        }
        foreground.Paint(g);
    }
    
    // Methoden 
    public void updateGame() {
        if (status.Wait()) {
            bird.WingsWait();
        } else if (status.Play()) {
            bird.Move();
            bird.Wings();
            pipes.Move();  
            // Check Crash mit Boden
            if (bird.y + bird.height + 2 >= FlappyBird.Height - 80) {
                restart = true;
                status.Update(Status.Typ.GAMEOVER);
                Sound.hit.play();
                Thread tstats = new Thread(() -> {
                    /*if (score > user.getScore()) {
                    user.setScore(score);
                    }
                    topuser.update();*/
                }, "Stats");
                tstats.setPriority(Thread.MAX_PRIORITY);
                tstats.start();
                return;
            }
            // Check Crash mit Stange
            pipes.pipes.forEach((pipe) -> {
                if (pipe.Touch(bird.x, bird.y, bird.width, bird.height)) {
                    restart = true;
                    status.Update(Status.Typ.GAMEOVER);
                    Sound.hit.play();
                    Thread tstats = new Thread(() -> {
                        /*if (score > user.getScore()) {
                        user.setScore(score);
                        }
                        topuser.update();*/
                    }, "Stats");
                    tstats.setPriority(Thread.MAX_PRIORITY);
                    tstats.start();
                } else if (pipe.x == bird.x && pipe.direction == Pipe.Direction.UP) {
                    Sound.point.play();
                    score++; // Add score
                }
            });
        } else if (status.GameOver()) {
            bird.Move();
        } 
    }    

    // Key- & MouseAdapter
    private class TypeL extends KeyAdapter {

        public TypeL() {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            // Control using space, W and arrow key up
            if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_W || key  == KeyEvent.VK_UP) {
                if (status.Play()) {
                    space = true;
                }
            }
        }
    }
    
    private class MouseL extends MouseAdapter {

        public MouseL() {
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            int button  = e.getButton();
            // Control using left mouse button
            if (button == MouseEvent.BUTTON1) {
                if (status.Play()) {
                    space = true;
                }
            }
        }
    }
}
