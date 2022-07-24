package pl.seleniumdemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.IOException;

public class DriverFactory {

    public static WebDriver getDriver() throws IOException {
        String name = PropertiesLoader.loadProperty("browser.name");
        if (name.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else {
            WebDriverManager.edgedriver().setup();
            return new EdgeDriver();
        }
    }
}
