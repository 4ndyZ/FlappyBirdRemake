package eu.andycraftz.flappybirdremake.stats;

public class Stats {

    // MySQL
    public static MySQL mysql;

    public static void Main() {
        // MySQL
        mysql = new MySQL("#SERVER", "flappybird", "#PW", "flappybird");
        mysql.Connect();
    }
    
}
