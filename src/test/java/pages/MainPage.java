package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    String buttonXpath = "//h2[text() = '%s']/following-sibling::*//a[text()='%s' and contains(@data-next, '%s')]";

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public MainPage step(String title, String option, String gender) {
        driver.findElement(By.xpath(String.format(buttonXpath, title, option, gender))).click();
        return this;
    }
}