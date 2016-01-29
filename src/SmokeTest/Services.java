package SmokeTest;

/**
 * Created by nkorchyk on 18.08.15.
 */

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;

public class Services extends BizCommon {


    @Test
    public void testServices() throws Exception {
        Business.Services.ServicesExecute services = new Business.Services.ServicesExecute();
        driver.get(baseUrl);
        driver.manage().window().maximize();
        logIn(email, password);


        //Business selecting
        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//button[@id='venueDropdown']"));
        el.click();
        Thread.sleep(1000); //wait 1 sec
        el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='Zname2']"));
        el.click();//select Zname2
        Thread.sleep(1000); //wait 1 sec
        el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-bind, 'BOOKINGS')]"));
        el.click(); //BOOKINGS
        Thread.sleep(1000); //wait 1 sec
        el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-bind, 'SERVICES')]"));
        el.click(); //SERVICES
        Thread.sleep(1000); //wait 1 sec
        if(BizCommon.isElementPresent(driver, By.xpath("//*[@class='group-name ng-binding ng-scope']"))){
            el = BizCommon.waitForVisible(driver, By.xpath("//*[@class='fa fa-pencil edit-group-icon ng-scope']"));
            el.click(); //click to edit category
            Thread.sleep(2000); //wait 2 sec
            el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='newCategory.deleteGroup();']"));
            el.click(); //click to delete category
            Thread.sleep(2000); //wait 2 sec
            el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-click,'vm.delete()')]"));
            el.click(); //click to confirm deleting
        }

        Thread.sleep(2000); //wait 2 sec
        //Add new Category
        el = BizCommon.waitForVisible(driver, By.xpath("//span[@class='dropdown-toggle add-item-icon no-shadow']"));
        el.click(); //Click +
        el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='Category']"));
        el.click(); //click category
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-model='newCategory.model.name']"));
        el.sendKeys(services.serviceFirst.categoryName); //Enter category name
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='newCategory.createCategory();']"));
        el.click(); //click Save
        Thread.sleep(2000); //wait 2 sec
        //Add new Service
        el = BizCommon.waitForVisible(driver, By.xpath("//span[@class='dropdown-toggle add-item-icon no-shadow']"));
        el.click(); //Click +
        el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='Service']"));
        el.click(); //click category
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-model='controller.typeModel.name']"));
        //String tmpServiceName = "Service["+df.format(currentDate)+"]";
        el.sendKeys(services.serviceFirst.name); //Enter service name
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-model='controller.typeModel.description']"));
        el.sendKeys(services.serviceFirst.description); //Enter service description
        el = BizCommon.waitForVisible(driver, By.xpath("//option[@value='number:"+services.serviceFirst.duration+"']"));
        el.click(); //Select duration 30m
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@custom-checkbox='controller.typeModel.isAddPrice']"));
        el.click(); //Enable prices
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@class='full-price-section']//*[@ng-model='value']"));
        el.clear();
        el.sendKeys(services.serviceFirst.fullPrice); //Select 100
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='controller.saveType();']"));
        el.click(); //click Save
        el = BizCommon.waitForVisible(driver, By.xpath("//span[text()='"+services.serviceFirst.categoryName+"']"));
        el.click(); //open category
        assertEquals(services.serviceFirst.name, driver.findElement(By.xpath("//div[@class='reser-type ng-scope'][1]//span[@class='type-name ng-binding']")).getText());
        Thread.sleep(3000); //wait 3 sec

        //Add new Service
        el = BizCommon.waitForVisible(driver, By.xpath("//span[@class='dropdown-toggle add-item-icon no-shadow']"));
        el.click(); //Clear +
        el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='Service']"));
        el.click(); //click category
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-model='controller.typeModel.name']"));
        //tmpServiceName = "Service["+df.format(currentDate)+"]";
        el.sendKeys(services.serviceSecond.name); //Enter service name
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-model='controller.typeModel.description']"));
        el.sendKeys(services.serviceSecond.description); //Enter service description
        el = BizCommon.waitForVisible(driver, By.xpath("//option[@value='number:"+services.serviceSecond.duration+"']"));
        el.click(); //Select duration 60m
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@custom-checkbox='controller.typeModel.allowGroupBookings']"));
        el.click(); //Enable group bookings
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@class='max-amount-of-bookings']//*[@ng-model='value']"));
        el.clear();
        el.sendKeys(services.serviceSecond.amountOfGroupBookings); //amount - 3
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='controller.saveType();']"));
        el.click(); //click Save
        assertEquals(services.serviceSecond.name, driver.findElement(By.xpath("//div[@class='reser-type ng-scope'][2]//span[@class='type-name ng-binding']")).getText());
        Thread.sleep(3000); //wait 3 sec
    }
}
