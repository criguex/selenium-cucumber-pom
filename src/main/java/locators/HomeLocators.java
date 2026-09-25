package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeLocators {
    @FindBy(xpath = "//panel-atom[contains(@title-panel, 'Te damos la bienvenida, ¿Qué deseas hacer?')]" )
    public static WebElement PANEL;

    @FindBy(css = "siigo-header-molecule" )
    public static WebElement MN_SHADOW;

    @FindBy(css = "a[title='View your shopping cart']")
    public static WebElement BTN_SHOPPING_CART;
}
