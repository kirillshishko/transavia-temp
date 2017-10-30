package transavia.core;

import com.epam.mentoring.framework.core.FactoryDriver;
import com.epam.mentoring.framework.core.PropertyProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import services.HomePageService;

public class BaseTest {
    protected WebDriver driver;
    protected HomePageService homePageService;

    @BeforeSuite
    public void setUp(){
        driver = FactoryDriver.getInstance();
        driver.get(PropertyProvider.getProperty("url"));
        homePageService = new HomePageService(driver);
    }


    @AfterSuite
    public void teardown(){
        FactoryDriver.closeDriver();
        driver = null;
    }
}
