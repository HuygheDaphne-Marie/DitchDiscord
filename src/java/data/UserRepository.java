/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import domain.User;
import java.util.List;

/**
 *
 * @author Henri
 */
public interface UserRepository {
    public List<User> getAllUsers();
    public User getUserWithNameAndPassword(String name, String hashedPassword);
    public User getUserByUsername(String username);
    public User getUserById(int id);
    public void AddUser(User u);
    public void deleteUser(User u);
    public void AddPicture(String path,String name); 
}
