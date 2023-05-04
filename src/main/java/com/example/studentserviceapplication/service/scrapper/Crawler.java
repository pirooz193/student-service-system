package com.example.studentserviceapplication.service.scrapper;

import com.example.studentserviceapplication.domain.Food;
import com.example.studentserviceapplication.domain.enumuration.MealType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Crawler {
    public static void main(String[] args) throws InterruptedException {
        crawl();
    }

    public static void crawl() throws InterruptedException {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/home/pirooz/myFiles/chromedriver_linux64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        // Create a new instance of the Chrome driver
//        System.setProperty("webdriver.http.factory", "jdk-http-client");
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);

        // Navigate to the login page
        driver.get("https://self.shahroodut.ac.ir/identity/login");

        WebElement returnPageButton = driver.findElement(By.linkText("بازگشت به صفحه اصلی"));
        returnPageButton.click();

        // Find the username and password fields and enter the appropriate values
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("9718223");

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("Az@r1234");

        // Submit the form
        WebElement submitButton = driver.findElement(By.className("btn"));
        submitButton.click();

        // Find the first "رزرو غذا" link and click on it
        WebElement firstLink = driver.findElement(By.xpath("//span[contains(text(), 'رزرو غذا')]"));
        firstLink.click();

// Wait for the submenu to be displayed and find the second "رزرو غذا" link
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement submenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.treeview ul.treeview-menu")));
        WebElement secondLink = submenu.findElement(By.cssSelector("li.child a[href='#!/Reservation'][my-refresh=''] span"));
        secondLink.click();

        Thread.sleep(7000);
        WebElement btnNextWeek = driver.findElement(By.xpath("//button[contains(@ng-click, 'browseWeek(startdate,7)')]"));
        btnNextWeek.click();

        WebElement btnWeekPlan = driver.findElement(By.id("shopping"));
        btnWeekPlan.click();

        WebElement meals = driver.findElement(By.tagName("tbody"));
//        System.out.println(meals.getText());
        List<String> meal = new ArrayList<>(List.of(meals.getText().trim().split("\n")));
//        Arrays.stream(meals.getText().trim().split("\n")).map(meal).collect(Collectors.toList());

//        System.out.println(meal);;


        Map<String, List<String>> days = new HashMap<>();
        days.put("sat", new ArrayList<>());
        days.put("sun", new ArrayList<>());
        days.put("mon", new ArrayList<>());
        days.put("tu", new ArrayList<>());
        days.put("wed", new ArrayList<>());
//        days.put("thu" , new ArrayList<>());

        List<Food> foods = new ArrayList<>();

        for (int i = 9, j = 0; i <= 13; i++, j++) {
            Food breakfast = new Food();
            breakfast.setTitle(meal.get(i));
            breakfast.setMealType(MealType.BREAKFAST);
            breakfast.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.EPOCH.getMonthValue(), LocalDate.now().getDayOfMonth() + 2 + j));
            foods.add(breakfast);
        }

        for (int i = 15, j = 0; i <= 24; i++, j++) {
            Food lunch = new Food();
            lunch.setTitle(meal.get(i));
            lunch.setMealType(MealType.LUNCH);
            lunch.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.EPOCH.getMonthValue(), LocalDate.now().getDayOfMonth() + 2 + j));
            foods.add(lunch);
        }

        for (int i = 26, j = 0; i <= 35; i++, j++) {
            Food dinner = new Food();
            dinner.setTitle(meal.get(i));
            dinner.setMealType(MealType.DINNER);
            dinner.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.EPOCH.getMonthValue(), LocalDate.now().getDayOfMonth() + 2 + j));
            foods.add(dinner);
        }

        System.out.println("foods : " + foods);

        Food satBreakFast = new Food();
        satBreakFast.setTitle(meal.get(9));
        satBreakFast.setMealType(MealType.BREAKFAST);
        satBreakFast.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.EPOCH.getMonthValue(), LocalDate.now().getDayOfMonth() + 2));

        Food satLunch1 = new Food();
        satBreakFast.setTitle(meal.get(15));
        satBreakFast.setMealType(MealType.LUNCH);
        satBreakFast.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.EPOCH.getMonthValue(), LocalDate.now().getDayOfMonth() + 2));

        Food satLunch2 = new Food();
        satBreakFast.setTitle(meal.get(16));
        satBreakFast.setMealType(MealType.BREAKFAST);
        satBreakFast.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.EPOCH.getMonthValue(), LocalDate.now().getDayOfMonth() + 2));

        Food satDinner1 = new Food();
        satBreakFast.setTitle(meal.get(26));
        satBreakFast.setMealType(MealType.DINNER);
        satBreakFast.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.EPOCH.getMonthValue(), LocalDate.now().getDayOfMonth() + 2));

        Food satDinner2 = new Food();
        satBreakFast.setTitle(meal.get(27));
        satBreakFast.setMealType(MealType.DINNER);
        satBreakFast.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.EPOCH.getMonthValue(), LocalDate.now().getDayOfMonth() + 2));

/*

        days.get("sat").add(meal.get(9));
        days.get("sat").add(meal.get(15));
        days.get("sat").add(meal.get(16));
        days.get("sat").add(meal.get(26));
        days.get("sat").add(meal.get(27));

        days.get("sun").add(meal.get(10));
        days.get("sun").add(meal.get(17));
        days.get("sun").add(meal.get(18));
        days.get("sun").add(meal.get(28));
        days.get("sun").add(meal.get(29));

        days.get("mon").add(meal.get(11));
        days.get("mon").add(meal.get(19));
        days.get("mon").add(meal.get(20));
        days.get("mon").add(meal.get(30));
        days.get("mon").add(meal.get(31));

        days.get("tu").add(meal.get(12));
        days.get("tu").add(meal.get(21));
        days.get("tu").add(meal.get(22));
        days.get("tu").add(meal.get(32));
        days.get("tu").add(meal.get(33));

        days.get("wed").add(meal.get(13));
        days.get("wed").add(meal.get(23));
        days.get("wed").add(meal.get(24));
        days.get("wed").add(meal.get(34));
        days.get("wed").add(meal.get(35));
*/


//        System.out.println(days);


//        meals.getText().trim();


        // Close the browser window
//        driver.quit();
    }
}
