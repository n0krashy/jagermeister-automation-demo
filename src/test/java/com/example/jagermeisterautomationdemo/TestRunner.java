package com.example.jagermeisterautomationdemo;

import org.testng.annotations.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class TestRunner {
    private WebDriver driver;
    private AgePage agePage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.jagermeister.com/en-GB/home");

        agePage = new AgePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test (priority=1)
    public void validBirthdate() {
        agePage.enterBirthdate(28, 6, 1990);
        HomePage homePage = new HomePage(driver);
        homePage.verifyTitle();
    }

    @Test (priority=3)
    public void tooEarlyBirthYear() {
        agePage.enterBirthdate(28, 6, 1899);
        agePage.verifyUnderOrOverageMessageAppeared();
    }

    @Test (priority=3)
    public void invalidBirthMonth() {
        agePage.enterBirthdate(28, 13, 2000);
        String errorMessage= agePage.getErrorMessage();
        assertEquals(errorMessage, "Invalid month input");
    }

    @Test (priority=3)
    public void invalidBirthDay() {
        agePage.enterBirthdate(40, 6, 2000);
        String errorMessage= agePage.getErrorMessage();
        assertEquals(errorMessage, "Invalid day input");
    }

    @Test (priority=2)
    public void underage() {
        agePage.enterBirthdate(30, 12, 2007);
        agePage.verifyUnderOrOverageMessageAppeared();
    }

    @Test (priority=1)
    public void directAccess() {
        driver.get("https://www.jagermeister.com/en-GB/drinks");
        agePage = new AgePage(driver);
        agePage.verifyTitle();
    }
}
