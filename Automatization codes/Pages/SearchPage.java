package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    WebDriver driver;

    @FindBy(id="search_query_top")
    WebElement searchBar;

    @FindBy(name="submit_search")
    WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"center_column\"]/p")
    WebElement incorrectSearchText;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchText(String text){searchBar.sendKeys(text);}
    public void pressSearchButton(){
        searchButton.click();
    }
    public String errorSearchText() { return incorrectSearchText.getText();}



}
