package com.kuta.db;

/**
 * UserModel
 * @param id : UUID of the user
 * @param username
 * @param email
 * @param passHash : hash of the password+salt
 * @param salt : salt for passHash
 */
public record UserModel(byte[] id,String username, String email, String passHash, String salt) {

    public UserModel(String username, String email, String passHash, String salt){
        this(null,username,email,passHash,salt);
    }
}
