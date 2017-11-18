/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.Blob;
import static java.sql.Types.BLOB;


/**
 *
 * @author Henri
 */
public class User 
{
 private int id;
 private String name;
 private String password;
 // private Blob profielfoto;

    public User(int id, String name, String password, Blob profielfoto) {
        this.id = id;
        this.name = name;
        this.password = password;
        // this.profielfoto = profielfoto;
    }
    
//    public User(int id, String name, String password) {
//        this(-1, name, password, null);
//    }

    public User(int id, String name, String password) {
        this(id, name, password, null);
    }
    
    public User(String name, String password) {
        this(-1, name, password);
    }
    
    public User(String name){
        this(name, null);
    }

    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

//    public Blob getProfielfoto() {
//        return profielfoto;
//    }

}
