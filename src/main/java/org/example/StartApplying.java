package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class StartApplying {
    public static void main(String[] args) {
        WebDriver driver = enterProfileChrome();
        List<String> jobLists = List.of("system design engineer", "senior java developer", "senior android developer", "full stack developer", "software developer", "senior software developer");
        List<String> locationList = List.of("Remote", "Delhi", "Kolkata");
        for (String job : jobLists) {
            for (String location : locationList) {
                HiristTest.applyHirist(driver, job, location);
            }
        }

        driver.quit();
    }

    private static WebDriver enterProfileChrome() {
        ChromeOptions options = new ChromeOptions();
        String userProfile = "C:\\Users\\coolm\\AppData\\Local\\Google\\Chrome\\User Data";
        options.addArguments("user-data-dir=" + userProfile); // Set the user data directory
        options.addArguments("profile-directory=Profile 1"); // Specify the profile directory

        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}
