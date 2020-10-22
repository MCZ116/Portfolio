package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(name="email")
    WebElement usernameInput;

    @FindBy(name="passwd")
    WebElement passwordInput;

    @FindBy(name="SubmitLogin")
    WebElement loginButton;


    //konstruktor
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username){
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }

    public void login(){
        loginButton.click();
    }

    public void loginToSite(String correctUsername,String correctPassword )
    {
        this.enterUsername(correctUsername);
        this.enterPassword(correctPassword);
        this.login();
    }

}
