package SmokeTest;

/**
 * Created by nkorchyk on 10.08.15.
 */

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;


public class Login extends BizCommon {

    private String login[] = {"incorrectLogin",null,"water_filter@mail.ru"};
    private String password[] = {"incorrectPass",null,"3"};
    private String correctLogin = "water_filter@mail.ru";
    private String correctPass = "3";
    private String validEmailText = "Please provide a valid email address.";
    private String incorrectNamePassword = "Incorrect user name or passwrod.";
    private String requirePass = "Password is required.";
    private String requireEmail = "Email is required";


    @Test
    public void testLogin() throws Exception {
        driver.get(baseUrl+"/Home/ChangeCulture?lang=2&returnUrl=%2F");
        driver.manage().window().maximize();
        WebElement el = BizCommon.waitForVisible(driver, By.xpath("//*[@id='nav']//a[contains(text(),'Log in')]"));

        el.click();
        for(int i = 0; i<3; i++){
            for(int j =0; j<3; j++){
                el = BizCommon.waitForVisible(driver, By.id("Email"));
                el.clear();
                el.sendKeys(login[i]);
                el = BizCommon.waitForVisible(driver, By.id("Password"));
                el.clear();
                el.sendKeys(password[j]);
                el = BizCommon.waitForVisible(driver, By.id("sbmtBtn"));
                el.click();

                //incorrectLogin & incorrectPass
                if (login[i] == "incorrectLogin" &&  password[j] == "incorrectPass"){
                    //	Email is required
                    try {
                        assertEquals(validEmailText, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[1]")).getText());
                        assertEquals(incorrectNamePassword, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[2]")).getText());
                    } catch (Error e) {
                        verificationErrors.append(e.toString());
                    }
                }
                //incorrectLogin & null
                else if (login[i] == "incorrectLogin" && password[j] == null){
                    //	Email is required
                    try {
                        assertEquals(validEmailText, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[1]")).getText());
                        assertEquals(requirePass, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[2]")).getText());
                        assertEquals(incorrectNamePassword, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[3]")).getText());
                    } catch (Error e) {
                        verificationErrors.append(e.toString());
                    }
                }

                //incorrectLogin & correctPass
                else if (login[i] == "incorrectLogin" && password[j] == correctPass){
                    //	Email is required
                    try {
                        assertEquals(validEmailText, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[1]")).getText());
                        assertEquals(incorrectNamePassword, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[2]")).getText());
                    } catch (Error e) {
                        verificationErrors.append(e.toString());
                    }
                }

                //null & incorrectPass
                else if (login[i] == null && password[j] == "incorrectPass"){
                    //	Email is required
                    try {
                        assertEquals(requireEmail, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[1]")).getText());
                        assertEquals(incorrectNamePassword, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[2]")).getText());
                    } catch (Error e) {
                        verificationErrors.append(e.toString());
                    }
                }

                //null & null
                else if (login[i] == null && password[j] == null){
                    //	Email is required
                    try {
                        assertEquals(requireEmail, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[1]")).getText());
                        assertEquals(requirePass, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[2]")).getText());
                        assertEquals(incorrectNamePassword, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[3]")).getText());
                    } catch (Error e) {
                        verificationErrors.append(e.toString());
                    }
                }

                //null & correctPass
                else if (login[i] == null && password[j] == correctPass){
                    //	Email is required
                    try {
                        assertEquals(requireEmail, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[1]")).getText());
                        assertEquals(incorrectNamePassword, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[2]")).getText());
                    } catch (Error e) {
                        verificationErrors.append(e.toString());
                    }
                }

                //correctLogin & incorrectPass
                else if (login[i] == correctLogin && password[j] == "incorrectPass"){
                    //	Email is required
                    try {
                        assertEquals(incorrectNamePassword, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[1]")).getText());
                    } catch (Error e) {
                        verificationErrors.append(e.toString());
                    }
                }

                //correctLogin & null
                else if (login[i] == correctLogin && password[j] == null){
                    //	Email is required
                    try {
                        assertEquals(requirePass, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[1]")).getText());
                        assertEquals(incorrectNamePassword, driver.findElement(By.xpath("//div[@class='validation-summary-errors']//li[2]")).getText());
                    } catch (Error e) {
                        verificationErrors.append(e.toString());
                    }
                }
                //correctLogin & correctPass
                //Enter

            }
        }

    }

}

