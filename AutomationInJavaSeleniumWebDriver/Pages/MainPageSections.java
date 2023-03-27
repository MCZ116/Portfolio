package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPageSections extends BasePage {

    @FindBy(css="a[class='sf-with-ul'][title='Women']")
    WebElement womenButton;

    @FindBy(xpath="/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[3]/a")
    WebElement tshirtButton;

    @FindBy(xpath = "/html/body/div/div[1]/header/div[3]/div/div/div[6]/ul/li[2]/a")
    WebElement dressesButton;

    @FindBy(xpath="/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[3]/span")
    WebElement totalPrice;

    @FindBy(xpath="(//H2)[1]")
    WebElement successAdd;

    @FindBy(css="a[title='Proceed to checkout']")
    WebElement proceedToCheckOut;

    @FindBy(css="a[class='button btn btn-default standard-checkout button-medium'][title='Proceed to checkout']")
    WebElement proceedToCheckOutInCart;

    @FindBy(css="button[name='processAddress']")
    WebElement proceedToCheckOutAddressConfirm;

    @FindBy(css="button[name='processCarrier']")
    WebElement proceedToCheckOutCarrier;

    @FindBy(css="#total_price")
    WebElement totalPriceCart;

    @FindBy(css="#address_delivery")
    WebElement deliveryAddress;

    @FindBy(css=".resume > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(3)")
    WebElement deliveryTime;

    @FindBy(css=".delivery_option_price")
    WebElement deliveryCost;

    public MainPageSections(WebDriver driver){
        super(driver);
    }

    public void clickWomenButton(){
        clickElement(womenButton);
    }

    public void pressProceedToCheckout(){
        clickElement(proceedToCheckOut);
    }

    public void pressProceedToCheckoutInCart(){
        clickElement(proceedToCheckOutInCart);
    }

    public void pressProceedToCheckoutToConfirmAddress(){
        clickElement(proceedToCheckOutAddressConfirm);
    }

    public String getDeliveryAddressText() { return deliveryAddress.getText();}

    public String getDeliveryTimeText() { return deliveryTime.getText();}

    public String getDeliveryCostText() { return deliveryCost.getText();}
    public String totalPriceText() {
        waitForVisibility(totalPrice);
        return totalPrice.getText();
    }

    public String totalPriceCartText() {
        waitForVisibility(totalPriceCart);
        return totalPriceCart.getText();
    }

    public String successAddText() { return successAdd.getText();}



}
