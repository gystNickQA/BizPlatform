package SmokeTest;

/**
 * Created by nkorchyk on 18.08.15.
 */

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class Resources extends BizCommon {


    @Test
    public void testResources() throws Exception {
        Business.Resources resources = new Business.Resources();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        logIn(email, password);

        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-bind, 'RESOURCES')]"));
        el.click(); //RESOURCES

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
        el.sendKeys(resources.resourceFirst.categoryName); //Enter category name
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='ctrl.saveGroup();']"));
        el.click(); //click Save
        Thread.sleep(3000); //wait 3 sec

        //Add new Resource
        el = BizCommon.waitForVisible(driver, By.xpath("//span[text()='+']"));
        el.click(); //Clear +
        el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='Resource']"));
        el.click(); //click category
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-model='ctrl.resourceModel.Name']"));
        //String tmpResourceName = "Resource["+df.format(currentDate)+"]";
        el.sendKeys(resources.resourceFirst.name); //Enter Resource name
        el = BizCommon.waitForVisible(driver, By.xpath("//option[text()='"+resources.resourceFirst.categoryName+"']"));
        el.click(); //Enter Resource category
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='ctrl.saveResource();']"));
        el.click(); //click Save
        assertEquals(resources.resourceFirst.name, driver.findElement(By.cssSelector(".reser-type.ng-scope:nth-child(1) .specialist-name.ng-binding")).getText());
        Thread.sleep(3000); //wait 3 sec

        //Add new Resource
        el = BizCommon.waitForVisible(driver, By.xpath("//span[text()='+']"));
        el.click(); //Clear +
        el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='Resource']"));
        el.click(); //click category
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-model='ctrl.resourceModel.Name']"));
        //tmpResourceName = "Resource["+df.format(currentDate)+"]";
        el.sendKeys(resources.resourceSecond.name); //Enter Resource name
        el = BizCommon.waitForVisible(driver, By.xpath("//option[text()='"+resources.resourceSecond.categoryName+"']"));
        el.click(); //Enter Resource category
        el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='Upload image']"));
        el.click(); //click Save
        BizCommon.imageUploader(resources.resourceSecond.imgSource); //upload image
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='ctrl.saveResource();']"));
        el.click(); //click Save
        assertEquals(resources.resourceSecond.name, driver.findElement(By.cssSelector(".reser-type.ng-scope:nth-child(2) .specialist-name.ng-binding")).getText());
        Thread.sleep(3000); //wait 3 sec
    }
}
