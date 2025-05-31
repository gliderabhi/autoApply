package org.example.apply;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Set;

public class  IndeedApply {

    private static final Logger log = LoggerFactory.getLogger(IndeedApply.class);

    public static void applyHirist(WebDriver driver, String job, String location) {
            driver.manage().window().maximize();
            try {
                // Step 1: Open the Google homepage
                driver.get("https://www.glassdoor.co.in/index.htm");
                driver.manage().window().maximize();
//            driver.manage().deleteAllCookies();
                login(driver);
                Thread.sleep(1000);
                searchJob(driver, job);
//                Thread.sleep(1000);
//                selectAllPossibleJobs(driver);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private static void searchJob(WebDriver driver, String job) throws InterruptedException {

        WebElement jobsTab = driver.findElement(By.xpath("//*[@id=\"InlineLoginModule\"]/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/button"));
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
            Thread.sleep(1000);
            WebElement signIn = driver.findElement(By.xpath("//*[@id=\"InlineLoginModule\"]/div/div[1]/div/div/div/div/div[2]/div/div[1]/div/button"));
            signIn.click();


            Thread.sleep(1000);
            switchWindow(driver);

            Thread.sleep(1000);
            WebElement emailEntry = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[1]/div[1]/div[2]/div/div/div[2]/div/div/div[1]/form/span/section/div/div/div/div/ul/li[1]/div/div[1]/div/div[2]/div"));
            emailEntry.click();

            Thread.sleep(1000);

            WebElement continueLogin = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/c-wiz/div/div[3]/div/div/div[2]/div/div/button"));
            continueLogin.click();

            Thread.sleep(1000);
        } catch (Exception e) {
            //do nothing
        }
    }

    private static void switchWindow(WebDriver driver) {
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
