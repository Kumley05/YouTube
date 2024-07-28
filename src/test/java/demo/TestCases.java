package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.utils.ExcelDataProvider;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases extends ExcelDataProvider { // Lets us read the data
        ChromeDriver driver;
        Wrappers wrappers;

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
                wrappers = new Wrappers(driver);

                driver.manage().window().maximize();
        }

        @Test(priority = 0, description = "Go to YouTube.com and Assert you are on the correct URL. Click on\"About\" at the bottom of the sidebar, and print the message on the screen.")

        public void testCase01() throws InterruptedException {
        System.out.println("Start Testcase 01");
        wrappers.navigateURL("https://www.youtube.com/");
        new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.urlToBe("https://www.youtube.com/"));
        String actualURl = driver.getCurrentUrl();
        String expectedURL = "https://www.youtube.com/";
        Assert.assertEquals(actualURl, expectedURL);
        driver.findElement(By.id("guide-button")).click();
        //scroll the pagedown
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        //
        WebElement button = driver.findElement(By.xpath("//a[@href='https://www.youtube.com/about/' and contains(text(),'About')]"));
        actions.moveToElement(button).perform();
        button.click();
        Thread.sleep(3000);
        System.out.println("Successfully entered About session");

        String text = driver.findElement(By.id("content")).getText();
        System.out.println("About text: "+text);

        System.out.println("End Testcase 01");
        }

        @Test(priority = 1,description="Go to the \"Films\" tab and in the “TopSelling”section,scroll to the extreme right.Apply a Soft Assert on whether the movie is marked“A”for Mature or not.Apply a Soft assert on whether the movie is either“Comedy”or“Animation”.")

        public void testCase02() throws InterruptedException{
        System.out.println("Start Testcase 02");
        SoftAssert softAssert = new SoftAssert();
        wrappers.navigateURL("https://www.youtube.com/");
        new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.urlToBe("https://www.youtube.com/"));
        driver.findElement(By.id("guide-button")).click();
        //scroll the pagedown
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        //
        WebElement button = driver.findElement(By.xpath("//*[@class='title style-scope ytd-guide-entry-renderer' and contains(text(),'Movies')]"));
        actions.moveToElement(button).perform();
        button.click();
        Thread.sleep(3000);
        System.out.println("Successfully entered Film session");

        List<WebElement> movieMarks =
        driver.findElements(By.xpath("//div[@class='badge badge-style-type-simple style-scope ytd-badge-supported-renderer style-scope ytd-badge-supported-renderer']"));
        for(WebElement movieMark : movieMarks){
        String movieMarkText = movieMark.getAttribute("aria-label");
        System.out.println(movieMarkText);
        //softAssert.assertTrue(movieMarkText.contains("A"),"Movie is not contains
        
        }
        List<WebElement> topSellings =
        driver.findElements(By.xpath("//span[@class='grid-movie-renderer-metadata style-scope ytd-grid-movie-renderer']"));
        for(WebElement topSelling : topSellings){
        System.out.println(topSelling.getText());
        //softAssert.assertTrue(topSelling.getText().contains("Comedy")||topSelling.getText().contains("Animation"),"Movie
        //is neither 'Comdey' nor 'Animation'");
        }

        softAssert.assertAll();

        }

        @Test(priority = 2,description="Go to the \"Music\" tab and in the 1stsection,scroll to the extreme right.Print the name of the playlist. Soft Assert on whether the number of tracks listed is less than or equal to 50.")

        public void testCase03() throws InterruptedException{
        System.out.println("Start Testcase 03");
        SoftAssert softAssert = new SoftAssert();
        wrappers.navigateURL("https://www.youtube.com/");
        new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.urlToBe("https://www.youtube.com/"));
        driver.findElement(By.id("guide-button")).click();
        //scroll the pagedown
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        //Sroll and find the element
        WebElement button = driver.findElement(By.xpath("//*[@class='title style-scope ytd-guide-entry-renderer' and text()='Music']"));
        actions.moveToElement(button).perform();
        button.click();
        Thread.sleep(3000);
        System.out.println("Successfully entered into Music Session");
        List<WebElement> tracksLists =
        driver.findElements(By.id("video-count-text"));
        actions.moveToElement(tracksLists.get(tracksLists.size()-1)).perform();

        WebElement playListElement =
        driver.findElement(By.xpath("//*[@id='video-count-text']/preceding::h3[1]"));
        String playlistName = playListElement.getText();
        System.out.println("PlayList Name: " +playlistName);

        WebElement numberOfTrackList = driver.findElement(By.id("video-count-text"));
        int numberOfTracks = Integer.parseInt(numberOfTrackList.getText().split("")[0]);
        softAssert.assertTrue(numberOfTracks <= 50, "Number of tracks is greater than 50");
        softAssert.assertAll();

        }

        @Test(priority = 3
        ,description="Go to the \"News\" tab and print the titleand body of the 1 st 3“ Latest News Posts” along with the sum of the number of likes on all 3 of them.No likes given means 0.")

        public void testCase04() throws InterruptedException{
        System.out.println("Start Testcase 04");
        SoftAssert softAssert = new SoftAssert();
        wrappers.navigateURL("https://www.youtube.com/");
        new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.urlToBe("https://www.youtube.com/"));
        driver.findElement(By.id("guide-button")).click();
        //scroll the pagedown
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        //Sroll and find the element
        WebElement button = driver.findElement(By.xpath("//*[@class='title style-scope ytd-guide-entry-renderer' and text()='News']"));
        actions.moveToElement(button).perform();
        button.click();
        Thread.sleep(3000);
        System.out.println("Successfully entered into News Session");

        List <WebElement> postTiltes =
        driver.findElements(By.xpath("//span[@id='title' and text()='Latest news posts']/ancestor::div[4]/following::div/div[@id='header']"));
        List<News> news = new ArrayList<>();

        for(WebElement newsDetail : postTiltes){
        String title = newsDetail.getText();
        WebElement newsBody = newsDetail.findElement(By.xpath("//span[@id='title' and text()='Latest news posts']/ancestor::div[4]/following::div/div[@id='header']/following::div[1]"));
        String body = newsBody.getText();
        WebElement newsLiks = newsDetail.findElement(By.xpath("//span[@id='title' and text()='Latest news posts']/ancestor::div[4]/following::div/div[@id='header']/following::div[12]/span[1]"));
        String likes = newsLiks.getText();
        news.add(new News(title, body, likes));
        }

        double totalLikes = 0;
        for(int i =0; i < Math.min(news.size(),3); i++){
        News latestNews = news.get(i);

        System.out.println("Post Title: "+latestNews.title);
        System.out.println("Post Body: " +latestNews.body);
        System.out.println("Post Like: "+latestNews.likes);
        System.out.println("*-------------*------------*");
        //totalLikes += latestNews.likes;
        }
        System.out.println("Total Likes: "+ totalLikes);

        softAssert.assertAll();
        }

        private static class News {
                String title;
                String body;
                String likes;

                News(String title, String body, String likes) {
                        this.title = title;
                        this.body = body;
                        this.likes = likes;
                }
        }

        @Test(priority = 4, description = "Search for each of the items given in the stubs: src/test/resources/data.xlsx, and keep scrolling till the sum of each video’s views reach 10 Cr.")
        public void testCase05() throws InterruptedException, IOException {
                System.out.println("Start Testcase 05");
                wrappers.navigateURL("https://www.youtube.com/");
                new WebDriverWait(driver, Duration.ofSeconds(10))
                                .until(ExpectedConditions.urlToBe("https://www.youtube.com/"));

                FileInputStream file = new FileInputStream("src/test/resources/data.xlsx");
                Workbook workbook = new XSSFWorkbook(file);

                for (Row row : workbook.getSheetAt(0)) {
                        String search = row.getCell(0).getStringCellValue();
                        driver.findElement(By.xpath("//input[@id='search']")).sendKeys(search, Keys.ENTER);
                        Thread.sleep(3000);
                }

                double views = 0;
                while (views < 100000000) {
                        List<WebElement> videos = driver.findElements(
                                        By.xpath("//div[@id='dismissible']//div[@id='metadata-line']//span[1]"));
                        for (WebElement videoView : videos) {
                                String viewText = videoView.getText().replace("views", "").replace("K", "000")
                                                .replace("M", "000000").replace("No","0").trim();
                                if (!viewText.isEmpty()) {
                                        double view = Double.parseDouble(viewText);
                                        views += view;
                                        if (views >= 100000000) {
                                                break;

                                        }
                                }
                        }
                        Actions actions = new Actions(driver);
                        actions.sendKeys(Keys.PAGE_DOWN).perform();
                        Thread.sleep(3000);

                }
                System.out.println(": " + views);

                driver.findElement(By.xpath("//input[@id='search']")).clear();
                Thread.sleep(3000);

                workbook.close();
                file.close();

                System.out.println("Successfully Done");
        }

        // private static double parseViews(String viewsText) {
        //         if (viewsText.endsWith("K")) {
        //                 return Double.parseDouble(viewsText.replace("K", "")) * 1_000;
        //         } else if (viewsText.endsWith("M")) {
        //                 return Double.parseDouble(viewsText.replace("M", "")) * 1_000_000;
        //         } else {
        //                 return Double.parseDouble(viewsText.replaceAll("[^0-9]", ""));
        //         }
        // }

        @AfterTest
        public void endTest() {
                driver.close();
                driver.quit();

        }
}