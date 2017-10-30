package com.epam.mentoring.framework.webdrivers.factory;

import com.epam.mentoring.framework.webdrivers.BrowserType;
import com.epam.mentoring.framework.webdrivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

import static com.google.common.base.Preconditions.checkState;
import static org.testng.util.Strings.isNullOrEmpty;

public class ChromeDriverFactory implements WebDriverFactory {

    @Override
    public WebDriver getDriverOf(BrowserType webDriverType) {

        ChromeOptions options = new ChromeOptions();
        options.setBinary(getChromeBinary());
        return new ChromeDriver(options);
    }

    private File getChromeBinary() {
        String binaryPath = System.getProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY);
        checkState(!isNullOrEmpty(binaryPath), "Chrome driver path must be specified");
        return new File(binaryPath);
    }
        }
