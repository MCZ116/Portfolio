package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {

    protected static WebDriver driver;

    protected static SoftAssert softAssert;

    protected static String scrPath = "C://Users//MCZ116//Desktop//TestSc//test.png";

    @BeforeSuite
    protected static void initialize(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        softAssert = new SoftAssert();
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    public String mainPageURL(){
        return "http://automationpractice.pl";
    }

    protected void goToMainPage(){
        driver.get(mainPageURL());
    }

    public void screenshotError(String testMethod) throws IOException {
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(scrPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }

    @AfterSuite
    protected void CloseBrowser(){
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
