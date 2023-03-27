package Tests;

import Base.BaseTest;
import Base.ScreenShotListener;
import Base.TestsMethodsScheme;
import Pages.LoginPage;
import Pages.MainPageSections;
import Pages.WomanPage;
import UserData.UserData;
import org.testng.Assert;
import org.testng.annotations.*;
@Listeners(ScreenShotListener.class)
public class CartTest extends BaseTest implements TestsMethodsScheme {
    MainPageSections mainPageSections;

    WomanPage womanPage;

    LoginPage loginPage;

    public CartTest() {
        super();
    }

    @BeforeClass
    public void setup() {
        loginPage = new LoginPage(driver);
        mainPageSections = new MainPageSections(driver);
        womanPage = new WomanPage(driver);
    }

    @BeforeMethod
    public void goToUrl(){
        goToMainPage();
    }

    @Test(groups = "Functionality",description = "User won't login before adding to cart products, it will need to but in further steps")
    public void TC1_NotLoggedUserAddToCartAndProceed() {

        mainPageSections.clickWomenButton();
        womanPage.clickDresses();
        womanPage.clickEveningDresses();
        womanPage.hoverPrintedDressProduct();
        womanPage.clickAddBtnForPrintedDressProduct();

        Assert.assertEquals(mainPageSections.totalPriceText(),"$57.99");
        Assert.assertEquals(mainPageSections.successAddText(),"Product successfully added to your shopping cart");

        mainPageSections.pressProceedToCheckout();
        Assert.assertEquals(mainPageSections.totalPriceCartText(),"$57.99");
        mainPageSections.pressProceedToCheckoutInCart();
        loginPage.loginToSite(UserData.userName,UserData.userPassword);
        Assert.assertEquals(mainPageSections.getDeliveryAddressText(),"YOUR DELIVERY ADDRESS\n" +
                "Software Tester\n" +
                "13th Street\n" +
                "New York, New York 10011\n" +
                "United States\n" +
                "643467673\n" +
                "Update");
        mainPageSections.pressProceedToCheckoutToConfirmAddress();
        Assert.assertEquals(mainPageSections.getDeliveryTimeText(),"My carrier\n" +
                "Delivery time: Delivery next day!");
        Assert.assertEquals(mainPageSections.getDeliveryCostText(),"$7.00");
    }

    @Test(groups = "Functionality",description = "User will login first before adding products to cart")
    public void TC2_LoggedUserAddToCartAndProceed() {
        loginPage.clickSignUp();
        loginPage.loginToSite(UserData.userName,UserData.userPassword);
        mainPageSections.clickWomenButton();
        womanPage.clickDresses();
        womanPage.clickEveningDresses();
        womanPage.hoverPrintedDressProduct();
        womanPage.clickAddBtnForPrintedDressProduct();

        Assert.assertEquals(mainPageSections.totalPriceText(),"$57.99");
        Assert.assertEquals(mainPageSections.successAddText(),"Product successfully added to your shopping cart");

        mainPageSections.pressProceedToCheckout();
        Assert.assertEquals(mainPageSections.totalPriceCartText(),"$57.99");
        mainPageSections.pressProceedToCheckoutInCart();
        Assert.assertEquals(mainPageSections.getDeliveryAddressText(),"YOUR DELIVERY ADDRESS\n" +
                "Software Tester\n" +
                "13th Street\n" +
                "New York, New York 10011\n" +
                "United States\n" +
                "643467673\n" +
                "Update");
        mainPageSections.pressProceedToCheckoutToConfirmAddress();
        Assert.assertEquals(mainPageSections.getDeliveryTimeText(),"My carrier\n" +
                "Delivery time: Delivery next day!");
        Assert.assertEquals(mainPageSections.getDeliveryCostText(),"$7.00");
    }

    @AfterMethod
    public void cleanCookies(){
        driver.manage().deleteAllCookies();
    }

}
