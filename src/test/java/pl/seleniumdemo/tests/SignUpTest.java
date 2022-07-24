package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        int randomNumber = (int) (Math.random() * 1000);
        String email = "majki" + randomNumber + "@testowy.pl";

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Michal")
                .setLastName("Testowy")
                .setPhone("622111111")
                .setEmail(email)
                .setPassword("testpassword")
                .setConfirmPassword("testpassword")
                .signUp();

        Assert.assertTrue(loggedUserPage.getHeadingText().contains("Michal"));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Michal Testowy");
    }

    @Test
    public void signUpEmptyFormTest() {
        SignUpPage signUpPage = new HotelSearchPage(driver).openSignUpForm();
        signUpPage.signUp();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(signUpPage.getErrorsList().get(0), "The Email field is required.");
        softAssert.assertEquals(signUpPage.getErrorsList().get(1), "The Password field is required.");
        softAssert.assertEquals(signUpPage.getErrorsList().get(2), "The Password field is required.");
        softAssert.assertEquals(signUpPage.getErrorsList().get(3), "The First name field is required.");
        softAssert.assertEquals(signUpPage.getErrorsList().get(4), "The Last Name field is required.");
        softAssert.assertAll();
    }

    @Test
    public void signUpInvalidEmailTest() {
        SignUpPage signUpPage = new HotelSearchPage(driver).openSignUpForm()
                .setFirstName("Michal")
                .setLastName("Testowy")
                .setPhone("622111111")
                .setEmail("majkitest.pl")
                .setPassword("testpassword")
                .setConfirmPassword("testpassword");
        signUpPage.signUp();

        // jesli test uruchamiany jest z http://www.kurs-selenium.pl/demo/register/ to wyrzuca StaleElementReferenceException przy assercji na errorze

        Assert.assertTrue(signUpPage.getErrorsList().contains("The Email field must contain a valid email address."));
    }
}
