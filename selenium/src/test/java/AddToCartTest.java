import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddToCartTest {
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


    @Test
    public void addToCart() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get("https://linden-hof.ch/produkt-kategorie/holz/");
        WebElement dismissLink = driver
                .findElement(By.xpath("//*[contains(text(),'Verstanden')]"));
        new Actions(driver)
                .click(dismissLink)
                .perform();
        WebElement cartCountZero = driver
                .findElement(By.xpath("/html/body/div[1]/div/div/div/a[2]/b"));
        Assert.assertEquals(cartCountZero.getText().trim(), "0 Artikel");
        WebElement addItemToCartButton = driver.findElement(By.cssSelector("button[value*='253']"));
        ((JavascriptExecutor) driver)
                .executeScript("document.querySelector(\"button[value*='253']\").scrollIntoView({block: 'center'})");
        new Actions((driver))
                .click(addItemToCartButton)
                .perform();
        Thread.sleep(3000);
        WebElement cartCountOne = driver
                .findElement(By.xpath("/html/body/div[1]/div/div/div/a[2]/b"));
        Assert.assertEquals(cartCountOne.getText().trim(), "1 Artikel");
    }
}
