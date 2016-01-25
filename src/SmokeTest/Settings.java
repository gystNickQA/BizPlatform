package SmokeTest;

/**
 * Created by nkorchyk on 18.08.15.
 */

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Settings extends BizCommon {
    @Test
    public void testSettings() throws Exception {

        driver.get(baseUrl);
        driver.manage().window().maximize();
        logIn(email, password);

        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-bind, 'SETTINGS')]"));
        el.click(); //SETTINGS

        //Minimal interval of booking
        el = BizCommon.waitForVisible(driver, By.xpath("//input[@value='00:15:00']"));
        el.click(); //select 15 min
        el = BizCommon.waitForVisible(driver, By.xpath("//input[@value='00:30:00']"));
        el.click(); //select 30 min
        el = BizCommon.waitForVisible(driver, By.xpath("//input[@value='01:00:00']"));
        el.click(); //select 1 h

        el = BizCommon.waitForVisible(driver, By.xpath("//input[@value='00:30:00']"));
        el.click(); //select 30 min

        //Number of weeks visitors can book in advance
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-model='vm.copyWeeksNumber']"));
        el.clear();
        el.sendKeys("3"); //set 3 weeks

        //Request additional information during booking process
        if(!BizCommon.isElementPresent(driver, By.xpath("//span[contains(@class,'checked')][@ng-model='vm.additionalFieldForm.isShown']"))){
            el = BizCommon.waitForVisible(driver,By.xpath("//span[@ng-model='vm.additionalFieldForm.isShown']"));
            el.click();
        }
        el = BizCommon.waitForVisible(driver,By.xpath("//*[@ng-model='vm.fieldName']"));
        el.clear();
        el.sendKeys("Additional information");
        if(!BizCommon.isElementPresent(driver, By.xpath("//span[contains(@class,'checked')][@ng-model='vm.isAdditionalFieldRequired']"))){
            el = BizCommon.waitForVisible(driver,By.xpath("//span[@ng-model='vm.isAdditionalFieldRequired']"));
            el.click();
        }

        el = BizCommon.waitForVisible(driver,By.xpath("//*[@ng-click='vm.saveChanges()']"));
        el.click(); //Save
    }
}
