package com.example.jagermeisterautomationdemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// page_url = https://www.jagermeister.com/en-GB/home
public class AgePage {
    private final static By dayOfBirth = By.id("DD");
    private final static By monthOfBirth = By.id("MM");
    private final static By yearOfBirth = By.id("YYYY");
    private final static By errorMessage = By.cssSelector("div[class^='input_errorMessage']");
    private final static By underOrOverAgeErrorMessage = By.cssSelector("div[class^='verificationError_headline']");


    private final static String title = "Jägermeister";
    private final WebDriver driver;
    private final WebDriverWait wait;

    public AgePage(WebDriver driver) {
        this.driver = driver;
        // Defining Explicit Wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        waitForPageToLoad();
    }

    /**
     * This method enters the day of birth
     */
    private void enterDayOfBirth(int i) {
        driver.findElement(dayOfBirth).sendKeys(Integer.toString(i));
    }

    /**
     * This method enters the month of birth
     */
    private void enterMonthOfBirth(int i) {
        driver.findElement(monthOfBirth).sendKeys(Integer.toString(i));
    }

    /**
     * This method enters the year of birth
     */
    private void enterYearOfBirth(int i) {
        driver.findElement(yearOfBirth).sendKeys(Integer.toString(i));
    }

    /**
     * This method enters the birthdate
     */
    public void enterBirthdate(int day, int month, int year) {
        enterDayOfBirth(day);
        enterMonthOfBirth(month);
        enterYearOfBirth(year);
    }

    public void verifyTitle(){
        wait.until(ExpectedConditions.titleIs(title));
    }

    /**
     * This method gets error message
     */
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    /**
     * This method verifies that error message appeared when the entered age is underage or over the valid age.
     */
    public void verifyUnderOrOverageMessageAppeared() {
        driver.findElement(underOrOverAgeErrorMessage).getText();
    }

    /**
     * This method waits for the page to load by waiting for dayOfBirth to be clickable
     */
    private void waitForPageToLoad() {
        wait.until(ExpectedConditions.elementToBeClickable(dayOfBirth));
    }
}
