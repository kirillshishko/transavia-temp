package com.epam.mentoring.framework.core;

import com.epam.mentoring.framework.webdrivers.BrowserType;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


public class FactoryDriver {

    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String GECKODRIVER_EXE_PATH = "src\\main\\resources\\browser\\firefox\\geckodriver.exe";
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String CHROMEDRIVER_EXE_PATH = "src\\main\\resources\\browser\\chrome\\chromedriver.exe";
    private static final Logger logger = Logger.getLogger("wowLogger");
    private static WebDriver driver;

    private FactoryDriver() {
    }

    private static WebDriver getActualDriver() {
        BrowserType type = BrowserType.valueOf(PropertyProvider.getProperty("browser"));
        switch (type) {
            case CHROME:
                driver = createChromeDriver();
                logger.info("Chrome driver created");
                break;
            case FIREFOX:
                driver = createFirefoxDriver();
                logger.info("Firefox driver created");
                break;
            default:
                driver = createFirefoxDriver();
                logger.info("Firefox driver created");

        }

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(PropertyProvider.getProperty("url"));
        return driver;
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            getActualDriver();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
        logger.info("Driver has been closed");
    }

    private static WebDriver createFirefoxDriver() {
        System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_EXE_PATH);
        return new FirefoxDriver();
    }

    private static WebDriver createChromeDriver() {
        System.setProperty(WEBDRIVER_CHROME_DRIVER, CHROMEDRIVER_EXE_PATH);
        return new ChromeDriver();
    }
}

