package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HiristTest {


    private static final Logger log = LoggerFactory.getLogger(HiristTest.class);

    public static void applyHirist(WebDriver driver, String job, String location) {
        try {
            driver.get("https://www.hirist.com/");
//                login(driver);
            Thread.sleep(3000);
            searchJob(driver, job, location);
            Thread.sleep(1000);
            selectAllPossibleJobs(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void selectAllPossibleJobs(WebDriver driver) throws InterruptedException {
        int i = 1;
        while (i < 3) {
            WebElement jobItem = driver.findElement(By.xpath("//*[@id=\"observer-div\"]/div/div[2]/div[2]/div[1]/div[2]/div[2]/a[" + i + "]/div/div[2]/div[1]/a"));
            jobItem.click();
            Set<String> windowHandles = driver.getWindowHandles();
            log.info("Window Handles: {}", windowHandles);

            // Create an iterator for the window handles
            Iterator<String> iterator = windowHandles.iterator();

            // Switch to the first window (if needed, though you might already be on it)
            String firstWindowHandle = iterator.next();
            driver.switchTo().window(firstWindowHandle);
            log.info("Switched to the first window");

            // Switch to the second window (i.e., new tab)
            String secondWindowHandle = iterator.next();
            driver.switchTo().window(secondWindowHandle);
            log.info("Switched to the second window");

            Thread.sleep(1000);

            WebElement apply = driver.findElement(By.xpath("//*[@id=\"buttons-wrapper\"]/div[1]/button"));
            apply.click();

            Thread.sleep(3000);
            driver.close();
            driver.switchTo().window(firstWindowHandle);
            Thread.sleep(1000);
            i++;
        }
    }

    private static void searchJob(WebDriver driver, String jobSearchkey,  String location) throws InterruptedException {
        Thread.sleep(2000);
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"observer-div\"]/div/div[1]/div[1]/div[4]"));
        searchButton.click();

        Thread.sleep(2000);

        WebElement searchInput = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/form[1]/div[2]/input"));
        searchInput.sendKeys(jobSearchkey);
        Thread.sleep(1000);
        experienceSelection(driver);
        Thread.sleep(1000);
        locationSelection(driver,location);
        Thread.sleep(1000);
        WebElement searchButtonClick = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/form[1]/button"));
        searchButtonClick.click();
        Thread.sleep(5000);
    }

    private static void locationSelection(WebDriver driver, String location) {
        WebElement locationDropDown = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/form[1]/div[4]/div[1]/div[2]/a[2]"));
        locationDropDown.click();
        try {
            WebElement inputLocation = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/form[1]/div[4]/div[2]/div[2]/div[1]/input"));
            inputLocation.clear();
            Thread.sleep(1000);
            inputLocation.sendKeys(location);
            Thread.sleep(1000);
            WebElement locationSelection = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/form[1]/div[4]/div[2]/div[2]/div[2]/div/label"));
            Thread.sleep(1000);
            locationSelection.click();
        } catch (Exception e) {

        }
    }

    private static void experienceSelection(WebDriver driver) {
        WebElement experienceDropButton = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/form[1]/div[3]/div[1]/div[2]/a[2]"));
        experienceDropButton.click();

        WebElement experienceSelected = driver.findElement(By.xpath("/html/body/div[3]/div/div/div/form[1]/div[3]/div[2]/ul/li[4]"));
        experienceSelected.click();
    }

    private static void login(WebDriver driver) throws InterruptedException {
        WebElement loginButton = driver.findElement(By.id("user-login-button-id"));
        loginButton.click();

        Thread.sleep(1000);

        WebElement signInButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[1]/div[2]/div/form/div[4]/p/span"));
        signInButton.click();

        Thread.sleep(1000);

        WebElement usernameField = driver.findElement(By.xpath("//*[@id=\"login-email-input\"]"));
        usernameField.sendKeys("abhishekh.ksharma.civ15@itbhu.ac.in");


        WebElement passwordField = driver.findElement(By.xpath("//*[@id=\"loginPassword\"]"));
        passwordField.sendKeys("qWer@2274");


        WebElement login = driver.findElement(By.xpath("//*[@id=\"loginSubmit\"]"));
        login.click();
    }
}
