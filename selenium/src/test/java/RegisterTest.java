import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        driver = new FirefoxDriver();
    }


    @AfterClass
    public void tearDown() {
        driver.close();
    }

    @Test(groups = "register")
    public void register() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://linden-hof.ch/my-account/");
        WebElement emailInput = driver.findElement(By.id("reg_email"));
        WebElement passwordInput = driver.findElement(By.id("reg_password"));
        WebElement submitButton = driver.findElement(By.name("register"));
        new Actions(driver)
                .sendKeys(emailInput, LoginConstants.EMAIL)
                .sendKeys(passwordInput, LoginConstants.PASSWORD)
                .click(submitButton)
                .pause(Duration.ofSeconds(1))
                .perform();

        WebElement logoutLink = driver.findElement(By.cssSelector("a[href*='customer-logout']"));
        new Actions(driver).click(logoutLink);
        Thread.sleep(3000);
    }


}
