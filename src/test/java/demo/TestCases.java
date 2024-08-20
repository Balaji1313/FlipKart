package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import java.text.NumberFormat;
import java.util.Locale;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    protected ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest() {

        // driver.close();
        // driver.quit();

    }

     @Test
    public void testCase01() throws InterruptedException {

        driver.get("https://www.flipkart.com/");

        WebElement searchBar = driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"));
        Wrappers.searchProduct(searchBar, "Washing Machine");

        Thread.sleep(2000);
        WebElement popularityTab = driver.findElement(By.xpath("//div[text()='Popularity']"));
        popularityTab.click();
        Thread.sleep(2000);

        List<WebElement> productList = driver
                .findElements(By.xpath("//div[@class='yKfJKb row']/div/div[@class='_5OesEi']/span/div"));
        int count = 0;

        for (int i = 0; i < productList.size(); i++) {
            String s = productList.get(i).getText();

            float n = Float.parseFloat(s);

            if (n <= 4.0) {
                WebElement productTitle = driver.findElement(
                        By.xpath("(//div[@class='yKfJKb row']/div/div[@class='_5OesEi']/span/div)[" + (i + 1)
                                + "]//parent::span/parent::div/preceding-sibling::div[@class='KzDlHZ']"));
                System.out.println(productTitle.getText() + "-----" + n);
                count++;
            }

        }
        System.out.println("count of items : " + count);

    }

    @Test
    public void testCase02() throws InterruptedException {

        driver.get("https://www.flipkart.com/");
        Thread.sleep(2000);
        WebElement searchBar = driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"));
        Wrappers.searchProduct(searchBar, "iPhone");
        Thread.sleep(2000);
        List<WebElement> productList = driver
                .findElements(By.xpath("//div[@class='yKfJKb row']/div/div/div/div[@class='UkUFwK']"));
        for (int i = 0; i < productList.size(); i++) {
            String s = productList.get(i).getText();
            String numberString = s.replaceAll("\\D+", "");
            int n = Integer.parseInt(numberString);
            if (17 < n) {
                // System.out.println(s);
                Thread.sleep(2000);
                WebElement tilesOfPercentage = driver.findElement(By
                        .xpath("(//div[@class='yKfJKb row']/div/div/div/div[@class='UkUFwK'])[" + (i + 1)
                                + "]//parent::div//parent::div//parent::div//preceding-sibling::div/div[@class='KzDlHZ']"));
                System.out.println(tilesOfPercentage.getText() + "-----" + s);
            }
        }

    }

    @Test
    public void testCase03() throws InterruptedException {
        // Wrappers.launchUrl();
        driver.get("https://www.flipkart.com/");
        WebElement searchBar = driver.findElement(By.xpath("//input[@title='Search for Products, Brands and More']"));
        // searchBar.sendKeys("Coffee Mug", Keys.ENTER);
        Wrappers.searchProduct(searchBar, "Coffee Mug");
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Coffee Mug']")));

        WebElement fourAboveRating = driver.findElement(By.xpath("(//div[@class='_6i1qKy'])[2]"));
        fourAboveRating.click();
        Thread.sleep(2000);

        List<WebElement> productListReview = driver
                .findElements(By.xpath("//div[@class='slAVV4']/div[@class='_5OesEi afFzxY']/span[@class='Wphh3N']"));
        int higestRating = 0;

        for (WebElement webElement : productListReview) {
            String str = webElement.getText();
            String s = str.replaceAll("[^\\d]", "");
            int m = Integer.parseInt(s);
            if (higestRating < m) {
                higestRating = m;
            }
        }
        System.out.println("Highest Rating is : " + higestRating);

        int[] array = new int[productListReview.size()];
        for (int i = 0; i < productListReview.size(); i++) {
            String text = productListReview.get(i).getText();
            String s = text.replaceAll("[^\\d]", "");
            array[i] = Integer.parseInt(s);
        }
        Arrays.sort(array);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        // for (int i = array.length - 5; i < array.length; i++) {
        // System.out.print(array[i] + " ");
        // for (int j = 0; j < productListReview.size(); j++) {
        // String text1 = productListReview.get(j).getText();
        // String s = text1.replaceAll("[^\\d]", "");
        // int x = Integer.parseInt(s);
        // if (array[i] == x) {
        // WebElement titles = driver.findElement(
        // By.xpath("(//div[@class='slAVV4']/div[@class='_5OesEi
        // afFzxY']/span[@class='Wphh3N'])["
        // + (j + 1) + "]//parent::div//preceding-sibling::a[1]"));

        // WebElement imageUrl = driver.findElement(
        // By.xpath("(//div[@class='slAVV4']/div[@class='_5OesEi
        // afFzxY']/span[@class='Wphh3N'])["
        // + (j + 1)
        // +
        // "]//parent::div//preceding-sibling::a[1]//parent::div/a/div/div/div/img[@src]"));



        ///----------------------------updated code------------------------------------///////

        for (int i = array.length - 5; i < array.length; i++) {
            // System.out.print(array[i] + " ");
            int y = array[i];
            // Step 1: Format the number with a comma
            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
            String formattedNumber = numberFormat.format(y);

            // Step 2: Add parentheses around the formatted number
            String finalString = "(" + formattedNumber + ")";

            System.out.println(finalString);
            WebElement titles = driver.findElement(
                    By.xpath("(//div[@class='slAVV4']/div[@class='_5OesEi afFzxY']/span[text()='" + finalString
                            + "'])//parent::div//preceding-sibling::a[1]"));

            WebElement imageUrl = driver.findElement(
                    By.xpath("(//div[@class='slAVV4']/div[@class='_5OesEi afFzxY']/span[text()='" + finalString
                            + "'])//parent::div//preceding-sibling::a[1]//parent::div/a/div/div/div/img[@src]"));

            System.out.println(titles.getText());
            System.out.println(imageUrl.getAttribute("src"));
        }
    }

}