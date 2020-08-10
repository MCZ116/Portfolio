package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage{

    WebDriver driver;

    @FindBy(xpath="/html/body/div/div[2]/div/div[3]/div/div/div[1]/ul/li[4]/a/span")
    WebElement welcomeText;

    @FindBy(className="account")
    WebElement loggedUser;

    @FindBy(xpath="/html/body/div/div[1]/header/div[2]/div/div/nav/div[2]/a")
    WebElement logoutButton;


    public WelcomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public String getWelcomeTxt(){
        return welcomeText.getText();
    }

    public String getLoggedUser(){
        return loggedUser.getText();
    }

    public void logout(){
        logoutButton.click();
    }

}