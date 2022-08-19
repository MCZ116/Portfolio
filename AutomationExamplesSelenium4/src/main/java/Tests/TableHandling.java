package Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TableHandling {

    WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get("http://the-internet.herokuapp.com/challenging_dom");
    }

    @Test
    public void CheckValueInTable(){
        String row2_col2 = driver.findElement(By.xpath("//*[@class='large-10 columns']/table/tbody/tr[2]/td[2]")).getText();
        System.out.println(row2_col2);
        String expected = "Apeirian1";
        Assert.assertEquals(expected,row2_col2);
    }

}
