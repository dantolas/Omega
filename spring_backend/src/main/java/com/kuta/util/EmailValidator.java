package com.kuta.util;

import java.util.regex.Pattern;

/**
 * EmailValidator
 */
public abstract class EmailValidator {

    public static boolean isEmail(String email){
        return Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-]"+
        "[\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$")
        .matcher(email)
        .matches();
    }
    
}
