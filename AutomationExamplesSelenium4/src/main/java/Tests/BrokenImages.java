package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class BrokenImages {

    WebDriver driver;
    String URL ="http://the-internet.herokuapp.com/broken_images";
    @BeforeTest
    public void setup(){

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @Test(description="Find broken links", enabled=true)
    protected void BrokenLinksTest()
    {
        int brokenImageCount = 0;

        try
        {
            List<WebElement> image_list = driver.findElements(By.tagName("img"));

            System.out.println("The page has " + image_list.size() + " images");
            for (WebElement img : image_list)
            {
                if (img != null)
                {
                    CloseableHttpClient client = HttpClientBuilder.create().build();
                    HttpGet request = new HttpGet(img.getAttribute("src"));
                    HttpResponse response = client.execute(request);

                    if (response.getStatusLine().getStatusCode() != 200)
                    {
                        System.out.println(img.getAttribute("outerHTML") + " is broken.");
                        brokenImageCount++;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("The page " + URL + " has " + brokenImageCount + " broken images");
    }

}
