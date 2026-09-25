package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    public static void waitElementVisible(WebElement element, WebDriver driver, int seconds){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(d -> element.isDisplayed());
    }

    public static void waitElementVisibleForFiveSeconds(WebElement element, WebDriver driver){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> element.isDisplayed());
    }

    public static void waitElementEnable(WebElement element, WebDriver driver){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(d -> element.isEnabled());
    }

    public static void waitFor(int milisecounds) {
        try {
            Thread.sleep(milisecounds);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

}
