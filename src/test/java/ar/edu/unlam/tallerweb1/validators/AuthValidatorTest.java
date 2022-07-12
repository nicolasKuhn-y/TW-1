package ar.edu.unlam.tallerweb1.validators;

import ar.edu.unlam.tallerweb1.exceptions.InvalidFieldException;
import ar.edu.unlam.tallerweb1.validators.errorMessages.AuthErrorMessages;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AuthValidatorTest extends AuthValidator {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void shouldThrowAnInvalidFieldExceptionIfEmailIsNotValidWithMessage() {
        String badFormattedEmail = "hola";
        String wantedErrorMesage = AuthErrorMessages.BAD_EMAIL.message;

        expectToThrowErrorWith(wantedErrorMesage);

        this.validateEmail(badFormattedEmail);
    }

    @Test
    public void shouldThrowAnInvalidFieldExceptionIfPasswordIsNotValidWithMessage() {
        String badFormattedPassword = "1234";
        String wantedErrorMesage = AuthErrorMessages.BAD_PASSWORD.message;

        expectToThrowErrorWith(wantedErrorMesage);

        this.validatePassword(badFormattedPassword);

    }

    private void expectToThrowErrorWith(String message) {
        exceptionRule.expect(InvalidFieldException.class);
        exceptionRule.expectMessage(message);
    }


}
