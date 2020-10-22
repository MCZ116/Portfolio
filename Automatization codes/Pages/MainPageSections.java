package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPageSections {

    WebDriver driver;

    @FindBy(xpath="/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[1]/a")
    WebElement womenButton;

    @FindBy(xpath="/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[3]/a")
    WebElement tshirtButton;

    @FindBy(xpath = "/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a")
    WebElement dressesButton;

    public MainPageSections(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void pressWomenButton(){ womenButton.click(); }
    public void pressTshirtsButton(){ tshirtButton.click(); }
    public void pressDressesButton(){ dressesButton.click(); }

    public String womenButtonText() { return womenButton.getText();}
    public String tshirtsButtonText() { return tshirtButton.getText();}
    public String dressesButtonText() { return dressesButton.getText();}



}
