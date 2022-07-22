package pl.seleniumdemo.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;

import java.util.List;
import java.util.stream.Collectors;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() {

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        hotelSearchPage.setDates("25/07/2022", "30/07/2022");
        hotelSearchPage.setTravellers();
        hotelSearchPage.performSearch();

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
