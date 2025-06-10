package org.example.apply;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LinkedinApply {

    private static final Logger log = LoggerFactory.getLogger(LinkedinApply.class);

    public static void apply(WebDriver driver, String job, String location) {
        try {
            // Step 1: Open the Google homepage
            driver.get("https://www.linkedin.com");
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            login(driver);
            Thread.sleep(5000);
//                searchJob(driver, job);
//                Thread.sleep(1000);
//                selectAllPossibleJobs(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//                driver.quit();
        }
//        }
    }

    private static void searchJob(ChromeDriver driver, String job) throws InterruptedException {

        WebElement jobsTab = driver.findElement(By.xpath("//*[@id=\"ContentNav\"]/li[2]/a"));
        jobsTab.click();

        Thread.sleep(1000);
        WebElement jobSearchText = driver.findElement(By.xpath("//*[@id=\"searchBar-jobTitle\"]"));
        jobSearchText.sendKeys(job);


        WebElement location = driver.findElement(By.xpath("//*[@id=\"searchBar-location\"]"));
        location.sendKeys("Delhi");
        location.sendKeys(Keys.RETURN);

        Thread.sleep(1000);


        WebElement easyApply = driver.findElement(By.xpath("//*[@id=\"app-navigation\"]/div[4]/div[1]/button[1]"));
        easyApply.click();

        Thread.sleep(1000);

        for (int i = 1; i < 30; i++) {
            WebElement jobItem = driver.findElement(By.xpath("//*[@id=\"left-column\"]/div[2]/ul/li[" + i + "]/div/div/div[1]"));
            jobItem.click();
            Thread.sleep(1000);
            WebElement applyButton = driver.findElement(By.xpath("//*[@id=\"app-navigation\"]/div[4]/div[2]/div[2]/div/div[1]/header/div[1]/div[2]/div[2]/div/div/button"));
            applyButton.click();
            Thread.sleep(1000);
            switchWindow(driver);
            Thread.sleep(1000);
            WebElement check = driver.findElement(By.xpath(" //*[@id=\"uATa8\"]/div/label/input"));
            check.click();
            Thread.sleep(1000);
            WebElement continueBtn = driver.findElement(By.xpath("//*[@id=\"ia-container\"]/div/div[1]/div/div/div[2]/div[2]/div/div/main/div[3]/div/button[4]"));
            continueBtn.click();
            Thread.sleep(1000);
            WebElement selectResume = driver.findElement(By.xpath("//*[@id=\"ihl-useId-indeed-theme-provider-haqdlz-4-file-resume-input\"]"));
            selectResume.click();
            Thread.sleep(1000);
            WebElement continueBtn2 = driver.findElement(By.xpath("//*[@id=\"ia-container\"]/div/div[1]/div/div/div[2]/div[2]/div/div/main/div[3]/div/button[4]"));
            continueBtn2.click();
            Thread.sleep(1000);
        }
    }

    private static void login(WebDriver driver) {
        try {
//            WebElement signNowBtn  = driver.findElement(By.xpath("/html/body/nav/div/a[2]"));
//            signNowBtn.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement googleLog = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/section[1]/div/div/a"))
            );
            googleLog.click();

            WebElement emailInput = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div[2]/div[1]/form/div[1]/input"))
            );
            emailInput.sendKeys("abhishekh.ksharma.civ15@itbhu.ac.in");

            WebElement passwordInput = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/main/div[2]/div[1]/form/div[2]/input"))
            );
            passwordInput.sendKeys("Digio60@2274");

            Thread.sleep(4000);
            WebElement button = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"organic-div\"]/form/div[4]/button"))
            );
            button.click();

        } catch (Exception e) {
            //do nothing
            e.printStackTrace();
        }
    }

    private static void switchWindow(ChromeDriver driver) {
        Set<String> windowHandles = driver.getWindowHandles();
        log.info("Window Handles: {}", windowHandles);

        // Create an iterator for the window handles
        Iterator<String> iterator = windowHandles.iterator();
        String firstWindowHandle = iterator.next();
        driver.switchTo().window(firstWindowHandle);
        log.info("Switched to the first window");

        // Switch to the second window (i.e., new tab)
        String secondWindowHandle = iterator.next();
        driver.switchTo().window(secondWindowHandle);
        log.info("Switched to the second window");
    }

}
