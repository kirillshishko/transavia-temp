package com.epam.mentoring.framework.webdrivers;

import org.openqa.selenium.WebDriver;

public interface WebDriverFactory {

    WebDriver getDriverOf(BrowserType webDriverType);
}
