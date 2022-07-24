package pl.seleniumdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpTest() {
        String firstName = "Michal";
        String lastName = "Testowy";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "majki" + randomNumber + "@testowy.pl";

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

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        loggedUserPage.getHeadingText();

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, " + firstName + " " + lastName);
    }

    @Test
    public void signUpTest2() {
        String firstName = "Michal";
        String lastName = "Testowy";
        int randomNumber = (int) (Math.random() * 1000);
        String email = "majki" + randomNumber + "@testowy.pl";

        HotelSearchPage hotelSearchPage = new HotelSearchPage(driver);
        hotelSearchPage.openSignUpForm();

        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.fillSignUpForm(firstName, lastName, "222333444", email, "testpassword");

        LoggedUserPage loggedUserPage = new LoggedUserPage(driver);
        loggedUserPage.getHeadingText();

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, " + firstName + " " + lastName);
    }
}
