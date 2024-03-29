package com.kuta.app.configurations;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.gson.annotations.SerializedName;
import com.kuta.util.IO;
import com.kuta.vendor.GsonParser;

/**
 * ConfigFileModel
 */
public class ConfigFileModel {
    @SerializedName("database_host")
    public String databaseHost;
    @SerializedName("database_name")
    public String databaseName;
    @SerializedName("database_user")
    public String databaseUser;
    @SerializedName("database_password")
    public String databasePassword;

    public ConfigFileModel(){
    }

    public void setup() throws FileNotFoundException, IOException{
        checkDefaults();
    }

    private void checkDefaults(){
        if(this.databasePassword.equals("#")) this.databasePassword = "kuta";
        if(this.databaseHost.equals("#")) this.databaseHost= "localhost:3306";
        if(this.databaseUser.equals("#")) this.databaseUser = "user";
        if(this.databaseName.equals("#")) this.databaseName = "omega";
    }
    public static ConfigFileModel fromFile(String filepath) throws FileNotFoundException, IOException{
        String json = IO.readFileIntoString(filepath);
        return GsonParser.parser.fromJson(json,ConfigFileModel.class);
    }
}
