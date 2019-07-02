package com.yegorf.bookmaker.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ValidationStrategy {
    @Override
    public void check(String text) throws Exception {
        String regExpn =
                "^(?=\\S+$).{4,}$";

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if(!matcher.matches()) {
            throw new Exception("Invalid password!");
        }
    }
}
