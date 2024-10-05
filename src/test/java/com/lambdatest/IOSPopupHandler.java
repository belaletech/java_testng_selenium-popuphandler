package com.lambdatest;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class IOSPopupHandler {
    private RemoteWebDriver driver;

    @BeforeClass
    public void setup() throws MalformedURLException {
        String username = "belalahmad";
        String authkey = "cousQqH3syuMR3H55LiQfG4QqCyPHRsZs3XJ3mbEle94hOdYLj";
        String hub = "hub.lambdatest.com/wd/hub";

        // Use SafariOptions for iOS testing
//        SafariOptions safariOptions = new SafariOptions();
//        safariOptions.setPlatformName("ios");
//        safariOptions.setBrowserVersion("latest");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("w3c", true);
        ltOptions.put("platformName", "ios");
        ltOptions.put("deviceName", "iPhone 15 Pro");
        ltOptions.put("platformVersion", "17.0");
        ltOptions.put("build", "Belal-Ios-Chrome-Test");
        ltOptions.put("project", "PopupHandlerProject");
        ltOptions.put("safariAllowPopups", true);
//        ltOptions.put("autoGrantPermissions", true); // Automatically grant permissions like location access
//        ltOptions.put("autoAcceptAlerts", true); // Automatically accept alerts if present

        // Set LambdaTest capabilities in SafariOptions
        capabilities.setCapability("LT:Options", ltOptions);

        // Construct the remote URL for LambdaTest
        String remoteUrl = "https://" + username + ":" + authkey + "@" + hub;
        driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);

        // Use Duration for timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Navigate to the testing website
        driver.get("https://belaletech.github.io/allow-location-permission/");
//        driver.findElement(By.id("Allow")).click(); // Moved this after driver initialization
    }

    @Test
    public void testWebsite() {
        try {
            // Enter name
            driver.findElement(By.id("name")).sendKeys("Belal");
            Thread.sleep(3000); // Wait 3 seconds

            // Enter email
            driver.findElement(By.id("email")).sendKeys("belalahmad@lambdatest.com");
            Thread.sleep(3000); // Wait 3 seconds

            // Click the submit button
            driver.findElement(By.xpath("//*[@id='locationForm']/button")).click();
            Thread.sleep(2000); // Wait 2 seconds to observe the location pop-up handling


            driver.switchTo().alert();
            driver.findElement(By.id("Allow While Using App")).click();

            Thread.sleep(3000);

        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception occurred: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                Thread.sleep(5000); // Wait 5 seconds before quitting the browser
                driver.quit();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
