package com.example.studentserviceapplication.service.impl;

import com.example.studentserviceapplication.domain.Food;
import com.example.studentserviceapplication.domain.enumuration.MealType;
import com.example.studentserviceapplication.repository.FoodRepository;
import com.example.studentserviceapplication.service.FoodService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<Food> getTodayFood() {
        return foodRepository.getFoodByDate(LocalDate.now().plusDays(0));
    }

    @Scheduled(cron = "0 0 22 * * TUE")
    @Override
    public void getAllFoodsFromWeb() throws InterruptedException {
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
        List<String> meal = new ArrayList<>(List.of(meals.getText().trim().split("\n")));


//        List<Food> foods = new ArrayList<>();

        for (int i = 9, j = 0; i <= 13; i++, j++) {
            Food breakfast = new Food();
            breakfast.setTitle(meal.get(i));
            breakfast.setMealType(MealType.BREAKFAST);
            breakfast.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth() + 2 + j));
            foodRepository.save(breakfast);
        }

        for (int i = 15, j = 0; i <= 24; i = i + 2, j++) {
            Food lunch = new Food();
            lunch.setTitle(meal.get(i));
            lunch.setMealType(MealType.LUNCH);
            lunch.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth() + 2 + j));
            Food lunch2 = new Food();
            lunch2.setTitle(meal.get(i + 1));
            lunch2.setMealType(MealType.LUNCH);
            lunch2.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth() + 2 + j));

            foodRepository.save(lunch);
            foodRepository.save(lunch2);
        }

        for (int i = 26, j = 0; i < 36; i += 2, j++) {
            Food dinner = new Food();
            dinner.setTitle(meal.get(i));
            dinner.setMealType(MealType.DINNER);
            dinner.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth() + 2 + j));

            Food dinner2 = new Food();
            dinner2.setTitle(meal.get(i + 1));
            dinner2.setMealType(MealType.DINNER);
            dinner2.setDate(LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth() + 2 + j));

            foodRepository.save(dinner);
            foodRepository.save(dinner2);
        }

        // Close the browser window
        driver.quit();
    }
}
