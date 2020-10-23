package test;

import Pages.LoginPage;
import Pages.MainPageSections;
import Pages.SearchPage;
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

public class Test01 {

   WebDriver driver;
   LoginPage loginPage;
   WelcomePage welcomePage;
   SearchPage searchPage;
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


    @Test(priority = 0)
    public void correctLogin(){

        loginPage = new LoginPage(driver);

        loginPage.loginToSite("MCZyoutest2020@gmail.com","Zaq123409");

        WebDriverWait waitName = new WebDriverWait(driver, 5000);
        waitName.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[2]/div/div[3]/div/div/div[1]/ul/li[4]/a/span")));
        welcomePage = new WelcomePage(driver);

        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "http://automationpractice.com/index.php?controller=my-account" );
        Assert.assertEquals(welcomePage.getWelcomeTxt().toLowerCase(), "my personal information");
        Assert.assertTrue(welcomePage.getLoggedUser().toLowerCase().contains("joey last"));

    }
    @Test(priority = 1)
    public void incorrectSearch(){

        searchPage = new SearchPage(driver);

        WebDriverWait waitName = new WebDriverWait(driver, 5000);
        waitName.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_query_top")));

        searchPage.searchText("Jacket");
        searchPage.pressSearchButton();
        Assert.assertTrue(searchPage.errorSearchText().contains("No results were found for your search"));

    }

    @Test(priority = 2)
    public void VisibilityOfSections() throws InterruptedException {

        mainPageSections = new MainPageSections(driver);
        Thread.sleep(3000);
        boolean womanButtonDisplay = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[1]/a")).isDisplayed();
        boolean dressButtonDisplay = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a")).isDisplayed();
        boolean tshirtButtonDisplay = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[3]/a")).isDisplayed();
        System.out.println("Section buttons displayed are :"+" Women "+womanButtonDisplay+" Dresses "+dressButtonDisplay+ " T-shirts "+tshirtButtonDisplay);

        Assert.assertTrue(mainPageSections.womenButtonText().contains("WOMEN"));
        Assert.assertTrue(mainPageSections.dressesButtonText().contains("DRESSES"));
        Assert.assertTrue(mainPageSections.tshirtsButtonText().contains("T-SHIRTS"));

    }

    @Test(priority = 3)
    public void CorrectLogout() throws Exception {
        welcomePage.logout();
        boolean signInButtonDisplay = driver.findElement(By.xpath("//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")).isDisplayed();
        System.out.println("Element displayed is :"+signInButtonDisplay);
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