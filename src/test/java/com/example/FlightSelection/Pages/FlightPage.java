package com.example.FlightSelection.Pages;

import com.example.FlightSelection.Controllers.Search;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FlightPage extends Search {

    WebDriver driver;

    public FlightPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    String airportCodeContainers = ".ib-table-daily__legend-txt";

    public List<WebElement> airportCodeContainers() {
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfAllElements(
                        driver.findElements(By.className(airportCodeContainers))));
    }

}
