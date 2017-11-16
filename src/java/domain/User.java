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
 private String name;
 private String password;
 private Blob profielfoto;

    public User(String name, String password, Blob profielfoto) {
        this.name = name;
        this.password = password;
        this.profielfoto = profielfoto;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public Blob getProfielfoto() {
        return profielfoto;
    }

 
 
 
    
}
