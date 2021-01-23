package eu.andycraftz.flappybirdremake.stats;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Properties;
import java.util.UUID;

public class User {
    
    private UUID uuid;
    private int score;
    private String name;

    public User() {
        File f = new File(getDir() + File.separator + "data.yml");
        if (!f.exists()) {
            Properties prop = new Properties();
            OutputStream output = null;
            try {
                uuid = UUID.randomUUID(); // UUID
                output = new FileOutputStream(getDir() + File.separator + "data.yml"); // Output stream for data file
                prop.setProperty("UUID", uuid.toString()); // Set UUID
                prop.setProperty("Name", "Spieler");
                name = "Spieler";
                prop.setProperty("Score", String.valueOf(0));
                score = 0;
                prop.store(output, null); // Data file
            } catch (IOException err) {
                err.printStackTrace();
            } finally {
                if (output != null) {
                    try {
                        output.close();
                    } catch (IOException err) {
                        err.printStackTrace();
                    }
                }
            }
        }
        else {
            Properties prop = new Properties();
            InputStream input = null;
            try {
                input = new FileInputStream(getDir() + File.separator + "data.yml");
                prop.load(input);
                uuid = UUID.fromString(prop.getProperty("UUID"));
                name = prop.getProperty("Name");
                score = Integer.valueOf(prop.getProperty("Score"));
            } catch (IOException err) {
                err.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException err) {
                        err.printStackTrace();
                    }
                }
            }      
        }

        if (Stats.mysql.Connected()) {
            if (AccountExist()) {
                Stats.mysql.MultiUpdate(new String[] {
                    "UPDATE `User` SET `Name` = '" + name + "' WHERE `UUID` = '" + uuid.toString() + "'",
                    "UPDATE `User` SET `Score` = '" + score + "' WHERE `UUID` = '" + uuid.toString() + "'"
                });
            }
            else {
                Stats.mysql.Update("INSERT INTO `User` (`UUID`, `Name`, `Score`) VALUES ('" + uuid.toString() + "','" + name + "','" + score + "')");
            }
        }
    }
    
    public User(UUID uuid) {
        this.uuid = uuid;
        if (Stats.mysql.Connected()) {
            if (AccountExist()) {
                try {
                    ResultSet rs = Stats.mysql.Query("SELECT * FROM `User` WHERE `UUID`='" + uuid.toString() + "'");
                    if (rs.next()) {
                        name = rs.getString("Name");
                        score = rs.getInt("Score");
                    }
                } catch (SQLException err) {
                    err.printStackTrace();
                }
            }
        }
    }

    private String getDir() {
        String dir;
        if (System.getProperty("os.name").toLowerCase().contains("win")) {
            dir = System.getenv("APPDATA") + File.separator + "Flappybird";
        } else {
            dir = System.getProperty("user.home") + File.separator + "Flappybird";
        }
        return dir;
    }

    private void Folder() {
        File folder = new File(this.getDir());
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
    
    private void saveConfig() {
        Properties prop = new Properties();
        OutputStream output = null;
        try {
            output = new FileOutputStream(getDir() + File.separator + "data.yml"); // Output Stream für Data File
            prop.setProperty("UUID", uuid.toString());
            prop.setProperty("Name", name);
            prop.setProperty("Score", String.valueOf(score));
            prop.store(output, null); // Data File
        } catch (IOException err) {
            err.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        }
    }

    private boolean AccountExist() {
        boolean acc = false;
        try {
            ResultSet rs = Stats.mysql.Query("SELECT * FROM `User` WHERE `UUID`='" + uuid.toString() + "'");
            if (rs.next()) {
                acc = true;
            }
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return acc;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void setScore(int score) {
        this.score = score;
        if (Stats.mysql.Connected()) {
            Stats.mysql.Update("UPDATE `User` SET `Score` = '" + this.score + "' WHERE `UUID` = '" + uuid.toString() + "'");
        }
        saveConfig();
    }
    
    public void setName(String name) {
        this.name = name;
        if (Stats.mysql.Connected()) {
            Stats.mysql.Update("UPDATE `User` SET `Name` = '" + this.name + "' WHERE `UUID` = '" + uuid.toString() + "'");
        }
        saveConfig();
    }
}
