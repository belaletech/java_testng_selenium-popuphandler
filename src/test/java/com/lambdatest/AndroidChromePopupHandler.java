package com.lambdatest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AndroidChromePopupHandler {
    private RemoteWebDriver driver;

    // Setup Section: Configure and initialize the WebDriver
    @BeforeTest
    public void setup() {
        try {
            // LambdaTest credentials
            String username = "belalahmad"; // Your username
            String authkey = "cousQqH3syuMR3H55LiQfG4QqCyPHRsZs3XJ3mbEle94hOdYLj"; // Your authkey
            String hub = "hub.lambdatest.com/wd/hub";

            DesiredCapabilities capabilities = new DesiredCapabilities();
            HashMap<String, Object> ltOptions = new HashMap<String, Object>();
            ltOptions.put("w3c", true);
            ltOptions.put("platformName", "android");
            ltOptions.put("deviceName", "Pixel 7");
            ltOptions.put("platformVersion", "14");
            ltOptions.put("build", "Belal-Ios-Chrome-Test");
            ltOptions.put("autoAcceptAlerts", true);
            // Set the LambdaTest capabilities as a capability within the ChromeOptions
            capabilities.setCapability("LT:Options", ltOptions);

            // Construct the remote URL and initialize the RemoteWebDriver
            String remoteUrl = "https://" + username + ":" + authkey + "@" + hub;
            driver = new RemoteWebDriver(new URL(remoteUrl), capabilities);

            // Set implicit wait for the driver
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Error during setup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Test Section: Perform the actions and handle exceptions inside try-catch
    @Test
    public void testPopupHandler() {
        try {
            Thread.sleep(20000);
            // Navigate to the website where location popup needs to be handled
            driver.get("https://belaletech.github.io/allow-location-permission/"); // Replace with your actual URL
            Thread.sleep(2000); // Wait for 2 seconds after loading the page

            // Fill out the form
            WebElement nameInput = driver.findElement(By.xpath("//*[@id=\"name\"]")); // Replace with the correct element locator
            nameInput.sendKeys("belal");
            Thread.sleep(2000); // Wait for 2 seconds after entering name

            WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"email\"]")); // Replace with the correct element locator
            emailInput.sendKeys("belal@gmail.com");
            Thread.sleep(2000); // Wait for 2 seconds after entering email

            WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"locationForm\"]/button")); // Replace with the correct element locator
            submitButton.click();
            Thread.sleep(2000); // Wait for 2 seconds after clicking the button

            Alert savePopupAlert;
            savePopupAlert = driver.switchTo().alert();
            String alertText = savePopupAlert.getText();
            if (alertText.contains("Allow"))
            { savePopupAlert.accept(); }

            Thread.sleep(2000);
            System.out.println("Form submitted successfully and popup handled.");
        } catch (Exception e) {
            System.out.println("Error during test execution: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Teardown Section: Quit the driver after test execution
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver quit successfully.");
        }
    }
}

