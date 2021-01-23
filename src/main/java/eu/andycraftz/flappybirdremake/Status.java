package eu.andycraftz.flappybirdremake;

public class Status {

    // Status
    private Typ status;

    public Status() {
        this.status = Typ.WAIT;
    }

    // Methoden
    public boolean Wait() {
        return this.status == Typ.WAIT;
    }

    public boolean Play() {
        return this.status == Typ.PLAY;
    }

    public boolean Stats() {
        return this.status == Typ.STATS;
    }

    public boolean GameOver() {
        return this.status == Typ.GAMEOVER;
    }

    public void Update(Typ s) {
        this.status = s;
    }

    // Status Typen
    public enum Typ {
        WAIT, PLAY, STATS, GAMEOVER
    }
}
