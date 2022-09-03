import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class LoginTest {

    public void run() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        WebDriver driver;
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
        WebElement textBox = driver.findElement(By.id("my-text-id"));
        WebElement passwordBox = driver.findElement(By.name("my-password"));
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        new Actions(driver)
                .moveToElement(textBox)
                .sendKeys("Username")
                .moveToElement(passwordBox)
                .sendKeys("Password123")
                .moveToElement(submitButton)
                .click()
                .pause(Duration.ofSeconds(1))
                .perform();
        WebElement message = driver.findElement(By.id("message"));
        if (message.getText().equals("Received!")) {
            System.out.println("Login success");
        } else {
            System.out.println("login failed");
        }
    }
}
