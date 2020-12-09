package app.cyberzen.easytax;

import junit.framework.TestCase;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WelcomeActivityTest extends TestCase {

    private static boolean onBackPressed;

    @Test
    public void onBackPressed() {
        if(!onBackPressed) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("onBackPressed Test Case 3"  + "\n" + dtf.format(now) );

        }
    }
}