/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.utils.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.DitchDiscordException;

/**
 *
 * @author Michiel
 */
public class ComplaintsMySQL implements ComplaintsRepository
{
private static final String ADD_USER = "INSERT INTO ditchdiscord.complaints (complaintfrom, complainttype,complaint) VALUES(?, ?, ?)";
    

@Override
    public void AddComplaint(String name, String type, String complaint) 
    {      try (Connection con = MySQLConnection.getConnection();
                PreparedStatement stmt = con.prepareStatement(ADD_USER)) {
            stmt.setString(1,name);
            stmt.setString(2, type);
            stmt.setString(3,complaint);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DitchDiscordException("Couldn't add complaint", ex);
        }
    
        
    }
    
}
