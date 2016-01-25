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

        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-bind, 'OPENING_TIME')]"));
        el.click(); //Opening time

        //Add new booking time
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.clear()']"));
        el.click(); //Clear week schedule
        BizCommon.addNewTime(9, 18, 2);
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.saveSchedule()']"));
        el.click(); //Save
        Thread.sleep(1000); //wait 1 sec

        BizCommon.addNewTime(9, 18, 3);
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.saveSchedule()']"));
        el.click(); //Save
        Thread.sleep(1000); //wait 1 sec

        BizCommon.addNewTime(9, 18, 4);
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.saveSchedule()']"));
        el.click(); //Save
        Thread.sleep(1000); //wait 1 sec

        BizCommon.addNewTime(9, 18, 5);
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.saveSchedule()']"));
        el.click(); //Save
        Thread.sleep(1000); //wait 1 sec

        BizCommon.addNewTime(9, 18, 6);
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.saveSchedule()']"));
        el.click(); //Save
        Thread.sleep(3000); //wait 3 sec

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
