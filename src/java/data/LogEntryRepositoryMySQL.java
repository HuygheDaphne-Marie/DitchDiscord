
package data;

import data.utils.MySQLConnection;
import domain.LogEntry;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.DitchDiscordException;

public class LogEntryRepositoryMySQL implements LogEntryRepository{
    private static final String ADD_TO_THE_DATABASE = "INSERT INTO log(logMessage) VALUES(?)";
    private static final String GET_ALL_FROM_STATEMENT = "SELECT * FROM log ORDER BY time DESC";

    
    @Override
    public void addToDataBase(LogEntry log){
        try(Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(ADD_TO_THE_DATABASE)){
            stmt.setString(1, log.getMessage());
            stmt.executeUpdate();
        }catch(SQLException ex){
            throw new DitchDiscordException("Couldn't add the logging entry into the database", ex);
        }
    }
    
    @Override
    public List<LogEntry> getAllFromDataBase(){
        try(Connection conn = MySQLConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(GET_ALL_FROM_STATEMENT); 
            ResultSet res = stmt.executeQuery()){
            List<LogEntry> logList = new ArrayList();
            while(res.next()){
                int id = res.getInt("id");
                String date = res.getDate("time").toString();
                String time = res.getTime("time").toString();
                String message = res.getString("logMessage");
                LogEntry log = new LogEntry(message, id, date+" "+time);
                logList.add(log);
            }
            return logList;
        }catch(SQLException ex){
            throw new DitchDiscordException("Couldn't retrieve the logging entry from the database", ex);
        }
    }
}
