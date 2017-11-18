
package data.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import util.DitchDiscordException;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost/ditchdiscord";
    private static final String USR = "ditchAdmin";
    private static final String PWD = "d33z_nu7z";
    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex) {
            throw new DitchDiscordException("Unable to load database driver.", ex);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USR, PWD);
    }
}
