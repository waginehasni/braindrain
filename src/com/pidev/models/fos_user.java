/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pidev.models;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author WSI
 */
public class fos_user {
    private int id;
    private String username;
    private String email;

    private String password;
    private String roles;
     private String telephone;

    public fos_user() {
    }

    public fos_user(int aInt, String string, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public fos_user(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public String toString() {
        return "fos_user{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", roles=" + roles +", telephone=" + telephone+ '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    

    public fos_user(String username, String email, String password, String roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public fos_user(int id, String username, String email, String password, String roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public fos_user(String username, String email, String password, String roles, String telephone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.telephone = telephone;
    }

    
}
