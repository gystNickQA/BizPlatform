package SmokeTest;

/**
 * Created by nkorchyk on 18.08.15.
 */

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;



public class MobileBuilder extends BizCommon {
    @Test
    public void testOpeningTime() throws Exception {
        Business.OpeningTime openingTime = new Business.OpeningTime();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        logIn(email, password);

        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-bind, 'MOBILE_APP')]"));
        el.click(); //Mobile app

        el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-bind, 'BUILDER_HOMESCREEN')]"));
        el.click(); //HOMESCREEN
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@btn-radio=\"'LIGHT'\"]"));
        el.click(); //LIGHT
        Thread.sleep(1000); //wait 1 sec
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@btn-radio=\"'DARK'\"]"));
        el.click(); //DARK
        Thread.sleep(1000); //wait 1 sec

        WebElement scroll = driver.findElement(By.xpath("//div[contains(@class,'builder-page')]//div[@class='slimScrollBar']"));


        ((JavascriptExecutor)driver).executeScript("document.getElementsByClassName('slimScrollBar')[0].style.display='true'");
        el = BizCommon.waitForVisible(driver,By.xpath("//div[contains(@class,'builder-page')]"));
        int windowHeight = el.getSize().getHeight()/8;
        System.out.println(windowHeight);
        Actions action = new Actions(driver);
        action.dragAndDropBy(scroll, 0, 150).build().perform();
        Thread.sleep(3000); //wait 3 sec
        el = BizCommon.waitForVisible(driver, By.xpath("//label[@btn-radio=\"'TEXT'\"]"));
        el.click(); //DARK
        action.dragAndDropBy(scroll, 0, 150).build().perform();
        Thread.sleep(3000); //wait 3 sec



    }
}
