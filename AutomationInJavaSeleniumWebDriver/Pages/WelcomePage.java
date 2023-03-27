package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends BasePage{

    @FindBy(xpath="/html/body/div/div[2]/div/div[3]/div/div/div[1]/ul/li[4]/a/span")
    WebElement welcomeText;

    @FindBy(className="account")
    WebElement loggedUser;

    @FindBy(css=".logout")
    WebElement logoutButton;


    public WelcomePage(WebDriver driver){
        super(driver);
    }

    public String getWelcomeTxt(){
        return welcomeText.getText();
    }

    public String getLoggedUser(){
        return loggedUser.getText();
    }

    public String signOutButtonText(){
        return logoutButton.getText();
    }

    public void logout(){
        logoutButton.click();
    }

}
