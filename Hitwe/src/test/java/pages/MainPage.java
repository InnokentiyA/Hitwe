package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    String buttonXpath = "//h2[text() = '%s']/following-sibling::*//a[text()='%s' and contains(@data-next, '%s')]";

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage step(String title, String option, String sex) {
        driver.findElement(By.xpath(String.format(buttonXpath, title, option, sex))).click();
        return this;
    }

    public void login(String login, String password) {
        driver.findElement(By.xpath("//a[text()='Войти']")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//input[@name='email' and @type = 'email']")).sendKeys(login);
        driver.findElement(By.xpath("//input[@name='password' and @type = 'password' and @class = 'txt']")).sendKeys(password);
        driver.findElement(By.id("sgnn-submit")).submit();
        driver.findElement(By.className("interstial-close")).click();
    }
}