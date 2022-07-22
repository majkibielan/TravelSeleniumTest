import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SignUpTest {

    @Test
    public void signUp() {
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://www.kurs-selenium.pl/demo/");

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

        String firstName = "Michal";
        String lastName = "Testowy";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "majki" + randomNumber + "majki@gmail.com";

        driver.findElement(By.name("firstname")).sendKeys(firstName);
        driver.findElement(By.name("lastname")).sendKeys(lastName);
        driver.findElement(By.name("phone")).sendKeys("666333222");
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys("testpassword123");
        driver.findElement(By.name("confirmpassword")).sendKeys("testpassword123");
        driver.findElement(By.xpath("//button[text()=' Sign Up']")).click();

        WebElement header = driver.findElement(By.xpath("//h3[@class='RTL']"));


        Assert.assertTrue(header.getText().contains(firstName));
        Assert.assertEquals(header.getText(), "Hi, Michal Testowy");
    }
}
