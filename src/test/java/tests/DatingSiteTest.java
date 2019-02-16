package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ProfilePage;
import pages.RegistrationPage;
import utils.RandomImage;

public class DatingSiteTest {
    private static WebDriver driver;

    @BeforeClass
//    @Parameters({"os", "browser", "url"})
    public void setUp() {
        SetupTestDriver setupTestDriver = new SetupTestDriver("windows", "chrome", "https://hitwe.com/landing/inter2?p=15276");
        driver = setupTestDriver.getDriver();
    }

    @Test
    public void googleTitleTest() {
        Assert.assertTrue(driver.getTitle().contentEquals("Hitwe – сайт встреч! Общение без ограничений"));
    }

    @Test
    public void googleUrlTest() {
        Assert.assertTrue(driver.getCurrentUrl().contains("https://hitwe.com/landing/inter2?p=15276"));
    }

    @Test
    public void registration(String question, String answer, String attr) {
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.step("Кто тебе нравится?", "Девушки", "")
                .step("Выбери цвет волос", "Темные", "f")
                .step("Выбери цвет глаз", "Светлые", "f")
                .step("Выбери фигуру", "Стройная", "");
        RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
        registrationPage.registration("Test", "test1602@testmail.com", "m", "23");
        Assert.assertTrue(driver.getTitle().contentEquals("Я"));
    }

    @Test
    public void login() {
        String password = "VHKN2d3t2pfjFXq";
        String login = "test1602manual@testmail.com";
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        mainPage.login(login, password);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[3]/div/a/div/div[1]/div")).click();
    }

    @Test(dependsOnMethods = "login")
    public void uploadPhoto() {
        ProfilePage profilePage = PageFactory.initElements(driver, ProfilePage.class);
        RandomImage.generate();
        profilePage.loadImage();
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}