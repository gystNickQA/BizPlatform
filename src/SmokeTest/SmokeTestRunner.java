package SmokeTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
/**
 * Created by nkorchyk on 04.12.15.
 */
public class SmokeTestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SmokeTestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
