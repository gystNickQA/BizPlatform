package SmokeTest;

/**
 * Created by nkorchyk on 18.08.15.
 */

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class Specialists extends BizCommon {


    @Test
    public void testSpecialists() throws Exception {
        Business.Specialists specialists = new Business.Specialists();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        logIn(email, password);

        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-bind, 'SPECIALISTS')]"));
        el.click(); //SPECIALISTS

        if(BizCommon.isElementPresent(driver, By.cssSelector(".specialist-name.ng-binding"))){
            el = BizCommon.waitForVisible(driver, By.xpath("//*[@class='fa fa-pencil edit-group-pencil ng-scope']"));
            el.click(); //click to edit category
            el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-bind, 'DELETE')]"));
            el.click(); //DELETE
            el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='ctrl.deleteGroup()']"));
            el.click(); //click delete
            Thread.sleep(3000); //wait 3 sec
        }

        //Add new Category
        el = BizCommon.waitForVisible(driver, By.xpath("//span[text()='+']"));
        el.click(); //Clear +
        el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='New category']"));
        el.click(); //click category
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-model='ctrl.resourceGroupModel.Name']"));
        el.sendKeys(specialists.specialistFirst.categoryName); //Enter category name
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='ctrl.saveGroup();']"));
        el.click(); //click Save
        Thread.sleep(3000); //wait 3 sec

        //Add new Specialist
        el = BizCommon.waitForVisible(driver, By.xpath("//span[text()='+']"));
        el.click(); //Clear +
        el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='Staff']"));
        el.click();
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-model='ctrl.resourceModel.Name']"));
        //String tmpResourceName = "Specialist["+df.format(currentDate)+"]";
        el.sendKeys(specialists.specialistFirst.name); //Enter Specialist name
        el = BizCommon.waitForVisible(driver, By.xpath("//option[text()='"+specialists.specialistFirst.categoryName+"']"));
        el.click(); //Enter Specialist category
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='ctrl.saveResource();']"));
        el.click(); //click Save
        assertEquals(specialists.specialistFirst.name, driver.findElement(By.cssSelector(".reser-type.ng-scope:nth-child(1) .specialist-name.ng-binding")).getText());
        Thread.sleep(3000); //wait 3 sec

        //Add new Specialist
        el = BizCommon.waitForVisible(driver, By.xpath("//span[text()='+']"));
        el.click(); //Clear +
        el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='Staff']"));
        el.click(); //click category
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-model='ctrl.resourceModel.Name']"));
        //tmpResourceName = "Specialist["+df.format(currentDate)+"]";
        el.sendKeys(specialists.specialistSecond.name); //Enter Specialist name
        el = BizCommon.waitForVisible(driver, By.xpath("//option[text()='"+specialists.specialistSecond.categoryName+"']"));
        el.click(); //Enter Specialist category
        el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='Upload image']"));
        el.click(); //click Save
        BizCommon.imageUploader(specialists.specialistSecond.imgSource); //upload image
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='ctrl.saveResource();']"));
        el.click(); //click Save
        assertEquals(specialists.specialistSecond.name, driver.findElement(By.cssSelector(".reser-type.ng-scope:nth-child(2) .specialist-name.ng-binding")).getText());
        Thread.sleep(3000); //wait 3 sec
    }
}
