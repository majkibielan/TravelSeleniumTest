import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpHomeWorkTest extends BaseTest {

    @Test
    public void signUpEmptyFormTest() {
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();
        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']/p"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertEquals(errors.get(0), "The Email field is required.");
        Assert.assertEquals(errors.get(1), "The Password field is required.");
        Assert.assertEquals(errors.get(2), "The Password field is required.");
        Assert.assertEquals(errors.get(3), "The First name field is required.");
        Assert.assertEquals(errors.get(4), "The Last Name field is required.");
    }

    @Test
    public void signUpInvalidEmailTest() {
        driver.findElements(By.xpath("//li[@id='li_myaccount']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        driver.findElements(By.xpath("//a[text()='  Sign Up']"))
                .stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);

        // jesli test uruchamiany jest z http://www.kurs-selenium.pl/demo/register/ to wyrzuca StaleElementReferenceException przy assercji na errorze

        String firstName = "Michal";
        String lastName = "Tester";
        String phoneNumber = "111222333";
        String email = "majki112";
        String password = "zxczxc";

        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys(phoneNumber);
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmpassword")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        List<String> errors = driver.findElements(By.xpath("//div[@class='alert alert-danger']//p"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        Assert.assertTrue(errors.contains("The Email field must contain a valid email address."));
    }

}
