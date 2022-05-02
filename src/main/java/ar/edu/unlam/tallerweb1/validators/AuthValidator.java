package ar.edu.unlam.tallerweb1.validators;

import ar.edu.unlam.tallerweb1.entities.UserData;
import ar.edu.unlam.tallerweb1.validators.errorMessages.AuthErrorMessages;
import ar.edu.unlam.tallerweb1.exceptions.InvalidFieldException;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class AuthValidator {
    private final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private final Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");

    public void validateUserToCreate(UserData user) {
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
    }

    protected void validateEmail(String email) {
        boolean isEmailValid = emailPattern.matcher(email).matches();

        if (!isEmailValid) {
            throw new InvalidFieldException(AuthErrorMessages.BAD_EMAIL.message);
        }
    }

    protected void validatePassword(String password) {
        boolean isPasswordValid = passwordPattern.matcher(password).matches();

        if (!isPasswordValid) {
            throw new InvalidFieldException(AuthErrorMessages.BAD_PASSWORD.message);
        }
    }
}
