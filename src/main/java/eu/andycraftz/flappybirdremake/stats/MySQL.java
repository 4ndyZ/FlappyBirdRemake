package eu.andycraftz.flappybirdremake.stats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    private final String host;
    private final String user;
    private final String pw;
    private final String db;
    private final int port;
    private Connection con;

    public MySQL(String host, String user, String pw, String db, int port) {
        this.host = host;
        this.user = user;
        this.pw = pw;
        this.db = db;
        this.port = port;
    }

    public MySQL(String host, String user, String pw, String db) {
        this(host, user, pw, db, 3306);
    }

    public MySQL(String user, String pw, String db) {
        this("localhost", user, pw, db, 3306);
    }

    public MySQL Connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db + "?autoReconnect=" + true, user, pw);

            if (!con.isClosed() && con != null) {
                // Erfolgreich
            } else {
                // Fail
            }
        } catch (SQLException err) {
            System.err.println("[MySQL] Konnte nicht verbinden: " + err.getMessage());
        } catch (ClassNotFoundException err) {
            System.err.println("[MySQL] Eine benötigte Klasse konnte nicht gefunden werden: " + err.getMessage());
        }
        return this;
    }

    public void Close() {
        try {
            if (!con.isClosed() && con != null) {
                con.close();
                if (con.isClosed()) {
                    System.out.println("[MySQL] Verbindung erfolgreich geschlossen!");
                } else {
                    System.err.println("[MySQL] Verbindung konnte nicht geschlossen werden!");
                }
            }
        } catch (SQLException err) {
            System.err.println("[MySQL] Verbindung konnte nicht geschlossen werden: " + err.getMessage());
         }
    }

    public void Update(String query) {
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException err) {
            System.err.println("[MySQL] Update Error: " + err.getMessage());
        }
    }

    public void MultiUpdate(String[] multiquery) {
        for (String query : multiquery) {
            Update(query);
        }
    }

    public ResultSet Query(String query) {
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
        } catch (SQLException err) {
            System.err.println("[MySQL] Query Error: " + err.getMessage());
        }
        return rs;
    }
    
    public boolean Connected() {
        try {
            return !con.isClosed() && con != null;
        } catch (SQLException err) {
            return false;    
        }
    }

    @Override
    public String toString() {
        return "[MySQL]: " + this.host + ":" + this.port + "-" + this.user + "(" + this.pw + ")/" + this.db;
    }
}
