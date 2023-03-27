package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WomanPage extends BasePage {

    @FindBy(css = "a[class='img'][title='Dresses']")
    WebElement dressesLinkTextBtn;

    @FindBy(css = "a[class='img'][title='Evening Dresses']")
    WebElement eveningDressesLinkTextBtn;

    @FindBy(css = "a[class='product-name'][title='Printed Dress']")
    WebElement printedDressText;

    @FindBy(css = "img[title='Blouse']")
    WebElement blouseImgLink;

    @FindBy(css = "a[class='product-name'][title='Blouse']")
    WebElement blouseHeader;

    @FindBy(css = "[data-id-product='4']")
    WebElement addPrintedDressProduct;

    public WomanPage(WebDriver driver) {
        super(driver);
    }

    public void clickDresses(){
        clickElement(dressesLinkTextBtn);
    }
    public void clickEveningDresses(){
        clickElement(eveningDressesLinkTextBtn);
    }

    public void hoverPrintedDressProduct(){
        scrollTo(printedDressText);
        hoverOverElement(printedDressText);
    }

    public void clickAddBtnForPrintedDressProduct(){
        clickElement(addPrintedDressProduct);
    }

    public String getBlouseLink(){
        scrollTo(blouseImgLink);
        return blouseImgLink.getAttribute("src");
    }

    public String getBlouseProductHeaderText(){
        scrollTo(blouseHeader);
        return blouseHeader.getText();
    }


}
