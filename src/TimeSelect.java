/**
 * Created by nkorchyk on 10.08.15.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;


public class TimeSelect {

    protected WebDriver driver;
    protected String baseUrl = "http://bizplatform.co";
    protected StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testTimeSelect() throws Exception {
        driver.get(baseUrl+"/Home/ChangeCulture?lang=2&returnUrl=%2F");
        driver.manage().window().maximize();
        Thread.sleep(10000);

            }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

}

