package test;

import Pages.MainPageSections;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddToCartTest {

    WebDriver driver;
    MainPageSections mainPageSections;

    @BeforeTest
    public void setup() {

        System.out.println("Test automatizationpractice site");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\MCZ116\\IdeaProjects\\Zadania\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        //ustawienia drivera i przejście do naszej strony logowania
    }

    @Test
    public void AddToCart() throws InterruptedException {

        Thread.sleep(3000);
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        mainPageSections = new MainPageSections(driver);
        mainPageSections.pressWomenButton();
        WebElement dress = driver.findElement(By.linkText("Printed Dress"));
        js.executeScript("arguments[0].scrollIntoView();",dress);
        actions.moveToElement(dress).perform();
        WebElement addButton = driver.findElement(By.cssSelector("[data-id-product='3']"));
        addButton.click();
        Thread.sleep(3000);

        Assert.assertTrue(mainPageSections.totalPriceText().contains("$28.00"));
        Assert.assertTrue(mainPageSections.successAddText().contains("Product successfully added to your shopping cart"));

    }



}
