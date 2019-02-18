package tests;

import entity.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainPage;
import pages.ProfilePage;
import pages.RegistrationPage;
import utils.Randomizer;

public class DatingSiteTest {
    private static WebDriver driver;

    @BeforeClass
    @Parameters({"os", "browser", "url"})
    public void setUp(String os, String browser, String baseUrl) {
        SetupTestDriver setupTestDriver = new SetupTestDriver(os, browser, baseUrl);
        driver = setupTestDriver.getDriver();
    }

    @DataProvider(name = "users")
    public Object[][] createUser() {
        return new Object[][] {
                { new User("Testfemale", Randomizer.generateEmail(), "f", "19") }
        };
    }

    @Test(dataProvider = "users")
    public void registration(User user) {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.step("Кто тебе нравится?", "Девушки", "")
                .step("Выбери цвет волос", "Темные", user.getGender())
                .step("Выбери цвет глаз", "Светлые", user.getGender())
                .step("Выбери фигуру", "Стройная", "");
        RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        registrationPage.registration(user);
        registrationPage.handleAlert();
        Assert.assertEquals(PageFactory.initElements(driver, ProfilePage.class).getUserName(), user.getName());
    }

    @Test(dependsOnMethods = "registration")
    public void uploadPhoto() {
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        profilePage.loadImage();
        Assert.assertTrue(profilePage.isPhotoLoaded());
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}