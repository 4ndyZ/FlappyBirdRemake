package eu.andycraftz.flappybirdremake.stats;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.LinkedList;
import java.util.UUID;

public class TopUser {
    
    LinkedList<User> topusers;
    
    public TopUser() {
        topusers = new LinkedList<>();
        LinkedList<User> list = new LinkedList<>();
        if (Stats.mysql.Connected()) {
            ResultSet rs = Stats.mysql.Query("SELECT * FROM User ORDER BY Score desc LIMIT 3");
            try {
                while (rs.next()) {
                    list.add(new User(UUID.fromString(rs.getString("UUID"))));
                }
            } catch (SQLException err) {
                System.err.println("[MySQL] Error:" + err.getMessage());
            }
        }
        topusers = list;
    }
    
    public void update() {
        LinkedList<User> list = new LinkedList<>();
        if (Stats.mysql.Connected()) {
            ResultSet rs = Stats.mysql.Query("SELECT * FROM User ORDER BY Score desc LIMIT 3");
            try {
                while (rs.next()) {
                    list.add(new User(UUID.fromString(rs.getString("UUID"))));
                }
            } catch (SQLException err) {
                System.err.println("[MySQL] Error:" + err.getMessage());
            }
        }
        topusers = list;        
    }
    
    public LinkedList<User> getTopUser() {
        return topusers;
    }

}
