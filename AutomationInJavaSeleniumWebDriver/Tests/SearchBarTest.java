package Tests;

import Base.BaseTest;
import Base.ScreenShotListener;
import Base.TestsMethodsScheme;
import Pages.LoginPage;
import Pages.SearchPage;
import Pages.WomanPage;
import UserData.UserData;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(ScreenShotListener.class)
public class SearchBarTest extends BaseTest implements TestsMethodsScheme {

    SearchPage searchPage;

    LoginPage loginPage;

    WomanPage womanPage;

    @BeforeClass
    public void setup() {
        searchPage = new SearchPage(driver);
        loginPage = new LoginPage(driver);
        womanPage = new WomanPage(driver);
        driver.get(mainPageURL());
        loginPage.clickSignUp();
        loginPage.loginToSite(UserData.userName,UserData.userPassword);
    }

    @Test(groups = "Functionality",description = "Search not existing product")
    public void TC1_noResultsSearch() {
        searchPage.searchText("Jacket");
        searchPage.pressSearchButton();
        Assert.assertTrue(searchPage.errorSearchText().contains("No results were found for your search"));
    }

    @Test(groups = "Functionality",description = "Existing product search results")
    public void TC2_existingProductSearch() {
        searchPage.searchText("Blouse");
        searchPage.pressSearchButton();
        Assert.assertEquals(womanPage.getBlouseLink(),mainPageURL()+"/img/p/7/7-home_default.jpg");
        Assert.assertEquals(womanPage.getBlouseProductHeaderText(),"Blouse");
    }

    @AfterMethod
    public void clearField(){
        searchPage.clearSearchBar();
    }
}
