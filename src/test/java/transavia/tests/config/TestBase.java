package transavia.tests.config;

import drivermanager.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.HomePageSteps;
import utils.PropertyProvider;

public abstract class TestBase {

    protected WebDriver driver;
    protected HomePageSteps homePageSteps;


    @BeforeMethod
    public void setUp(){
        driver = DriverFactory.getInstance();
        driver.get(PropertyProvider.getProperty("url"));
        homePageSteps = new HomePageSteps(driver);
    }


    @AfterMethod
    public void teardown(){
        DriverFactory.closeDriver();
        driver = null;
    }
}
