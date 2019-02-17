package pages;

import entity.User;
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

    @FindBy(className = "interstial-close")
    WebElement alertClose;

    public void registration(User user) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        wait.until(ExpectedConditions.elementToBeClickable(nameInput));
        nameInput.sendKeys(user.getName());
        emailInput.sendKeys(user.getEmail());
        new Select(genderSelect).selectByValue(user.getGender());
        new Select(ageSelect).selectByValue(user.getAge());
        regButton.submit();
        wait.until(ExpectedConditions.visibilityOf(alertClose));
        alertClose.click();
    }
}
