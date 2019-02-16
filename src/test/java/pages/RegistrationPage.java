package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@name='name']")
    WebElement nameInput;

    @FindBy(xpath = "//input[@name='email']")
    WebElement emailInput;

    @FindBy(xpath = "//select[@name='gender']")
    WebElement genderSelect;

    @FindBy(xpath = "//select[@name='age']")
    WebElement ageSelect;

    @FindBy(xpath = "//button[text() = 'Зарегистрироваться']")
    WebElement regButton;

    public void registration(String name, String email, String gender, String age) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='name']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='name']")));

        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        new Select(genderSelect).selectByValue(gender);
        new Select(ageSelect).selectByValue(age);
        regButton.submit();
    }

}
