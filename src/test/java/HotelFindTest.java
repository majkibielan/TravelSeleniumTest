import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HotelFindTest extends BaseTest {

    @Test
    public void findHotelTest() {
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

        Assert.assertEquals(hotelNames.get(0),"Jumeirah Beach Hotel",  "Jumeirah Beach Hotel nie znaleziony");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower", "Oasis Beach Tower nie znaleziony");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana", "Rose Rayhaan Rotana nie znaleziony");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth", "Hyatt Regency Perth nie znaleziony");
    }

    @Test
    public void findHotelWithoutCityTest() {
        driver.findElement(By.name("checkin")).sendKeys("25/07/2022");
        driver.findElement(By.name("checkout")).sendKeys("30/07/2022");
        driver.findElement(By.id("travellersInput")).click();
        driver.findElement(By.id("childPlusBtn")).click();
        driver.findElement(By.xpath("//button[text()=' Search']")).click();

        WebElement noResultHeader = driver.findElement(By.xpath("//div[@class='itemscontainer']//h2"));

        Assert.assertTrue(noResultHeader.isDisplayed());
        Assert.assertEquals(noResultHeader.getText(), "No Results Found");
    }
}
