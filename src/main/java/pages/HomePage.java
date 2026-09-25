package pages;

import locators.HomeLocators;
import locators.LoginLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Waits;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, HomeLocators.class);
    }

    public void checkTextUserName(){
        Waits.waitFor(5000);
        assert HomeLocators.PANEL.isDisplayed();
    }

    public void clickOnCreatedCustomer(){
        Waits.waitElementVisible(HomeLocators.MN_SHADOW, driver, 10);
        WebElement siigoElementCreate = HomeLocators.MN_SHADOW.getShadowRoot()
                .findElement(By.cssSelector("siigo-button-atom[data-id='header-create-button']"));

        siigoElementCreate.getShadowRoot().findElement(By.cssSelector("button")).click();
        HomeLocators.MN_SHADOW.getShadowRoot().findElement(By.linkText("Clientes, proveedores u otros")).click();

    }
}
