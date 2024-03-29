package com.kuta.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DBAPI
 */
public abstract class DBAPI {

    private static  DBConnector connector;

    public static void setConnector(DBConnector connector){
        DBAPI.connector=connector;
    }


    public static boolean insertUser(UserModel user) throws ConnectorInitException, SQLException{
        if(!connector.isInitialized) {
            throw new ConnectorInitException("Please first provide an initialized connector");
        }

        String sql = """
        Insert into User(username,email,passHash,salt)
        values(?,?,?,?);
        """;

        try(Connection c = connector.getConnection()){
            try(PreparedStatement ps = connector.prepSql(c,sql)){
                ps.setString(1, user.username());
                ps.setString(2, user.email());
                ps.setString(3, user.passHash());
                ps.setString(4, user.salt());

                return ps.executeUpdate() > 0;
            }
        }
    }

    public static List<UserModel> getAllUsers() throws ConnectorInitException, SQLException{
        if(!connector.isInitialized) {
            throw new ConnectorInitException("Please first provide an initialized connector");
        }

        String sql = """
        select * from User;
        """;
        try(Connection c = connector.getConnection()){
            try(PreparedStatement ps = connector.prepSql(c, sql)){
                try(ResultSet rs = ps.executeQuery()){
                    List<UserModel> users = new ArrayList<>();
                    while(rs.next()){
                        users.add(new UserModel(
                            rs.getBytes("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("passHash"),
                            rs.getString("salt")
                        ));
                    }
                    return users;
                }
            }
        }
    }

    public static UserModel getUserByEmail(String email) throws ConnectorInitException, SQLException{
        if(!connector.isInitialized) {
            throw new ConnectorInitException("Please first provide an initialized connector");
        }

        String sql = """
        select * from User
        where email = ?;
        """;
        try(Connection c = connector.getConnection()){
            try(PreparedStatement ps = connector.prepSql(c, sql)){
                ps.setString(1,email);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        UserModel user = new UserModel(
                            rs.getBytes("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("passHash"),
                            rs.getString("salt")
                        );
                        return user;
                    }
                }
            }
        }

        return null;
    }
    public static UserModel getUserByUsername(String username) throws ConnectorInitException, SQLException{
        if(!connector.isInitialized) {
            throw new ConnectorInitException("Please first provide an initialized connector");
        }

        String sql = """
        select * from User
        where username = ?;
        """;
        try(Connection c = connector.getConnection()){
            try(PreparedStatement ps = connector.prepSql(c, sql)){
                ps.setString(1,username);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        UserModel user = new UserModel(
                            rs.getBytes("id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("passHash"),
                            rs.getString("salt")
                        );
                        return user;
                    }
                }
            }
        }

        return null;
    }
    
}
