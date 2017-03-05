/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.classes;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mukolwe Michael
 */
public class database implements Serializable{
    private static Connection connection;

    public void newConn() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutorial", "root", "");
    }
    //Jstl SQL Connector
    public String jstlDriver() {
        String jDriver = "com.mysql.jdbc.Driver";        
        return jDriver;
    }
    public String jstlUrl(){
        String jUrl = "jdbc:mysql://localhost:3306/tutorial";
        return jUrl;
    }
    public String jstlUser(){
        String jUser = "root";
        return jUser;
    }
    public String jstlPassword(){
        String jPassword = "";
        return jPassword;
    }
    
    //   post the question
    public int postQuestion(String user, String blog,String date) throws SQLException, Exception {
        newConn();
        Statement stmt = connection.createStatement();
        int postStatus = stmt.executeUpdate("INSERT INTO blogpost VALUES(null,'" + user + "','" + blog + "','" +date +"')");
        return postStatus;
    }
    
    //Select all Blog Post
    public String blogsPosted() {
        String query = "SELECT * from blogpost";
        return query;
    }
     
}
