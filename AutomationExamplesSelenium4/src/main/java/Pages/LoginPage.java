package Pages;


import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy (name = "email")
    WebElement usernameInput;

    @FindBy (name = "passwd")
    WebElement passwordInput;

    @FindBy (name = "SubmitLogin")
    WebElement loginButton;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements((SearchContext) driver, this);
    }

    public void enterUsername(String username){
        usernameInput.sendKeys(username);
    }
    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }
    public void login(String username, String password){
        enterUsername(username);
        enterPassword(password);
        loginButton.click();
    }


}
