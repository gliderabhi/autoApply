package org.example.apply;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.*;

public class HiristTest {


    private static final Logger log = LoggerFactory.getLogger(HiristTest.class);

    public static void applyHirist(WebDriver driver, String job, String location) {
        try {
            WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            driver.get("https://www.hirist.com/");
            login(driverWait);
            searchJob(driverWait, job, location);
            selectAllPossibleJobs(driverWait, driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void selectAllPossibleJobs(WebDriverWait driverWait, WebDriver driver) {
        int i = 1;
        while (i < 30) {
            try {
                WebElement jobItem = driverWait.until(
                        ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"__next\"]/div[3]/div/div[2]/div[1]/div/div[2]/div[2]/div/div[" + i + "]/div/div[2]/div"))
                );
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
                try {
                    WebElement apply = driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[3]/div[2]/div[2]/div[1]/div[1]/div[2]/div/button[1]")));
                    apply.click();
                    WebElement apply2 = driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div[3]/div[2]/div[2]/div[1]/div[1]/div[3]/div/button[1]")));
                    apply2.click();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                canFillForm(driver, driverWait);
                Thread.sleep(2000);
                driver.close();
                driver.switchTo().window(firstWindowHandle);
                Thread.sleep(1000);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                scroll(driver);
            }
        }
    }

    private static void canFillForm(WebDriver driver, WebDriverWait driverWait) {
        try {
            List<WebElement> questionContainers = driver.findElements(By.className("screening-question-container"));
            Map<String, String> questionAnswerMap = new HashMap<>();
            questionAnswerMap.put("java", "Java 1.8, Java 11, Java 17, Java 20");
            questionAnswerMap.put("serving", "No");
            questionAnswerMap.put("notice", "30 days");
            questionAnswerMap.put("days", "30 days");
            questionAnswerMap.put("years", "4.8 yrs");
            questionAnswerMap.put("expected", "");
            questionAnswerMap.put("current", "");
            questionAnswerMap.put("ECTC", "");
            questionAnswerMap.put("CTC", "");
            questionAnswerMap.put("aws", "yes");



            for (WebElement container : questionContainers) {
                // Try to get question text
                String questionText = "";
                try {
                    questionText = container.findElement(By.cssSelector(".question-text")).getText().trim();
                } catch (NoSuchElementException e) {
                    // Skip if no question text found
                    continue;
                }

                // Check for Yes/No container
                List<WebElement> yesNoType = container.findElements(By.className("yes-no-answer-question-container"));
                if (!yesNoType.isEmpty()) {
                    // Click "Yes" radio for example
                    try {
                        WebElement yesRadio = container.findElement(By.cssSelector("input[id$='-op-yes']"));
                        if (yesRadio.isDisplayed() && yesRadio.isEnabled()) {
                            yesRadio.click();
                        }
                    } catch (NoSuchElementException ignored) {
                    }
                    continue;
                }

                // Match using partial keyword
                String matchedAnswer = null;
                for (Map.Entry<String, String> entry : questionAnswerMap.entrySet()) {
                    if (questionText.contains(entry.getKey().toLowerCase())) {
                        matchedAnswer = entry.getValue();
                        break;
                    }
                }

                if (matchedAnswer == null) {
                    continue;
                }
                // Check for Short Answer container
                List<WebElement> shortAnswerType = container.findElements(By.className("short-answer-question-container"));
                if (!shortAnswerType.isEmpty()) {
                    try {
                        WebElement textarea = container.findElement(By.tagName("textarea"));
                        textarea.sendKeys(matchedAnswer);
                    } catch (NoSuchElementException ignored) {
                    }
                    continue;
                }
            }

            WebElement apply = driverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div/div/div[2]/div/div/div/div[4]/button")));
            apply.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void searchJob(WebDriverWait driverWait, String jobSearchkey, String location) throws InterruptedException {
        WebElement searchButton = driverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div[1]/header/div/div/div[4]/div[2]"))
        );
        searchButton.click();

        WebElement searchInput = driverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div/form/div[1]/div[2]/div/div/input"))
        );
        searchInput.sendKeys(jobSearchkey);
        experienceSelection(driverWait);
        locationSelection(driverWait, location);
        WebElement searchButtonClick = driverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div/form/div[2]/div/div[4]/button"))
        );
        searchButtonClick.click();
        Thread.sleep(2000);
    }

    private static void scroll(WebDriver driver) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 400);");
            Thread.sleep(1000);
            js.executeScript("window.scrollBy(0, -600);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void locationSelection(WebDriverWait driverWait, String location) {
        WebElement locationDropDown = driverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div/form/div[2]/div/div[2]/div/div/div"))
        );
        locationDropDown.click();
        try {
            WebElement inputLocation = driverWait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div/form/div[2]/div/div[2]/div/div/div[2]/div[3]/ul/li[1]/div/div/input"))
            );
            inputLocation.clear();
            inputLocation.sendKeys(location);
            WebElement locationSelection = driverWait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div/form/div[2]/div/div[2]/div/div/div[2]/div[3]/ul/li[2]/p"))
            );
            locationSelection.click();
        } catch (Exception e) {

        }
    }

    private static void experienceSelection(WebDriverWait driverWait) {
        WebElement experienceDropButton = driverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div/form/div[2]/div/div[1]/div/div"))
        );
        experienceDropButton.click();

        WebElement experienceSelected = driverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div/form/div[2]/div/div[1]/div/div/div[2]/div[3]/ul/li[4]/p"))
        );
        experienceSelected.click();
    }

    private static void login(WebDriverWait driverWait) throws InterruptedException {
        WebElement loginButton = driverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[1]/div[1]/div[2]/button[3]"))
        );
        loginButton.click();

        WebElement signInButton = driverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div/div/div[2]/div[2]/div/p[3]/a"))
        );
        signInButton.click();


        WebElement usernameField = driverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\":r7:\"]"))
        );
        usernameField.sendKeys("abhishekh.ksharma.civ15@itbhu.ac.in");

        WebElement passwordField = driverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\":ra:\"]"))
        );
        passwordField.sendKeys("Digio60@2274");

        WebElement login = driverWait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/div/div/div[2]/div[2]/div/div/form/button"))
        );
        login.click();
    }
}
