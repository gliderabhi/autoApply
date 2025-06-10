package org.example.apply;

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
import java.util.Set;

public class GlassdoorApply {

    private static final Logger log = LoggerFactory.getLogger(GlassdoorApply.class);

    public static void apply(WebDriver driver, String job, String location) {
        try {
            // Step 1: Open the Google homepage
            driver.get("https://www.glassdoor.co.in/index.htm");
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            login(driver);
            Thread.sleep(5000);
            searchJob(driver, job, location);
//                Thread.sleep(1000);
//                selectAllPossibleJobs(driver);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//                driver.quit();
        }
//        }
    }

    private static void login(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            WebElement googleLog = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/section[1]/div[2]/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/button"))
            );
            googleLog.click();
            switchWindow((ChromeDriver) driver);

            WebElement emailInput = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[2]/div/div/div[1]/form/span/section/div/div/div[1]/div/div[1]/div/div[1]/input"))
            );
            emailInput.sendKeys("abhishekh.ksharma.civ15@itbhu.ac.in");

            WebElement emailNext = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[3]/div/div[1]/div/div/button/span"))
            );
            emailNext.click();

            WebElement passwordInput = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[2]/div/div/div[1]/form/span/section[2]/div/div/div[1]/div[1]/div/div/div/div/div[1]/div/div[1]/input"))
            );
            passwordInput.sendKeys("Digio60@7255");

            WebElement button = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[3]/div/div[1]/div/div/button/span"))
            );
            button.click();

            WebElement continueButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[3]/div/div/div[2]/div/div/button/span"))
            );
            continueButton.click();

            Thread.sleep(3000);
            switchWindow((ChromeDriver) driver);

        } catch (Exception e) {
            //do nothing
            e.printStackTrace();
        }
    }


    private static void searchJob(WebDriver driver, String job, String location) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            WebElement jobTab = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[1]/div/div[1]/div/ul/li[2]/a"))
            );
            jobTab.click();

            WebElement jobNameInput = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[5]/div[1]/div/div[2]/div[1]/div[2]/input"))
            );
            jobNameInput.sendKeys(job);

            WebElement locationInput = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[5]/div[1]/div/div[2]/div[2]/div[2]/input"))
            );
            locationInput.sendKeys(location);
            locationInput.sendKeys(Keys.ENTER);

            WebElement easyApplyBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[5]/div[4]/div[1]/button[1]"))
            );
            easyApplyBtn.click();


            for (int i = 1; i < 10; i++) {
                applyJobItem((ChromeDriver) driver, wait, i);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void applyJobItem(ChromeDriver driver, WebDriverWait wait, int i) {
        try {
            WebElement jobItem = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[1]/div[2]/ul/li[" + i + "]/div/div"))
            );
            jobItem.click();

            WebElement easyApplyJobItemBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[5]/div[4]/div[2]/div[2]/div/div[1]/header/div[1]/div[2]/div[2]/div/div/button"))
            );
            easyApplyJobItemBtn.click();

            Thread.sleep(5000);

//            switchWindow(driver);


            switchWindow((ChromeDriver) driver);
            WebElement verifyCheckBox = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body//div[1]/div/div[1]/div/label/input"))
            );
            verifyCheckBox.click();

            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div/main/div[3]/div/button[6]/span"))
            );
            continueBtn.click();

            WebElement selectResume = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div/main/div[2]/div/fieldset/div[1]/label/span/span[2]"))
            );
            selectResume.click();

            WebElement continueBtn2 = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[2]/div[2]/div/div/main/div[3]/div/button[6]"))
            );
            continueBtn2.click();

            closeWindowsExceptFirst(driver);
        } catch (Exception e) {
            e.printStackTrace();
//            closeWindowsExceptFirst(driver);
        }
    }

    private static void closeWindowsExceptFirst(ChromeDriver driver) {
        String firstWindowHandle = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            if (!handle.equals(firstWindowHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(firstWindowHandle);
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
