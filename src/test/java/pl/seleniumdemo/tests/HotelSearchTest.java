package pl.seleniumdemo.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.ResultsPage;
import pl.seleniumdemo.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class HotelSearchTest extends BaseTest {

    @Test
    public void searchHotelTest() throws IOException {
        ExtentTest test = extentReports.createTest("Search Hotel Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setCity("Dubai");
        test.log(Status.PASS, "Setting city done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setDates("25/07/2022", "30/07/2022");
        test.log(Status.PASS, "Setting dates done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(1, 2);
        test.log(Status.PASS, "Setting travellers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done", SeleniumHelper.getScreenshot(driver));

        ResultsPage resultsPage = new ResultsPage(driver);
        List<String> hotelNames = resultsPage.getHotelNames();

        Assert.assertEquals(hotelNames.get(0), "Jumeirah Beach Hotel");
        Assert.assertEquals(hotelNames.get(1), "Oasis Beach Tower");
        Assert.assertEquals(hotelNames.get(2), "Rose Rayhaan Rotana");
        Assert.assertEquals(hotelNames.get(3), "Hyatt Regency Perth");
        test.log(Status.PASS, "Assertions passed!", SeleniumHelper.getScreenshot(driver));
    }

    @Test
    public void searchHotelWithoutCityTest() throws IOException {
        ExtentTest test = extentReports.createTest("Search Hotel Without Names Test");
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.setDates("25/07/2022", "30/07/2022");
        test.log(Status.PASS, "Setting dates done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.setTravellers(0, 1);
        test.log(Status.PASS, "Setting travellers done", SeleniumHelper.getScreenshot(driver));
        hotelSearchPage.performSearch();
        test.log(Status.PASS, "Performing search done", SeleniumHelper.getScreenshot(driver));

        ResultsPage resultsPage = new ResultsPage(driver);

        Assert.assertEquals(resultsPage.getHeadingText(), "No Results Found");
        test.log(Status.PASS, "Assertions passed!", SeleniumHelper.getScreenshot(driver));
    }
}
