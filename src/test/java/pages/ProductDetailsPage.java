package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.SeleniumUtils;

public class ProductDetailsPage extends BasePage {
    private WebDriver driver;
    private String divProductName = "//div[@data-test='inventory-item-name']";
    private String divProductDescription = "//div[@data-test='inventory-item-desc']";
    private String divProductPrice = "//div[@data-test='inventory-item-price']";
    private String spanShoppingCartBadge = "//span[@class='shopping_cart_badge']";
    private String btnAddToCart = "//button[@id='add-to-cart']";
    private String btnRemove = "//button[@id='remove']";

    /**
     * Constructor of the page. Initialize the Page Factory objects.
     *
     * @param driver
     */
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getProductName(){
        return SeleniumUtils.waitForElement(driver,divProductName).getText();
    }

    public String getProductDescription(){
        return SeleniumUtils.waitForElement(driver,divProductDescription).getText();
    }

    public String getProductPrice(){
        return SeleniumUtils.waitForElement(driver,divProductPrice).getText();
    }

    public String getNumberOfProductInShoppingCart(){
        WebElement shoppingCartBadge = SeleniumUtils.waitForElementToBeVisible(driver,spanShoppingCartBadge,10);
        if(shoppingCartBadge != null){
            return shoppingCartBadge.getText();
        }
        return "There is no item shopping cart";
    }

    public void clickAddToCart(){
        SeleniumUtils.waitForElementToBeClickable(driver,btnAddToCart).click();
    }

    public void clickRemoveProduct(){
        SeleniumUtils.waitForElementToBeClickable(driver,btnRemove).click();
    }

}
