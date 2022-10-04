package com.example.FlightSelection.Steps;

import com.example.FlightSelection.Pages.BookingData;
import com.example.FlightSelection.Pages.Cookies;
import com.example.FlightSelection.Pages.FlightPage;
import com.example.FlightSelection.Pages.MainPage;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import com.example.FlightSelection.GetDriver;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class FlightBooking {

    private static GetDriver driver;
    private static Cookies cookies;
    private static MainPage mainPage;
    private static BookingData bookingData;
    private static FlightPage flightPage;

    private WebDriver getDriver(){
        return driver.getDriver();
    }

    @AfterAll
    public static void closeDriver() {
        driver.closeDriver();
    }

    @BeforeAll
    public static void createDriver() {
        driver = new GetDriver();
        cookies = new Cookies(driver.getDriver());
        mainPage = new MainPage(driver.getDriver());
        bookingData = new BookingData();
        flightPage = new FlightPage(driver.getDriver());
    }

    @Given("I navigate to iberia.com domain")
    public void iNavigateToIberiaComDomain() {
        driver.getDriver().get(
                System.getProperty("domain.DNS"));
    }

    @And("I accept all cookies")
    public void iAcceptAllCookies() throws InterruptedException {
        cookies.assertAllDisplayed();
        cookies.accept_btn().click();
    }


    @Given("I am in the main page")
    public void iAmInTheMainPage() {
        mainPage.searchBox.assertAllDisplayed();
    }

    @When("I write the data")
    public void iWriteTheData() throws InterruptedException {

        BookingData.ExampleData data = bookingData.getData(0);

        mainPage.searchBox.flightOrigin().clear();
        mainPage.searchBox.flightOrigin().sendKeys(data.getOrigen());
        mainPage.searchBox.flightOrigin().sendKeys(Keys.ENTER);
        mainPage.searchBox.flightDestiny().sendKeys(data.getDestino());
        mainPage.searchBox.flightDestiny().sendKeys(Keys.ENTER);

        mainPage.searchBox.dateEntry().sendKeys(data.getFecha_ida().substring(0,2));
        mainPage.searchBox.dateEntry().sendKeys(data.getFecha_ida().substring(3,5));
        mainPage.searchBox.dateEntry().sendKeys(data.getFecha_ida().substring(6,10));

        mainPage.searchBox.dateExit().sendKeys(data.getFecha_vuelta().substring(0,2));
        mainPage.searchBox.dateExit().sendKeys(data.getFecha_vuelta().substring(3,5));
        mainPage.searchBox.dateExit().sendKeys(data.getFecha_vuelta().substring(6,10));

        if (Integer.parseInt(data.getAdultos()) > 1){ //Default value 1
            // TODO select more passengers
            throw new ParameterResolutionException("Pending implementation for " +data.getAdultos() + " passenger");
        }
        if (!mainPage.searchBox.journey_return().getText().toLowerCase().contains("ida y vuelta")){  //Default value Ida y Vuelta
            //TODO select other journey types
            throw new ParameterResolutionException("Pending implementation for " + mainPage.searchBox.journey_return().getText());
        }
    }

    @Then("I check the data and submit it")
    public void iCheckTheDataAndSubmitIt() {
        assertAll(
                ()-> assertEquals("Madrid (MAD)",mainPage.searchBox.flightOrigin().getAttribute("value")),
                ()-> assertEquals("París (PAR)",mainPage.searchBox.flightDestiny().getAttribute("value"))
        );

        mainPage.searchBox.searchBtn().click();
    }

    @Then("I check the {string}, {string} and submit it")
    public void iCheckTheOriginDestinyAndSubmitIt(String origen, String destino) {

    }

    @And("see flight availability")
    public void seeFlightAvailability() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("www.iberia.com/flights"));
        assertTrue(getDriver().getCurrentUrl().contains("www.iberia.com/flights"));
    }
}
