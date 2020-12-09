package app.cyberzen.easytax.auth;

import junit.framework.TestCase;

import java.util.regex.Pattern;

public class AuthListenerTest extends TestCase {

    private static boolean testOnAuthentication;

    public void testOnAuthentication() {
class EmailValidator {
    boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();

    }}}}