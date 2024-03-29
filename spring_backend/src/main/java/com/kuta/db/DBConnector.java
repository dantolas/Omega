package com.kuta.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Serves as custom wrapper for MySQL JDBC Connector | see {@link https://dev.mysql.com/downloads/connector/j/}. 
 * Provides functionality to connect to database, and execute queries.
 */
public class DBConnector {

    public abstract class Prefix{
        public static final String MYSQL = "jdbc:mysql://";
    }
    public boolean isInitialized = false;

    public String username;
    public String password;
    public String connectionUrl;

    public DBConnector(){

    }

    public DBConnector setUsername(String username){
        this.username = username;
        return this;
    }

    public DBConnector setPassword(String password){
        this.password = password;
        return this;
    }

    public DBConnector setConnectionUrl(String prefix, String hostname,String dbName){
        this.connectionUrl = prefix + hostname + "/" + dbName;
        return this;
    }


    /**
     * Initialize the connector
     * This is ideal to use after creating a new instance to test DB connectivity
     * @param dbHost Hostname of db server
     * @param dbName Name of database on server
     * @param username Connection username
     * @param password Connection password
     */
    public boolean init(){
        if(!testConnection())  return false;
        isInitialized = true;
        return isInitialized;
    }

    /**
     * Get an open connection to db.
     * @return java.sql.Connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(connectionUrl, username,password);
    };

    /**
     * @param c
     * @param sql
     * @return
     * @throws SQLException
     */
    public PreparedStatement prepSql(Connection c,String sql) throws SQLException{
            return c.prepareStatement(sql);
    }

    /**
     * Can be used to test connection to db.
     * @return true if connection established
     */
    private boolean testConnection(){
        String testSql = "SELECT version()";

        try(Connection c = getConnection()){
            try (PreparedStatement ps = prepSql(c,testSql)){
                try(ResultSet rs = ps.executeQuery()){
                    return true;
                }
            }

        }catch(SQLException e){
            return false;
        }
    }




    public String settingsToString(){
        return connectionUrl + " -u " + username + "-p " + password;
    }
    
}
