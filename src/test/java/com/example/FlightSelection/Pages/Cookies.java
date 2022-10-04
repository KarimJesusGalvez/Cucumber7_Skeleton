package com.example.FlightSelection.Pages;

import com.example.FlightSelection.Controllers.Search;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class Cookies extends Search {

    WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(MainPage.class);

    private String accept_btn = "#onetrust-accept-btn-handler";
    private String configuration_btn = "#onetrust-pc-btn-handler";
    private String policy_anchor = "#onetrust-policy-text > a";

    public WebElement accept_btn() throws InterruptedException {
        Thread.sleep(10);
        return driver.findElement(By.cssSelector(accept_btn));
    }

    public WebElement configuration_btn(){return driver.findElement(By.cssSelector(policy_anchor));}

    public WebElement policy_btn(){
        return driver.findElement(By.cssSelector(policy_anchor));
    }

    public void assertAllDisplayed(){
        logger.info("Asserting cookies banner components...");

        assertAll(
                ()-> assertTrue(accept_btn().isDisplayed()),
                ()-> assertTrue(configuration_btn().isDisplayed()),
                ()-> assertTrue(policy_btn().isDisplayed())
        );
    }

    public Cookies(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
}
