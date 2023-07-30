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

    @FindBy(css = "#center_column > div.alert.alert-danger > p")
    WebElement errorMessage;

    @FindBy(css = "#center_column > div.alert.alert-danger > ol > li")
    WebElement authenticationError;

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

    public String getErrorMessage(){ return errorMessage.getText(); }

    public String getAuthenticationError(){ return authenticationError.getText(); }

}
