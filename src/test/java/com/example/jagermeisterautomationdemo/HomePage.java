package com.example.jagermeisterautomationdemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// page_url = https://www.jagermeister.com/en-GB/home
public class HomePage {
    private final static By mainDiv = By.id("app");
    private final static String title = "Be The Meister | Jägermeister";
    private final WebDriver driver;
    private final WebDriverWait wait;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        // Defining Explicit Wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        waitForPageToLoad();
    }

    public void verifyTitle(){
        wait.until(ExpectedConditions.titleIs(title));
    }

    /**
     * This method waits for the page to load by waiting for dayOfBirth to be clickable
     */
    private void waitForPageToLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(mainDiv));
    }
}