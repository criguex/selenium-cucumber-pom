package locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CreateCostumerLocators {
    @FindBy(xpath = "//button[text()=' Guardar ']")
    public static WebElement BTN_SAVE;

    @FindBy(how = How.CLASS_NAME, using = "siigo-components")
    public static List<WebElement> LTS_COMPONENTS;
}
