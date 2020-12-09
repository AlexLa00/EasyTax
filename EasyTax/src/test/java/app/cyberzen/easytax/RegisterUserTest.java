package app.cyberzen.easytax;

import junit.framework.TestCase;

import java.util.Random;

public class RegisterUserTest extends TestCase {



    public void testValidate() {
        Random rnd = new Random();
        char c = (char) (rnd.nextInt(26) + 'a');
    }
}