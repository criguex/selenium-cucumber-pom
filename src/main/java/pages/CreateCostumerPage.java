package pages;

import locators.CreateCostumerLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.RandomFunctions;
import utils.Waits;

import java.util.List;

public class CreateCostumerPage {
    WebDriver driver;

    public CreateCostumerPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, CreateCostumerLocators.class);
    }

    public void fillBasicData(){
        Waits.waitElementVisible(CreateCostumerLocators.BTN_SAVE, driver, 10);
        WebElement card = CreateCostumerLocators.LTS_COMPONENTS.get(1);

        List<WebElement> listDropDown = card.findElements(By.cssSelector("siigo-dropdownlist-web"));
        for (WebElement dropDown : listDropDown){
            dropDown.getShadowRoot().findElement(By.cssSelector("div.siigo-components div")).click();
            List<WebElement> options = dropDown.getShadowRoot().findElements(By.cssSelector("span.mdc-list-item__text"));
            options.get(RandomFunctions.getRandomNumber(0,1)).click();
        }

        List<WebElement> inpIdentification = card.findElement(By.cssSelector("siigo-identification-input-web")).getShadowRoot()
                .findElements(By.cssSelector("div.siigo-component input"));
        inpIdentification.get(0).click();
        inpIdentification.get(0).sendKeys(String.valueOf(RandomFunctions.getRandomNumber(300000000, 310000000)));
        inpIdentification.get(1).sendKeys(String.valueOf(RandomFunctions.getRandomNumber(1, 9)));


        List<WebElement> listInpText = card.findElements(By.cssSelector("siigo-textfield-web"));
        for (int i=0; i < 6; i++){
            if(i==0){
                listInpText.get(i).getShadowRoot().findElement(By.cssSelector("div.siigo-components input"))
                        .sendKeys(String.valueOf(RandomFunctions.getRandomNumber(500,600)));
            }
            if(i != 3){
                listInpText.get(i).getShadowRoot().findElement(By.cssSelector("div.siigo-components input"))
                        .sendKeys(RandomFunctions.getRandomText());
            }
        }

        WebElement listInputPhone = card.findElement(By.cssSelector("siigo-phone-web"));
        List<WebElement> partsPhone = listInputPhone.getShadowRoot().findElements(By.cssSelector("div.columns-phone input"));
        partsPhone.get(0).sendKeys("57");
        partsPhone.get(1).sendKeys(String.valueOf(RandomFunctions.getRandomNumber(300000000, 310000000)));
        partsPhone.get(2).sendKeys(String.valueOf(RandomFunctions.getRandomNumber(500,600)));

        SearchContext cityShadow = card.findElement(By.cssSelector("siigo-autocomplete-web")).getShadowRoot();
        cityShadow.findElement(By.cssSelector("div.autocompleteContainer div label")).click();
        System.out.println(cityShadow.findElement(By.cssSelector("div.autocompleteContainer div label")).getText());

    }

    public void fillContact(){
        WebElement card = CreateCostumerLocators.LTS_COMPONENTS.get(3);
        card.findElement(By.cssSelector("div.clickable")).click();
        List<WebElement> listInpText = card.findElements(By.cssSelector("siigo-textfield-web"));
        for (WebElement input : listInpText){
            input.getShadowRoot().findElement(By.cssSelector("div.siigo-components input"))
                    .sendKeys(RandomFunctions.getRandomText());
        }
        card.findElement(By.cssSelector("siigo-emailinput-web")).getShadowRoot()
                .findElement(By.cssSelector("div.siigo-components input"))
                .sendKeys(RandomFunctions.getRandomEmail("prueba"));

        card.findElement(By.cssSelector("siigo-textfield-web[name='Indicative']")).getShadowRoot()
                .findElement(By.cssSelector("div.siigo-components input"))
                .sendKeys("+57");



    }
}
