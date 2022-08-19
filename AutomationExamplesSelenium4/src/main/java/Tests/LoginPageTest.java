package Tests;

import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginPageTest {

    WebDriver driver;

    @BeforeTest
    public void setup(){

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(firefoxOptions);
        driver.get("http://the-internet.herokuapp.com");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void CorrectLoginToPageTest(){

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("admin","admin");
        WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        String URL = driver.getCurrentUrl();
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("example")));
        Assert.assertEquals( URL,"http://the-internet.herokuapp.com/basic_autht" );
        CloseBrowser();
    }

    public void CloseBrowser(){
        driver.close();
    }

}
