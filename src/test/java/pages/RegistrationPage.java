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
    private WebElement nameInput;

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//select[@name='gender']")
    private WebElement genderSelect;

    @FindBy(xpath = "//select[@name='age']")
    private WebElement ageSelect;

    @FindBy(xpath = "//button[text() = 'Зарегистрироваться']")
    private WebElement regButton;

    @FindBy(className = "interstial-close")
    private WebElement alertClose;

    public void registration(User user) {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        wait.until(ExpectedConditions.elementToBeClickable(nameInput));
        nameInput.sendKeys(user.getName());
        emailInput.sendKeys(user.getEmail());
        new Select(genderSelect).selectByValue(user.getGender());
        new Select(ageSelect).selectByValue(user.getAge());
        regButton.submit();
    }

    public void handleAlert(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(alertClose));
        alertClose.click();
    }
}
