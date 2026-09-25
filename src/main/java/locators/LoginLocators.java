package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginLocators {
    @FindBy(linkText = "Créala aquí")
    public static WebElement LINK_CREATE;

    @FindBy(css = "input-atom[id='username']")
    public static WebElement INP_USERNAME;

    @FindBy(css = "input-atom[id='current-password']")
    public static WebElement INP_PASSWORD;

    @FindBy(css = "button[id='login-submit']")
    public static WebElement BTN_LOGIN;
}
