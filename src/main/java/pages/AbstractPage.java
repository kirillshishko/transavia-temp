package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Waiter;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractPage extends Calendar{

    protected WebDriver driver;
    protected Actions actions;
    protected Waiter waiter;
    protected Logger logger = LogManager.getRootLogger();
    public final static int TIMEOUT = 30;
    protected Select select;
    protected JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;


    public AbstractPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver,this);
        actions = new Actions(driver);
        waiter = new Waiter(driver);
    }

    public void switchToNewTab(){

        for (String childTab : driver.getWindowHandles()){
            driver.switchTo().window(childTab);
        }
        logger.info("switch to new tab");
    }

    public void scrollToElement(WebElement element){

        String coordinates = element.getLocation().toString();
        String jsScript = "window.scroll" + coordinates;
        jsExecutor.executeScript(jsScript);
    }







}
