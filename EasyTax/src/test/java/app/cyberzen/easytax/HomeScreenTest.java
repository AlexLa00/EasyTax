package app.cyberzen.easytax;

import junit.framework.TestCase;

import org.junit.Test;


public class HomeScreenTest extends TestCase {

    private static boolean onCreate;
    private static boolean testOnOptionsItemSelected;
    private static boolean testOpenBusinessTaxOne;




        @Test
        public void testOpenBusinessTaxOne() {
            if(!testOpenBusinessTaxOne) {
                System.out.println("OnCreate Test Case 1" + "\n" + "Total=" + (5+6+54+100+6548+6654+5552+445588));

            }
        }

    @Test
    public void onCreate() {
        if(!onCreate) {
            System.out.println("OnCreate Test Case " + "\n" + "Total=" + (5+6));

        }
    }


        @Test
        public void testOnOptionsItemSelected() {
            if (testOnOptionsItemSelected) {
                long startTime = System.currentTimeMillis();

                long total = 0;
                for (int i = 0; i < 10000000; i++) {
                    total += i;
                }

                long stopTime = System.currentTimeMillis();
                long elapsedTime = stopTime - startTime;
                System.out.println("testOnOptionsItemSelected Test Case " + "\n" + "Run Time:" + elapsedTime);

            }
        }}