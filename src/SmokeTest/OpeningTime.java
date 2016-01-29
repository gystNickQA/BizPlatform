package SmokeTest;

/**
 * Created by nkorchyk on 18.08.15.
 */

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OpeningTime extends BizCommon {
    @Test
    public void testOpeningTime() throws Exception {
        Business.OpeningTime openingTime = new Business.OpeningTime();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        logIn(email, password);

        //Business selecting
        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//button[@id='venueDropdown']"));
        el.click(); 
        el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='Zname2']"));
        el.click();//select Zname2

        el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-bind, 'BOOKINGS')]"));
        el.click(); //Opening time
        el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-bind, 'OPENING_TIME')]"));
        el.click(); //Opening time

        //Add new booking time
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.clear()']"));
        el.click(); //Clear week schedule
        BizCommon.addNewTime(9, 18, 2);

        BizCommon.addNewTime(9, 18, 3);

        BizCommon.addNewTime(9, 18, 4);

        BizCommon.addNewTime(9, 18, 5);

        BizCommon.addNewTime(9, 18, 6);

        //Save as template
        BizCommon.saveAsTemplate(openingTime.templateName, openingTime.templateDescription);
        Thread.sleep(3000); //wait 3 sec

        //Copy from template
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.clear()']"));
        el.click(); //Clear week schedule
        BizCommon.copyFromTemplate(openingTime.templateName);
        Thread.sleep(3000); //wait 3 sec

        //Delete template
        BizCommon.deleteTemplate(openingTime.templateName);
        Thread.sleep(3000); //wait 3 sec
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.saveSchedule()']"));
        el.click(); //Save

        //Save as template
        BizCommon.saveAsTemplate(openingTime.templateName, openingTime.templateDescription);
        Thread.sleep(3000); //wait 3 sec
    }
}
