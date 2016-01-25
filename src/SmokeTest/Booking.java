package SmokeTest;

/**
 * Created by nkorchyk on 18.08.15.
 */

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Booking extends BizCommon {
    @Test
    public void testBooking() throws Exception {
        Business.Services.ServicesExecute services = new Business.Services.ServicesExecute();
        Business.Resources resources = new Business.Resources();
        Business.Specialists specialists = new Business.Specialists();

        driver.get(baseUrl);
        driver.manage().window().maximize();
        logIn(email, password);

        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='WEEK']"));
        el.click(); //week view
        el = BizCommon.waitForVisible(driver,By.xpath("//a[text()='MONTH']"));
        el.click(); //month view
        el = BizCommon.waitForVisible(driver,By.xpath("//a[text()='DAY']"));
        el.click(); //day view
        Thread.sleep(5000); //wait 5 sec

        //Add new booking
        BizCommon.addBooking(resources.resourceFirst.categoryName,1,9,"Client1","380111111111","email@test.test",services.serviceFirst.name);
        Thread.sleep(5000); //wait 5 sec
        BizCommon.addBooking(resources.resourceFirst.categoryName, 2, 10, "Client2", "380222222222", "email2@test.test", services.serviceSecond.name);
        Thread.sleep(5000); //wait 5 sec
    }
}
