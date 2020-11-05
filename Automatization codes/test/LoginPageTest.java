package test;

import Pages.LoginPage;
import Pages.WelcomePage;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class LoginPageTest {

    WebDriver driver;
    LoginPage loginPage;
    WelcomePage welcomePage;

    @BeforeTest
    public void setup() {

        System.out.println("Test automatizationpractice site");
        System.setProperty("webdriver.gecko.driver","C:\\Users\\MCZ116\\IdeaProjects\\Zadania\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        //ustawienia drivera i przejście do naszej strony logowania

    }

    @Test(priority = 0)
    public void correctLogin() throws Exception {

        loginPage = new LoginPage(driver);

        loginPage.loginToSite("MCZyoutest2020@gmail.com","Zaq123409");

        WebDriverWait waitName = new WebDriverWait(driver, 5000);
        waitName.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[1]/ul/li[4]/a/span")));
        welcomePage = new WelcomePage(driver);
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://automationpractice.com/index.php?controller=my-account" );
        Assert.assertEquals(welcomePage.getWelcomeTxt().toLowerCase(), "my personal information");
        Assert.assertTrue(welcomePage.getLoggedUser().toLowerCase().contains("joey last"));
        takeScshot(driver, "c://Users//MCZ116//Desktop//Dokumenty//MCZ//Inne//javascreenshots//test.png") ;
        CloseBrowser();
    }

    public static void takeScshot (WebDriver webdriver,String fileWithPath) throws Exception
    {
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }


    public void CloseBrowser(){

        driver.close();
    }
}
