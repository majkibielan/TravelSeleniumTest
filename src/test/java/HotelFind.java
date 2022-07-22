import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

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
        driver.findElement(By.id("travellersInput")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("adultPlusBtn"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("childPlusBtn"))).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        List<String> hotelNames = driver.findElements(By.xpath("//h4//b"))
                                                        .stream()
                                                        .map(el -> el.getAttribute("textContent"))
                                                        .collect(Collectors.toList());

        Assert.assertEquals("Jumeirah Beach Hotel", hotelNames.get(0), "Jumeirah Beach Hotel nie znaleziony");
        Assert.assertEquals("Oasis Beach Tower", hotelNames.get(1), "Oasis Beach Tower nie znaleziony");
        Assert.assertEquals("Rose Rayhaan Rotana", hotelNames.get(2), "Rose Rayhaan Rotana nie znaleziony");
        Assert.assertEquals("Hyatt Regency Perth", hotelNames.get(3), "Hyatt Regency Perth nie znaleziony");
    }
}
