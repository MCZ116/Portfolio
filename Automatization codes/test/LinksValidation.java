package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LinksValidation {

    private static WebDriver driver = null;

    String url = "";
    String name= "";
    String homePage = "http://automationpractice.com";
    HttpURLConnection huc = null;
    int respCode = 200;

    @BeforeTest
    public void setup() {

        System.out.println("Test automatizationpractice site");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\MCZ116\\IdeaProjects\\Zadania\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String homePage = "http://automationpractice.com";
        driver.manage().window().maximize();
        driver.get(homePage);
        //ustawienia drivera i przejście do strony

    }
        @Test(priority = 0)
        public void BrokenLinks(){

        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign in")));

        List<WebElement> links = driver.findElements(By.tagName("a"));

            for (WebElement link : links) {

                    url = link.getAttribute("href");

                    name = link.getText();

                    System.out.println(url);

                    if (url == null || url.isEmpty()) {
                        System.out.println("URL is either not configured for anchor tag or it is empty " +name);
                        continue;
                    }

                    if (!url.startsWith(homePage)) {
                        System.out.println("URL belongs to another domain, skipping it. " );
                        continue;
                    }

                    try {
                        huc = (HttpURLConnection) (new URL(url).openConnection());

                        huc.setRequestMethod("HEAD");

                        huc.connect();

                        respCode = huc.getResponseCode();

                        if (respCode >= 400) {
                            System.out.println(url + " is a broken link ");
                        } else {
                            System.out.println(url + " is a valid link ");
                        }

                    } catch (MalformedURLException e) {

                        e.printStackTrace();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
            }

        driver.quit();

    }
}