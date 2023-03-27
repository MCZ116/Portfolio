package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(name="email")
    WebElement usernameInput;

    @FindBy(name="passwd")
    WebElement passwordInput;

    @FindBy(name="SubmitLogin")
    WebElement loginButton;

    @FindBy(css=".login")
    WebElement signUpBtn;

    @FindBy(css = ".page-heading")
    WebElement loginPageHeader;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public String getLoginPageHeader(){
        return loginPageHeader.getText();
    }

    public void clickSignUp(){
        clickElement(signUpBtn);
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
        waitForVisibility(usernameInput);
        this.enterUsername(correctUsername);
        this.enterPassword(correctPassword);
        this.login();
    }


}
