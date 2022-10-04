package com.example.FlightSelection.Controllers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.NoSuchElementException;

public class Search {

    Logger logger = LoggerFactory.getLogger(Search.class);
    WebDriver driver;

    public Search(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement search(String locator){
        try {
            WebElement element;
            if (String.valueOf(locator.charAt(0)).equals("/"))
                element = driver.findElement(By.xpath(locator));
            else
                element = driver.findElement(By.cssSelector(locator));

            logger.info("Located: " + locator);
            return element;

        }catch(NoSuchElementException | WebDriverException error){
            logger.error("The element was not found");
            error.printStackTrace();
            throw new NoSuchElementException();
        }
    }

    public WebElement wait_element(WebElement element){
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                        .until(ExpectedConditions.visibilityOf(element));
    }
}
