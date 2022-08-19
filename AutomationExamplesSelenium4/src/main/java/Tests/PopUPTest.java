package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PopUPTest {

    WebDriver driver;

    @BeforeTest
    public void setup(){

        String username = "admin";
        String password = "admin";
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("http://"+username+":"+password+"@the-internet.herokuapp.com/basic_auth");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }


    @Test
    public void CorrectLoginToPageTest(){

        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("example")));
        String congratsText = driver.findElement(By.cssSelector(".example>p")).getText();
        String expected = "Congratulations! You must have the proper credentials.";
        System.out.println(congratsText);
        Assert.assertEquals(congratsText,expected);
        driver.close();
    }


}
