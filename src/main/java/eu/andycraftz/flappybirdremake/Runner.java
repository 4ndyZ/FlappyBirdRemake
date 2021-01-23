package eu.andycraftz.flappybirdremake;

public class Runner implements Runnable{

    private Game game;
    
    public Runner(Game game) {
        this.game = game;
    }
    
    final int FPS = 120;
    final int SKIP_TICKS = 2000 / FPS;
    final int MAX_FRAMESKIP = 5;

    @Override
    public void run() {
        double next_game_tick = System.currentTimeMillis();
        int loops;

        while (true) {
            loops = 0;
            while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {
                // Game Updaten
                game.updateGame();
                // Rechnen
                next_game_tick += SKIP_TICKS;
                loops++;
            }
            // Graphic Repainten
            game.repaint();
        }
    }
}
