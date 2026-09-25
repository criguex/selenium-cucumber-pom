package pages;

import locators.LoginLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.RandomFunctions;
import utils.Waits;


public class LoginPage {
    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, LoginLocators.class);
    }

    public void fillLoginForm(String username, String password){
        Waits.waitFor(4000);
        this.fillInpUsername(username);
        this.fillInpPassword(password);
        this.clickOnBtnLogin();
    }

    public void fillInpUsername(String username){
        Waits.waitElementVisible(LoginLocators.INP_USERNAME, driver, 3);
        LoginLocators.INP_USERNAME.click();
        LoginLocators.INP_USERNAME.sendKeys(username);
    }

    public void fillInpPassword(String password){
        LoginLocators.INP_PASSWORD.click();
        LoginLocators.INP_PASSWORD.sendKeys(password);
    }

    public void clickOnBtnLogin(){
        Waits.waitFor(1000);
        LoginLocators.BTN_LOGIN.click();
    }



}
