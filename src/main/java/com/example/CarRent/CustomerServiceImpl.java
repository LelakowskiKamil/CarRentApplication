package com.example.CarRent;

import com.example.CarRent.exception.IncorrectPasswordException;
import com.example.CarRent.exception.IncorrectUsernameException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public boolean validatePassword(char[] password) {

        if (password.length < 8) {
            return false;}
        else {
            Pattern p = Pattern.compile("^(?=.*[a-z])(?=."
                    + "*[A-Z])(?=.*\\d)"
                    + "(?=.*[-+_!@#$%^&*., ?]).+$", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(Arrays.toString(password));
            boolean b = m.find();
            return b;
        }

    }

    @Override
    public boolean validateUsername(String username) {
        if (username.length() < 3) {
            return false;
        } else {
            Pattern p = Pattern.compile("(?=.*[-+_!@#$%^&*., ?])", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(username);
            boolean b = m.find();
                return !b;
            }
    }
}
