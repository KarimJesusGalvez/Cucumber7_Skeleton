package com.example.FlightSelection;

import com.example.FlightSelection.Pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class GetDriver {

    private JavascriptExecutor js;
    private WebDriver webDriver;
    private Logger logger = LoggerFactory.getLogger(MainPage.class);

    String browser = Optional.ofNullable(System.getProperty("browser")).orElse("Chrome").toLowerCase();
    String headless = Optional.ofNullable(System.getProperty("headless")).orElse("true");


    public GetDriver() {

        if(browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver((ChromeOptions) resolveOptions());
        }
        else if (browser.equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver((FirefoxOptions) resolveOptions());
        }
        else{
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver((EdgeOptions) resolveOptions());
        }

        webDriver.manage().window().setSize(new Dimension(1500,1500));
        logger.info("Browser instance created");
    }

    private AbstractDriverOptions resolveOptions(){

        if(browser.equals("chrome")){

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.setHeadless(Boolean.getBoolean(headless));
            return options;
        }
        else if (browser.equals("firefox")){
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(Boolean.getBoolean(headless));
            return options;
        }
        else {
            EdgeOptions options = new EdgeOptions();
            options.setHeadless(Boolean.getBoolean(headless));
            return options;
        }
    }

    public WebDriver getDriver() {return webDriver;}
    public JavascriptExecutor getJS() {return js;}

    public void closeDriver() {
        webDriver.close();
        logger.warn("Browser closed...");
        webDriver.quit();
        logger.warn("Driver terminated");

    }
}