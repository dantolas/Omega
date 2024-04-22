package com.kuta;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kuta.app.models.ConfigFileModel;
import com.kuta.db.DBAPI;
import com.kuta.db.DBConnector;

/**
 * Main
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        try {
            ConfigFileModel config = 
            ConfigFileModel.fromFile(System.getProperty("user.dir")+"/conf/config.json");
            config.setup();
            DBConnector dbc = new DBConnector()
            .setPassword(config.databasePassword)
            .setUsername(config.databaseUser)
            .setConnectionUrl(DBConnector.Prefix.MYSQL, config.databaseHost, config.databaseName);
            int attempts = 3;
            while(!dbc.init()){
                if(attempts <= 0) throw new Exception();
                config = 
                ConfigFileModel.fromFile(System.getProperty("user.dir")+"/conf/config.json");
                config.setup();
                dbc = new DBConnector()
                .setPassword(config.databasePassword)
                .setUsername(config.databaseUser)
                .setConnectionUrl(DBConnector.Prefix.MYSQL, config.databaseHost, config.databaseName);
                attempts --;
            }
            DBAPI.setConnector(dbc);
            SpringApplication.run(Main.class,args);
        } catch (FileNotFoundException e) {
            System.out.println("The application couldn't start, because the config file is missing.");
            System.out.println("Please ensure the file is present in project source files as (projectRoot)/conf/config.json");
        } catch (IOException e) {
            System.out.println("The application couldn't start, because it couldn't read the config file.");
            System.out.println("Please ensure the application has read permissions for the (projectRoot)/conf/config.json file");
        } catch (Exception e) {
            System.out.println("The application couldn't start, because connection to database couldn't be established");
            System.out.println("Please check your connection and configuration settings and ensure they match your database settings");
        }
    }
}
