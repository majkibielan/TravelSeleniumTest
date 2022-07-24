package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.SignUpPage;

public class SignUpHomeWorkTest extends BaseTest {

    @Test
    public void signUpEmptyFormTest() {
        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUp();
        signUpPage.getErrorsList();

        Assert.assertEquals(signUpPage.getErrorsList().get(0), "The Email field is required.");
        Assert.assertEquals(signUpPage.getErrorsList().get(1), "The Password field is required.");
        Assert.assertEquals(signUpPage.getErrorsList().get(2), "The Password field is required.");
        Assert.assertEquals(signUpPage.getErrorsList().get(3), "The First name field is required.");
        Assert.assertEquals(signUpPage.getErrorsList().get(4), "The Last Name field is required.");
    }

    @Test
    public void signUpInvalidEmailTest() {
        String firstName = "Michal";
        String lastName = "Testowy";
        String email = "majkitest.pl";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.setFirstName(firstName);
        signUpPage.setLastName(lastName);
        signUpPage.setPhone("622111111");
        signUpPage.setEmail(email);
        signUpPage.setPassword("testpassword");
        signUpPage.setConfirmPassword("testpassword");
        signUpPage.signUp();

        signUpPage.getErrorsList();

        // jesli test uruchamiany jest z http://www.kurs-selenium.pl/demo/register/ to wyrzuca StaleElementReferenceException przy assercji na errorze

        Assert.assertTrue(signUpPage.getErrorsList().contains("The Email field must contain a valid email address."));
    }

}
