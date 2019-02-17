package tests;

import entity.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.MainPage;
import pages.ProfilePage;
import pages.RegistrationPage;

public class DatingSiteTest {
    private static WebDriver driver;

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setUp(String os, String browser) {
        SetupTestDriver setupTestDriver = new SetupTestDriver(os, browser, "https://hitwe.com/landing/inter2?p=15276");
        driver = setupTestDriver.getDriver();
    }

    @DataProvider(name = "users")
    public Object[][] createUser() {
        return new Object[][] {
                { new User("Testmale", "testmale@testmail.com", "m", "23") },
                { new User("Testfemale", "testfemale@testmail.com", "f", "19") }
        };
    }

    @Test(dataProvider = "users")
    public void registration(User user) {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.step("Кто тебе нравится?", "Девушки", "")
                .step("Выбери цвет волос", "Темные", "f")
                .step("Выбери цвет глаз", "Светлые", "f")
                .step("Выбери фигуру", "Стройная", "");
        RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        registrationPage.registration(user);
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