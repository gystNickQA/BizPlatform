package SmokeTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by nkorchyk on 04.12.15.
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Login.class,
        OpeningTime.class,
        Services.class,
        Resources.class,
        Specialists.class,
        Settings.class,
        Booking.class,
        MobileBuilder.class,
        MobileBuilder.class,
})

public class SmokeTestSuite {
}
