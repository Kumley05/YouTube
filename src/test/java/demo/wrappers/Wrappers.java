package demo.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */
    ChromeDriver driver;
    public Wrappers(ChromeDriver driver){
        this.driver = driver;
    }


    public void navigateURL(String URL)throws InterruptedException{
        System.out.println("Navigate to URL: " +URL);
        driver.get(URL);
        Thread.sleep(2000);
        System.out.println("success!");
        
    }
    
}
