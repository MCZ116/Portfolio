package test;

import Pages.MainPageSections;
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

public class MainPageSectionsDisplaying {

    WebDriver driver;
    MainPageSections mainPageSections;

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

    @Test(priority = 2)
    public void VisibilityOfSections() throws Exception {

        mainPageSections = new MainPageSections(driver);
        Thread.sleep(3000);
        boolean womanButtonDisplay = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[1]/a")).isDisplayed();
        boolean dressButtonDisplay = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a")).isDisplayed();
        boolean tshirtButtonDisplay = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[3]/a")).isDisplayed();
        System.out.println("Section buttons displayed are :"+" Women "+womanButtonDisplay+" Dresses "+dressButtonDisplay+ " T-shirts "+tshirtButtonDisplay);

        Assert.assertTrue(mainPageSections.womenButtonText().contains("WOMEN"));
        Assert.assertTrue(mainPageSections.dressesButtonText().contains("DRESSES"));
        Assert.assertTrue(mainPageSections.tshirtsButtonText().contains("T-SHIRTS"));

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
