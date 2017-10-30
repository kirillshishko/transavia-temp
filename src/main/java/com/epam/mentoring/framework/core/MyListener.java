package com.epam.mentoring.framework.core;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;


public class MyListener extends TestListenerAdapter {

    private String filePath = "./target/screenshots";
    public Logger logger = Logger.getLogger("wowLogger");
    private WebDriver driver;


    @Override
    public void onTestFailure(ITestResult result) {
        String mesg = ("**** Test " + " " + result.getName() + " " + result.getMethod() + " has failed ****");
        logger.info(mesg);
        String methodName = result.getName().toString().trim();
        takeScreenShot(methodName);
    }

    public void takeScreenShot(String methodName) {
        driver = FactoryDriver.getInstance();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(filePath + methodName + ".png"));
            System.out.println("*** Placed screenshot in " + filePath + " ***");
        } catch (IOException e) {
            logger.info(e);
        }
    }

}