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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    @Value(value = "${application.web.self.address}")
    private String selfAddress;
    @Value(value = "${application.web.self.username}")
    private String username;
    @Value(value = "${application.web.self.password}")
    private String password;

    public FoodServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<Food> getTodayFood(Integer differentDay) {
        return foodRepository.getFoodByDate(LocalDate.now().plusDays(differentDay));
    }

    @Scheduled(cron = "0 0 22 * * TUE")
//    @Scheduled(cron = "30 * * * * *")
    @Override
    public void getAllFoodsFromWeb() throws InterruptedException, MalformedURLException {
        crawlWeb();
    }

    @Override
    public List<Food> getThisWeekFoods(Integer from) {
        LocalDate date = LocalDate.now();
        return foodRepository.findAllByDateBetween(LocalDate.of(date.getYear(), date.getMonthValue(), from), LocalDate.of(date.getYear(), date.getMonthValue(), from + 7));
    }

    private void crawlWeb() throws MalformedURLException, InterruptedException {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "/home/pirooz/myFiles/chromedriver_linux64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.get(selfAddress);

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--window-size=1920,1080");
//        options.addArguments("--remote-allow-origins=*");
//        options.setCapability("browserless.token", "qM5B2AhDuxpRCkKVi96");


//        WebDriver driver = new RemoteWebDriver(new URL("https://student-service-chrome.iran.liara.run/webdriver"), options);
        driver.get("https://self.shahroodut.ac.ir/identity/login");

        WebElement returnPageButton = driver.findElement(By.linkText("بازگشت به صفحه اصلی"));
        if (returnPageButton != null) {
            returnPageButton.click();
        }


        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        // Submit the form
        WebElement submitButton = driver.findElement(By.className("btn"));
        submitButton.click();

        WebElement firstLink = driver.findElement(By.xpath("//span[contains(text(), 'رزرو غذا')]"));
        firstLink.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement submenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.treeview ul.treeview-menu")));
        WebElement secondLink = submenu.findElement(By.cssSelector("li.child a[href='#!/Reservation'][my-refresh=''] span"));
        secondLink.click();

        Thread.sleep(7000);
//        WebElement btnNextWeek = driver.findElement(By.xpath("//button[contains(@ng-click, 'browseWeek(startdate,7)')]"));
//        btnNextWeek.click();

        WebElement btnWeekPlan = driver.findElement(By.id("shopping"));
        btnWeekPlan.click();
        WebElement meals = driver.findElement(By.tagName("table"));
        List<WebElement> tdElements = meals.findElements(By.tagName("td"));
        List<String> thisWeekMeals = new ArrayList<>();
        LocalDate localDate = LocalDate.now().plusDays(4);
        for (int i = 0, j = 0; i < tdElements.size(); i++, j++) {
            thisWeekMeals.add(tdElements.get(i).getText());
        }

        for (int i = 9, j = 0; i < tdElements.size(); i++, j++) {
            MealType mealType;
            if (i < 16) {
                mealType = MealType.BREAKFAST;
            } else if (i > 16 && i < 24) {
                mealType = MealType.LUNCH;
            } else {
                mealType = MealType.DINNER;
            }
            if (i == 16 || i == 24) {
                localDate = LocalDate.now().plusDays(4);
                j = -1;
                continue;
            }
            if (tdElements.get(i).getText().length() < 7 && !tdElements.get(i).getText().isEmpty()) {
                j--;
                continue;
            }
            if (thisWeekMeals.get(i).split("\n").length > 1) {
                Food food = new Food(thisWeekMeals.get(i).split("\n")[0], mealType);
                food.setDate(localDate.plusDays(j));
                Food food2 = new Food(thisWeekMeals.get(i).split("\n")[1], mealType);
                food2.setDate(localDate.plusDays(j));
                if (food.getTitle().length() > 1) {
                    foodRepository.save(food);
                    foodRepository.save(food2);
                }
            } else {
                Food food = new Food(thisWeekMeals.get(i).split("\n")[0], MealType.BREAKFAST);
                food.setDate(localDate.plusDays(j));
                if (food.getTitle().length() > 1) {
                    foodRepository.save(food);
                }
            }
        }
        driver.quit();
    }
}
