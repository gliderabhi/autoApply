package org.example.apply;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LinkedinApply {

    private static final Logger log = LoggerFactory.getLogger(LinkedinApply.class);

    public static void apply(WebDriver driver, String job, String location) {
        try {
            // Step 1: Open the Google homepage
            driver.get("https://www.linkedin.com/notifications/?filter=all");
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
            Thread.sleep(2000);
            WebElement emailInput = driver.findElement(By.xpath("//*[@id=\"username\"]"));
            emailInput.sendKeys("abhishekh.ksharma.civ15@itbhu.ac.in");

            Thread.sleep(200);
            WebElement passwordInput = driver.findElement(By.xpath("//*[@id=\"password\"]"));
            passwordInput.sendKeys("Digio60@2274");

            Thread.sleep(200);

            WebElement continueLogin = driver.findElement(By.xpath("//*[@id=\"organic-div\"]/form/div[4]/button"));
            continueLogin.click();

            Thread.sleep(1000);
        } catch (Exception e) {
            //do nothing
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

    public static void main(String[] args) {
        // Set up ChromeDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Open Naukri.com
        driver.get("https://www.naukri.com/");

        // Maximize the window
        driver.manage().window().maximize();

        // Close any pop-ups if present
        try {
            WebElement closeButton = driver.findElement(By.xpath("//button[contains(text(),'X')]"));
            closeButton.click();
        } catch (Exception e) {
            // No pop-up appeared
        }

        // Wait for the page to load
        try {
            Thread.sleep(5000); // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Click on the 'Jobs' section
        WebElement jobsTab = driver.findElement(By.xpath("//a[@title='Jobs']"));
        jobsTab.click();

        // Wait for the job listings to load
        try {
            Thread.sleep(3000); // Wait for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Search for jobs with desired keywords
        WebElement searchBox = driver.findElement(By.id("qsb-keyskill-sugg"));
        searchBox.sendKeys("Java Developer");

        // Set location (optional)
        WebElement locationBox = driver.findElement(By.id("qsb-location-sugg"));
        locationBox.sendKeys("India");

        // Click on the 'Search' button
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();

        // Wait for search results to load
        try {
            Thread.sleep(5000); // Wait for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Apply filters if needed (e.g., experience, salary)
        // Example: Filter by experience
        WebElement experienceFilter = driver.findElement(By.xpath("//label[contains(text(),'2-5 Yrs')]"));
        experienceFilter.click();

        // Wait for filtered results
        try {
            Thread.sleep(3000); // Wait for 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Iterate through job listings and apply
        List<WebElement> jobListings = driver.findElements(By.xpath("//article[@class='jobTuple']"));
        for (WebElement job : jobListings) {
            try {
                // Click on the job title to open the job details
                WebElement jobTitle = job.findElement(By.xpath(".//a[@class='title fw500 ellipsis']"));
                jobTitle.click();

                // Wait for job details page to load
                Thread.sleep(3000);

                // Click on the 'Apply' button
                WebElement applyButton = driver.findElement(By.xpath("//button[contains(text(),'Apply')]"));
                applyButton.click();

                // Wait for application modal to appear
                Thread.sleep(2000);

                // Fill in application details if required
                // Example: Upload resume
                WebElement uploadResumeButton = driver.findElement(By.xpath("//input[@type='file']"));
                uploadResumeButton.sendKeys("path/to/your/resume.pdf");

                // Submit the application
                WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));
                submitButton.click();

                // Wait for confirmation
                Thread.sleep(2000);

                // Navigate back to the search results
                driver.navigate().back();

                // Wait for the page to load
                Thread.sleep(3000);
            } catch (Exception e) {
                // Handle exceptions (e.g., element not found)
                e.printStackTrace();
            }
        }

        // Close the browser
        driver.quit();
    }

}
