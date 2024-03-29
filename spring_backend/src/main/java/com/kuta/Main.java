package com.kuta;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kuta.db.ConnectorInitException;
import com.kuta.db.DBAPI;
import com.kuta.db.DBConnector;
import com.kuta.db.UserModel;

/**
 * Main
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
        DBConnector connector = new DBConnector().setUsername("charming").setPassword("kuta")
        .setConnectionUrl(DBConnector.Prefix.MYSQL, "localhost","omega");
        connector.init();
        DBAPI.setConnector(connector);
        try {
            System.out.println(DBAPI.getAllUsers());
        } catch (ConnectorInitException | SQLException e) {
            e.printStackTrace();
        }
    }
}
