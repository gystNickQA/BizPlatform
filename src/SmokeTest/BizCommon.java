package SmokeTest;

/**
 * Created by nkorchyk on 10.08.15.
 */

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class BizCommon {
    protected static WebDriver driver;
    protected String baseUrl = "http://bizplatform.co";
    protected String email = "water_filter@mail.ru";
    protected String password = "3";
    protected String managerEmail = "email3@test.com";
    protected String managerPass = "email3";
    protected String business = "Autoservice";
    public static final int WAITFOR_TIMEOUT = 30; //The amount of time to wait for a given operation
    protected StringBuffer verificationErrors = new StringBuffer();

    public static class Business{
        public static class OpeningTime{
            protected String templateName = "Full-time";
            protected String templateDescription = "Mon-Fri 9:00-18:00";
        }

        public static class Services{
            protected String categoryName = "ServiceCategory";
            public static class ServicesExecute extends Services{
                ServiceFirst serviceFirst = new ServiceFirst();
                ServiceSecond serviceSecond = new ServiceSecond();
            }
            public static class ServiceFirst extends Services{
                protected String name = "Repair car body";
                protected String description = "Repair car body Description";
                protected String duration = "60"; //minutes
                protected String fullPrice = "100";
                protected String preOrderingPrice = "0";
                protected String amountOfGroupBookings = "7";
            }
            public class ServiceSecond extends Services{
                protected String name = "Repair car motor";
                protected String description = "Repair car motor Description";
                protected String duration = "30"; //minutes
                protected String fullPrice = "200";
                protected String preOrderingPrice = "0";
                protected String amountOfGroupBookings = "7";
            }
        }

        public static class Resources{
            public class ResourceFirst{
                protected String categoryName = "ResourcesCategory";
                protected String name = "Station for car";
                protected String imgSource = "C:\\tmp\\resource.jpg";
            }
            ResourceFirst resourceFirst = new ResourceFirst();

            public class ResourceSecond{
                protected String categoryName = "ResourcesCategory";
                protected String name = "Station for moto";
                protected String imgSource = "C:\\tmp\\resource.jpg";
            }
            ResourceSecond resourceSecond = new ResourceSecond();
        }

        public static class Specialists{
            public class SpecialistFirst{
                protected String categoryName = "SpecialistsCategory";
                protected String name = "Specialist 1";
                protected String imgSource = "C:\\tmp\\specialist.png";
            }
            SpecialistFirst specialistFirst = new SpecialistFirst();

            public class SpecialistSecond{
                protected String categoryName = "SpecialistsCategory";
                protected String name = "Specialist 2";
                protected String imgSource = "C:\\tmp\\specialist.png";
            }
            SpecialistSecond specialistSecond = new SpecialistSecond();
        }

        public static class Settings{
            protected String minimalIntervalOfBooking = "00:30:00";
            protected String venueType = "Service";
            protected String numberOfweeksVisitorsCanBook = "3";
            protected String additionalInformation = "3";
        }
    }
    public class MobileApp{
        protected String appTheme = "LIGHT"; //or DARK
        protected String headerOnHomeScreen = "Text"; //or Image
        protected String headerOnHomeScreenText = "Header Home Screen Text";
        protected String storyText = "Story Text";
        protected String locationName = "Name of location";
        protected String locationAddress = "Address";
        protected String locationPhone = "1111111";
        protected String loyaltyPercentage = "50";
        protected String loyaltyMinimum = "100";

    }



    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static WebElement waitForVisible(WebDriver driver, final By by) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, WAITFOR_TIMEOUT);
        wait.withMessage("Element to be found by " + by.toString() + " did not become visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by);
    }

    public static WebElement waitForVisible(WebDriver driver, final By by, int timeout) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.withMessage("Element to be found by " + by.toString() + " did not become visible");
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return driver.findElement(by);
    }

    public static boolean isElementPresent(WebDriver driver, By by) {
        boolean res = false;
        //Use a short wait time so we don't wait too long for elements that aren't present
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //explicit wait for element to be present
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            res = true;
        } catch (WebDriverException e) {
        }
        return res;
    }

    public static void imageUploader(String absolutePath) throws Exception{
        //Enter path of image
        StringSelection stringSelection = new StringSelection(absolutePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Thread.sleep(1000); //wait 1 sec
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static void screen(String path) throws Exception {
        ((FirefoxDriver)driver).getKeyboard().pressKey(Keys.F11);
        Thread.sleep(1000); // wait time milliseconds
        File scrFileStep = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFileStep, new File("c:\\tmp\\" + path + ".png"));
        ((FirefoxDriver)driver).getKeyboard().pressKey(Keys.F11);
    }


    public static void logIn(String login, String password) throws Exception {
        //LOG IN
        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//nav[@id='nav']//*[@id='btn-dropdownMenu2']"));
        el.click();//Select language
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@id='nav']//a[contains(@href,'lang=2')]"));
        el.click();//EN
        el = BizCommon.waitForVisible(driver, By.cssSelector(".navbar.navbar-static-top .login"));
        el.click();//Login
        el = waitForVisible(driver,By.id("Email"));
        el.clear();
        el.sendKeys(login);
        el = waitForVisible(driver,By.id("Password"));
        el.clear();
        el.sendKeys(password);
        el = waitForVisible(driver,By.id("sbmtBtn"));
        el.click();
    }

    public static void addNewTime(int startHour, int endHour,int numberDayOfWeek) throws Exception {
        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@class,'k-today')][" + numberDayOfWeek + "]/strong[contains(text(),'" + startHour + ":00')]"));
        el.click(); //click on time
        Thread.sleep(2000); //wait 2 sec
        for(int i =1; i < endHour-startHour; i++)
        {
            el = BizCommon.waitForVisible(driver, By.xpath("//table[@ng-model='dataItem.end']//a[@ng-click='incrementHours()']"));
            el.click(); //increment hours +1
        }
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.saveEvent(dataItem)']"));
        el.click(); //Save
        Thread.sleep(2000); //wait 2 sec
    }

    public static void addBooking(String category, int numberResource, int startHour, String clientName, String phoneNumber, String email, String service) throws Exception {
        //Check booking on this time
        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//a[text()='"+category+"']"));
        el.click(); //select category
        Thread.sleep(2000); //wait 2 sec
        if(BizCommon.isElementPresent(driver,By.xpath("//label[text()='"+clientName+"']"))){
            el = BizCommon.waitForVisible(driver,By.xpath("//label[text()='"+clientName+"']"));
            System.out.println("There is booking on this time: "+el.getText());
            el.click(); //select booking
            el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.deleteBooking(dataItem)']"));
            el.click(); //click delete booking
            el = BizCommon.waitForVisible(driver, By.xpath("//button[text()='Delete']"));
            el.click(); //click to confirm delete booking
            Thread.sleep(3000); //wait 3 sec
        }
        //Create new booking
        el = BizCommon.waitForVisible(driver, By.xpath("//td["+numberResource+"]/strong[contains(text(),'"+startHour+":00')]"));
        el.click(); //click on time
        Thread.sleep(2000); //wait 2 sec
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@name='client']"));
        el.sendKeys(clientName); //enter name
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@name='phone']"));
        el.sendKeys(phoneNumber); //enter phone
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@name='email']"));
        el.sendKeys(email); //enter email
        el = BizCommon.waitForVisible(driver, By.xpath("//i[@class='caret pull-right']"));
        el.click(); //open service dropdown list
        Thread.sleep(1000); //wait 1 sec
        el = BizCommon.waitForVisible(driver, By.xpath("//ul[contains(@class,'ui-select-choices')]//span[contains(text(),'"+service+"')]"));
        el.click(); //select service
        Thread.sleep(2000); //wait 2 sec
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.saveBooking(dataItem)']"));
        el.click(); //Save
        Thread.sleep(2000); //wait 2 sec
    }

    public static void saveAsTemplate(String templateName, String templateDescription) throws Exception {
        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//div[@class='btn-group responsive-dropdown dropdown']"));
        el.click();//click to open add menu
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.saveAsTemplate()']"));
        el.click();//click save as template
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-model='saveAsTemplateCtrl.name']"));
        el.clear();
        el.sendKeys(templateName);//enter template Name
        el = BizCommon.waitForVisible(driver, By.xpath("//textarea[@ng-model='saveAsTemplateCtrl.description']"));
        el.clear();
        el.sendKeys(templateDescription);//enter template description
        el = BizCommon.waitForVisible(driver, By.xpath("//*[contains(@ng-click,'saveAsTemplateCtrl.saveMethod')]"));
        el.click();//click Save
        Thread.sleep(1000); //waiting for saving
        //check result
        el = BizCommon.waitForVisible(driver, By.xpath("//div[@class='btn-group responsive-dropdown dropdown']"));
        el.click();//click to open add menu
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.copyFromTemplate()']"));
        el.click();//click copy From Template
        Thread.sleep(5000); //wait 5 sec
        assertEquals(templateName, driver.findElement(By.xpath("//td[contains(text(),'"+templateDescription+"')]/preceding-sibling::td")).getText());
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='copyFromTemplateCtrl.cancelMethod()']"));
        el.click();//click Cancel
    }

    public static void copyFromTemplate(String templateName) throws Exception {
        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//div[@class='btn-group responsive-dropdown dropdown']"));
        el.click();//click to open add menu
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.copyFromTemplate()']"));
        el.click();//click copy From Template
        el = BizCommon.waitForVisible(driver, By.xpath("//td[contains(text(),'" + templateName + "')]"));
        el.click(); //click on Template1
    }

    public static void deleteTemplate(String templateName) throws Exception {
        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//div[@class='btn-group responsive-dropdown dropdown']"));
        el.click();//click to open add menu
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='schedulerCtrl.copyFromTemplate()']"));
        el.click();//click copy From Template
        Thread.sleep(3000); //wait 1 sec
        el = BizCommon.waitForVisible(driver, By.xpath("//button[@class='btn'][preceding::td[contains(text(),'" + templateName + "')]]"));
        el.click(); //click to delete Template1
        el = BizCommon.waitForVisible(driver, By.xpath("//*[@ng-click='copyFromTemplateCtrl.cancelMethod()']"));
        el.click();//click Cancel
    }


    @After
    public void tearDown() throws Exception {
        System.out.println("Quite driver");
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
