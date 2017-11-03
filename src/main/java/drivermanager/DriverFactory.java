package drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.PropertyProvider;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static BrowserType browserType;
    private static WebDriver driver;
    private static final String CHROMEDRIVER = "webdriver.chrome.driver";
    private static final String CHROMEDRIVER_PATH = ".\\chromedriver\\chromedriver.exe";
    private static final String GECKODRIVER = "webdriver.gecko.driver";
    private static final String GECKODRIVER_PATH = ".\\geckodriver\\geckodriver.exe";



    private static WebDriver getDriver() {

        browserType = BrowserType.valueOf(PropertyProvider.getProperty("browser"));

        switch (browserType) {

            case CHROME:
                driver = createChromeDriver();
                break;

            case FIREFOX:
                driver = createFirefoxDriver();
                break;

            case REMOTEDRIVER:

                break;

            default:
                driver = createChromeDriver();
        }

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getInstance(){
        if(driver == null){
            getDriver();
        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;

    }


    private static WebDriver createFirefoxDriver(){

        System.setProperty(GECKODRIVER,GECKODRIVER_PATH);
        return new FirefoxDriver();
    }

    private static WebDriver createChromeDriver(){

        System.setProperty(CHROMEDRIVER,CHROMEDRIVER_PATH);
        return new ChromeDriver();
    }



}
