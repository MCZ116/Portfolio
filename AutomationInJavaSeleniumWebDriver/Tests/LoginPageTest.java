package Tests;

import Base.BaseTest;
import Base.ScreenShotListener;
import Base.TestsMethodsScheme;
import Pages.LoginPage;
import Pages.WelcomePage;
import UserData.UserData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ScreenShotListener.class)
public class LoginPageTest extends BaseTest implements TestsMethodsScheme {

    LoginPage loginPage;
    WelcomePage welcomePage;

    @BeforeClass
    public void setup() {
        loginPage = new LoginPage(driver);
        welcomePage = new WelcomePage(driver);
        goToMainPage();
        loginPage.clickSignUp();
        loginPage.loginToSite(UserData.userName,UserData.userPassword);
    }

    @Test(groups = "Functionality",description = "Login to page with valid data")
    public void TC1_validDataLoginAndLogOut() {
        loginPage.loginToSite(UserData.userName,UserData.userPassword);
        Assert.assertEquals(welcomePage.getWelcomeTxt().toLowerCase(), "my personal information");
        Assert.assertTrue(welcomePage.getLoggedUser().toLowerCase().contains("software tester"));
        Assert.assertEquals(driver.getCurrentUrl(),mainPageURL()+"/index.php?controller=my-account");
    }

    @Test(groups = "Functionality",description = "Attempt to login with invalid data")
    public void TC2_invalidCredentialsLogin() {
        loginPage.loginToSite("noname@gmail.com","wrongpsswd");
        Assert.assertTrue(loginPage.getErrorMessage().toLowerCase().contains("there is 1 error"));
        Assert.assertTrue(loginPage.getAuthenticationError().toLowerCase().contains("software tester"));
        Assert.assertEquals(driver.getCurrentUrl(),mainPageURL()+"/index.php?controller=authentication");
    }

    @Test(groups = "Functionality",description = "Logout and redirect to login page")
    public void TC3_correctLogOut() {
        loginPage.loginToSite(UserData.userName,UserData.userPassword);
        Assert.assertEquals(welcomePage.signOutButtonText(),"Sign out");
        welcomePage.logout();
        Assert.assertEquals(loginPage.getLoginPageHeader(),"AUTHENTICATION");
        Assert.assertEquals(driver.getCurrentUrl(),mainPageURL()+"/index.php?controller=authentication&back=my-account");
    }
}
