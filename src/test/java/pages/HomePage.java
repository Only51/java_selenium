package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.SeleniumUtils;
import java.util.List;

public class HomePage extends BasePage {
    private WebDriver driver;
    private String divProducrts = "//div[@class='inventory_item_name ']";
    private String spanShoppingCartBadge = "//span[@class='shopping_cart_badge']";
    /**
     * Constructor of the page. Initialize the Page Factory objects.
     *
     * @param driver
     */
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public Boolean verifyProductsDisplay(List<String> lstProducts)
    {
        List<WebElement> products = SeleniumUtils.waitForElements(driver, divProducrts);

        if(lstProducts.size() != products.size()){
            return false;
        }

        for(int i = 0; i < products.size(); i++){
            String actualProduct = products.get(i).getText();
            if(!lstProducts.contains(actualProduct)){
                return false;
            }
        }
        return true;
    }
    public String getNumberOfProductInShoppingCart(){
        WebElement shoppingCartBadge = SeleniumUtils.waitForElementToBeVisible(driver,spanShoppingCartBadge,10);
        if(shoppingCartBadge != null){
            return shoppingCartBadge.getText();
        }
        return "There is no item shopping cart";
    }

    public void clickAddToCart(String productName){
        String btnAddToCart = "//div[@class='inventory_item_label' and contains(.,'"+productName+"')]//following-sibling::div[@class='pricebar']//button";
        SeleniumUtils.waitForElementToBeClickable(driver,btnAddToCart).click();
    }

    public void clickRemoveProduct(String productName){
        String strRemoveToCart = "//div[@class='inventory_item_label' and contains(.,'"+productName+"')]//following-sibling::div[@class='pricebar']//button";
        SeleniumUtils.waitForElementToBeClickable(driver,strRemoveToCart).click();
    }

    public ProductDetailsPage clickProductName (String productName){
        String xpathProductName = "//div[@data-test='inventory-item-name' and contains(.,'"+productName+"')]";
        SeleniumUtils.waitForElement(driver,xpathProductName).click();
        return new ProductDetailsPage(driver);
    }
}
