package Tests;

import Base.BaseTest;
import Base.TestsMethodsScheme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class LinksValidationTest extends BaseTest implements TestsMethodsScheme {

    String url = "";
    String name= "";
    HttpURLConnection huc = null;
    int respCode = 200;

    @BeforeClass
    public void setup() {
        driver.get(mainPageURL());
    }
        @Test(groups = "Response",description = "Checking response code of links")
        public void TC1_BrokenLinks(){

        List<WebElement> links = driver.findElements(By.tagName("a"));

            for (WebElement link : links) {

                    url = link.getAttribute("href");

                    name = link.getText();

                    if (url == null || url.isEmpty())
                    {
                        System.out.println("URL is either not configured for anchor tag or it is empty " +name);
                        continue;
                    }

                    if (!url.startsWith(mainPageURL())) {
                        System.out.println("URL belongs to another domain, skipping it. " );
                        continue;
                    }

                    try {
                        huc = (HttpURLConnection) (new URL(url).openConnection());

                        huc.setRequestMethod("HEAD");

                        huc.connect();

                        respCode = huc.getResponseCode();

                        if (respCode >= 400) {
                            softAssert.fail(url + " url not configured" + name);
                            System.out.println(url + " is a broken link ");
                        } else {
                            System.out.println(url + " is a valid link ");
                        }

                    } catch (IOException e) {

                        e.printStackTrace();
                    }
            }
            softAssert.assertAll();
    }
}