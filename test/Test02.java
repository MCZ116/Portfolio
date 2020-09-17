package test;

import Pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Test02 {

   WebDriver driver;
   SearchPage searchPage;

    @BeforeTest
    public void setup() {

        System.out.println("Test roku start!");
        System.setProperty("webdriver.gecko.driver","C:\\Users\\MCZ116\\IdeaProjects\\Zadania\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        //ustawienia drivera i przejście do naszej strony logowania

    }

    @Test(priority = 0)
    public void incorrectSearch(){

        searchPage = new SearchPage(driver);

        WebDriverWait waitName = new WebDriverWait(driver, 5000);
        waitName.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_query_top")));

        searchPage.searchText("Jacket");
        searchPage.pressSearchButton();
        Assert.assertTrue(searchPage.errorSearchText().contains("No results were found for your search"));

        CloseBrowser();
    }

    public void CloseBrowser(){

        driver.close();

    }

}