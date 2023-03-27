package Pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;

    protected static WebDriverWait driverWait;

    protected static JavascriptExecutor js;

    protected static Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        driverWait = new WebDriverWait(driver,Duration.ofSeconds(60));
        js = (JavascriptExecutor)driver;
        actions = new Actions(driver);
    }

    protected void scrollTo(WebElement element){
        js.executeScript("var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));",element);
    }

    public void scrollToBottom(){
        js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
    }

    public void findByCssSelector(String element){
        driver.findElement(By.cssSelector(element));
    }
    protected WebElement findByLinkText(String linkText){
       return driver.findElement(By.linkText(linkText));
    }

    public void clickElementByLinkText(String linkText){
        WebElement element = findByLinkText(linkText);
        element.click();
    }

    protected void clickElement(WebElement element){
        scrollTo(element);
        waitForVisibility(element);
        element.click();
    }

    protected void hoverOverElement(WebElement element){
        actions.moveToElement(element).perform();
    }

    protected void waitForVisibility(WebElement element){
        driverWait.until(ExpectedConditions.visibilityOf(element));
    }

}
