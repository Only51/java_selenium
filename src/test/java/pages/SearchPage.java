package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.SeleniumUtils;

public class SearchPage extends BasePage {
    private String radGamePrice = "//button[contains(.,'Game Price')]/following-sibling::div//label[contains(.,'%s')]/preceding-sibling::input";
    private String radGameType = "//button[contains(.,'Game Type')]/following-sibling::div//label[contains(.,'%s')]/parent::div";
    private String gameItems = "//div[@class='inds-category-a-type-item']";
    private String pagingItems = "//span[@class='inline-block']//button";
    private String lblNumberResult = "//span[contains(text(),'Search Result')]/following-sibling::em";
    private String searchFilterOption = "//span[contains(@class,'break-all text-xs') and contains(text(),'%s')]";
    private String lblGameTitle = "//p[@class='inds-category-a-type-subject' and contains(text(),'%s')]";
    private String divProductItem = "//div[contains(@class,'inds-product-relate-games-card-box') and contains(.,'%s')]";


    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public int getTotalElement(String xpath){
        return SeleniumUtils.waitForElements(driver,xpath).size();
    }

    public int calculateTotalGameItems(){
        int totalGameItems = 0;

        int totalPage = getTotalElement(pagingItems);

        int totalGameItemsOf1stPage = getTotalElement(gameItems);

        WebElement firstGameItem = SeleniumUtils.waitForElements(driver,gameItems).get(0);

        totalGameItems = totalGameItemsOf1stPage * (totalPage-1);

        SeleniumUtils.findElement(driver, pagingItems + "[" +totalPage +"]").click();

        String firstGameItemText = firstGameItem.findElement(By.xpath("//p[@class='inds-category-a-type-subject']")).getText();

        String xpathFirstGameItemName = "//p[@class='inds-category-a-type-subject' and contains(text(),'"+firstGameItemText+"')]";

        SeleniumUtils.waitForElementDisappear(driver,xpathFirstGameItemName,20);

        int totalGameItemsOfPage = getTotalElement(gameItems);

        totalGameItems += totalGameItemsOfPage;

        return totalGameItems;
    }

    public void clickGamePrice(String priceValue){
        SeleniumUtils.waitForElementToBeClickable(driver, String.format(radGamePrice,priceValue)).click();
    }

    public void clickGameType(String typeValue){
        SeleniumUtils.waitForElementToBeClickable(driver, String.format(radGameType,typeValue)).click();
    }

    public String getNumberOfResult(){
        return SeleniumUtils.waitForElementToBeClickable(driver, lblNumberResult).getText();
    }

    public void waitForSearchFilterOptionDisplay(String optionName, int timeOutInSecond){
        String ele = String.format(searchFilterOption,optionName);
        SeleniumUtils.waitForElementToBeVisible(driver, ele, timeOutInSecond);
    }

    public void waitForDataSearchResultChange(String initialValue){
        SeleniumUtils.waitForValueChange(driver,lblNumberResult,initialValue,20);
    }

    public void clickGameTitle(String gameName){
        SeleniumUtils.waitForElementToBeClickable(driver,String.format(lblGameTitle,gameName));
    }
}
