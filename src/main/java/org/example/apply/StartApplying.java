package org.example.apply;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StartApplying {
    public static void main(String[] args) {
        try {
            List<String> jobLists = List.of("senior java developer");
            List<String> locationList = List.of("Remote");
            for (String job : jobLists) {
                for (String location : locationList) {
                    WebDriver driver = enterProfileChrome();
                    HiristTest.applyHirist(driver, job, location);
//                    LinkedinApply.apply(driver, job, location);
//                GlassdoorApply.apply(driver, job, location);
                    driver.quit();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static WebDriver enterProfileChrome() {
        ChromeOptions options = new ChromeOptions();
//        String userProfile = "/Users/abhisheksharma/Library/Application Support/Google/Chrome/";
//        options.addArguments("user-data-dir=" + userProfile); // Set the user data directory
//        options.addArguments("profile-directory=Profile 1"); // Specify the profile directory

        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}
