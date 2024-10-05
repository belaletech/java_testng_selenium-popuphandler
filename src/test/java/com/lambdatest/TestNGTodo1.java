package com.lambdatest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestNGTodo1 {
    private RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws Exception {
        // LambdaTest credentials
        String username = "belalahmad";
        String authkey = "cousQqH3syuMR3H55LiQfG4QqCyPHRsZs3XJ3mbEle94hOdYLj";
        String hub = "hub.lambdatest.com/wd/hub";

        // Set up SafariOptions using W3C syntax
        ChromeOptions browserOptions = new ChromeOptions();
//        SafariOptions browserOptions = new SafariOptions();
//        browserOptions.setPlatformName("macOS Sonoma");
        browserOptions.setBrowserVersion("latest");
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.geolocation", 1); // Allow geolocation
        prefs.put("googlegeolocationaccess.enabled", true);
        browserOptions.setExperimentalOption("prefs", prefs);

        // Set up LambdaTest options using W3C syntax
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "belalsafaritest");
        ltOptions.put("project", "popuptest");
        ltOptions.put("w3c", true);  // Ensure that w3c is set to true

//        ltOptions.put("autoAcceptAlerts", true);  // Automatically accept alerts
//        ltOptions.put("autoGrantPermissions", true);  // Automatically grant permissions like location access

        // Set the LambdaTest capabilities as a capability within the SafariOptions
        browserOptions.setCapability("LT:Options", ltOptions);

        // Construct the remote URL and initialize the RemoteWebDriver
        String remoteUrl = "https://" + username + ":" + authkey + "@" + hub;
        driver = new RemoteWebDriver(new URL(remoteUrl), browserOptions);

//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testFormSubmission() throws InterruptedException {
        // Navigate to the website
        driver.get("https://belaletech.github.io/allow-location-permission/"); // Replace with your actual URL
        Thread.sleep(2000); // Wait for 2 seconds

        // Find the input fields and fill them
        WebElement nameInput = driver.findElement(By.id("name")); // Replace with the correct element locator
        nameInput.sendKeys("Belal");
        Thread.sleep(2000); // Wait for 2 seconds

        WebElement emailInput = driver.findElement(By.id("email")); // Replace with the correct element locator
        emailInput.sendKeys("test@example.com");
        Thread.sleep(2000); // Wait for 2 seconds

        // Find and click the submit button
        WebElement submitButton = driver.findElement(By.xpath("//*[@id='locationForm']/button")); // Replace with the correct element locator
        submitButton.click();
        Thread.sleep(2000); // Wait for 2 seconds

        // Handle the location popup
        try {
//            driver.switchTo().alert().accept(); // Accept the location alert
            System.out.println("belal");
        } catch (Exception e) {
            System.out.println("No alert present or auto-grant permissions handled it.");
        }
        Thread.sleep(2000); // Wait for 2 seconds

        // Additional interactions can be added here as needed
        // For example: Validate some action or navigate further
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the driver after execution
        }
    }
}
