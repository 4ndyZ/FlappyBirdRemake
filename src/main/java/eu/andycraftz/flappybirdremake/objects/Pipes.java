package eu.andycraftz.flappybirdremake.objects;

import eu.andycraftz.flappybirdremake.Game;

import java.awt.Graphics;

import java.util.ArrayList;

public class Pipes {

    // Game
    private final Game game;
    // Pipen
    public final ArrayList<Pipe> pipes;
    private int gap;

    public Pipes(Game game) {
        // Game
        this.game = game;
        // Pipes
        pipes = new ArrayList<>();
        gap = 0;
    }

    // Methods
    public void Move() {
        gap--;

        if (gap < 0) {
            gap = 100;
            Pipe topPipe = null;
            Pipe buttomPipe = null;

            for (Pipe pipe : pipes) {
                if (pipe.x - pipe.width < 0) {
                    if (buttomPipe == null) {
                        buttomPipe = pipe;
                    } else if (topPipe == null) {
                        topPipe = pipe;
                        break;
                    }
                }
            }
            if (buttomPipe == null) {
                Pipe pipe = new Pipe(Pipe.Direction.DOWN, game);
                pipes.add(pipe);
                buttomPipe = pipe;
            } else {
                buttomPipe.Reset();
            }
            if (topPipe == null) {
                Pipe pipe = new Pipe(Pipe.Direction.UP, game);
                pipes.add(pipe);
                topPipe = pipe;
            } else {
                topPipe.Reset();
            }
            buttomPipe.y = topPipe.y + topPipe.height + 175; // 175 y gap between the pipes
        }

        pipes.forEach((pipe) -> {
            pipe.Move();
        });
    }

    // Paint
    public void Paint(Graphics g) {
        pipes.forEach((pipe) -> {
            pipe.Paint(g);
        });
    }
}
