package com.example.FlightSelection.Pages;


import com.example.FlightSelection.Controllers.Search;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage extends Search {

    WebDriver driver;
    private Logger logger = LoggerFactory.getLogger(MainPage.class);

    public FlightSearchBox searchBox = new FlightSearchBox();

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebElement FlightSearchBoxContainer() {
        return search("#m-tab-searcher-1");}

    public class FlightSearchBox {

        public WebElement searchBtn() {
            return search("//button[@id='buttonSubmit1']");}

        public WebElement flightOnly_Btn() {
            return search("//li//a[@href='#tab-searcher-1']");}

        public WebElement flightOrigin() {
            return search("//input[@name='flight_origin']");}

        public WebElement flightDestiny() {
            return search("//input[@id='flight_destiny1']");}

        public WebElement dateEntry() {
            return search("//input[@id='flight_round_date1']");}

        public WebElement dateExit() {
            return search("//input[@id='flight_return_date1']");}

        public WebElement passengers() {
            return search("//button[@id='flight_passengers1']");}

        public WebElement journey_return() {
            return search("//span[@id='ticketops-seeker-button']");}

        public void assertAllDisplayed(){
            logger.info("Asserting Flight Search Box components...");
            assertAll(
                    ()-> assertTrue(flightOnly_Btn().isDisplayed()),
                    ()-> assertTrue(flightOrigin().isDisplayed()),
                    ()-> assertTrue(flightDestiny().isDisplayed()),
                    ()-> assertTrue(dateEntry().isDisplayed()),
                    ()-> assertTrue(dateExit().isDisplayed()),
                    ()-> assertTrue(passengers().isDisplayed()),
                    ()-> assertTrue(journey_return().isDisplayed()),
                    ()-> assertTrue(searchBtn().isDisplayed())
            );
        }
    }
}
