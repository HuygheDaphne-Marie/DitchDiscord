
package data;

import data.utils.MySQLConnection;
import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DitchDiscordException;

public class UserRepositorySQL implements UserRepository{
    public static final String FIELD_ID = "id";
    public static final String FIELD_USERNAME = "username";
    public static final String FIELD_PASSWORD = "password";
    
    private static final String GET_ALL_USERS = "SELECT * FROM ditchdiscord.user";
    private static final String GET_USERS_WITH_PASSWD_AND_USERNAME = "SELECT * FROM ditchdiscord.user WHERE username = ? AND password = ?";
    private static final String GET_USER_WITH_ID = "SELECT * FROM ditchdiscord.user WHERE id = ?";
//    private static final String GET_USER_WITH_USERNAME = "SELECT * FROM ditchdiscord.user WHERE username like ?";
    private static final String ADD_USER = "INSERT INTO ditchdiscord.user (username, password) VALUES(?, ?)";
//    private static final String DELETE_USER = "DELETE FROM ditchdiscord.user WHERE id = ? AND username = ? AND password = ?";

    @Override
    public List<User> getAllUsers() {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_ALL_USERS)) {
            
            try(ResultSet rs = stmt.executeQuery()) {
                List<User> users = new ArrayList<>();
                while(rs.next()) {
                    int id = rs.getInt(FIELD_ID);
                    String username = rs.getString(FIELD_USERNAME);
                    String password = rs.getString(FIELD_PASSWORD);
                    users.add(new User(id, username, password));
                }
                return users;
            }
            
        } catch (SQLException ex) {
            throw new DitchDiscordException("Couldn't get all users", ex);
        }
    }

    @Override
    public User getUserWithNameAndPassword(String name, String hashedPassword) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_USERS_WITH_PASSWD_AND_USERNAME)){
            stmt.setString(1, name);
            stmt.setString(2, hashedPassword);
            try(ResultSet rs = stmt.executeQuery()){
                User u = null;
                while (rs.next()){
                    int id = rs.getInt(FIELD_ID);
                    String username = rs.getString(FIELD_USERNAME);
                    String password = rs.getString(FIELD_PASSWORD);
                    u = new User(id, username, password);
                }
                return u;
            }
        } catch (SQLException ex) {
            throw new DitchDiscordException("Couldn't get user with name and password", ex);
        }
    }

    @Override
    public void AddUser(User u) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(ADD_USER)) {
            stmt.setString(1, u.getUsername());
            stmt.setString(2, u.getHashPassword());
            stmt.executeUpdate();
        } catch(SQLException ex) {
            throw new DitchDiscordException("Couldn't add user", ex);
        }
    }

    @Override
    public void deleteUser(User u) {
        
    }

    @Override
    public User getUserByUsername(String username) {
        try(Connection con = MySQLConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_USER_WITH_USERNAME)) {
            
            stmt.setString(1, username);
            try(ResultSet rs = stmt.executeQuery()) {
                User userWithUsername = null;
                if(rs.next()) {
                    int id = rs.getInt(FIELD_ID);
                    String password = rs.getString(FIELD_PASSWORD);
                    userWithUsername = new User(id, username, password);
                }
                return userWithUsername;
            }
        } catch(SQLException ex) {
            throw new DitchDiscordException("Couldn't get user with username", ex);
        }
    }
    
    
}
