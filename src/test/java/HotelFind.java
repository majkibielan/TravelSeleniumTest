import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class HotelFind {

    @Test
    public void findHotel() {
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

        driver.findElement(By.xpath("//span[text()='Search by Hotel or City Name']")).click();
        driver.findElement(By.xpath("//div[@id='select2-drop']//input")).sendKeys("Dubai");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='select2-match' and text()='Dubai']"))).click();
        driver.findElement(By.name("checkin")).sendKeys("25/07/2022");
        driver.findElement(By.name("checkout")).sendKeys("30/07/2022");
    }
}
