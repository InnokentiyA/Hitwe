package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Randomizer;

public class ProfilePage {

    private WebDriver driver;
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    private final String noPhoto = "//div[@class = 'prof-ava']/div[@class = 'avatar-placeholder no-photo']";

    @FindBy(xpath = "//*[@id='content']//div[@class = 'prof_add_avatar']")
    private WebElement addPhoto;

    @FindBy(xpath = "//*[@id='photo' and @type = 'file']")
    private WebElement uploadImg;

    @FindBy(xpath = "//a[text()='Поехали!']")
    private WebElement goButton;

    @FindBy(xpath = "//div[@class = 'prof-name']")
    private WebElement userName;

    @FindBy(xpath = "//div[@class= 'loading_indicator']")
    private WebElement loadIndicator;

    public void loadImage() {
        addPhoto.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        uploadImg.sendKeys(Randomizer.generateImage(540, 320));
        wait.until(ExpectedConditions.invisibilityOf(loadIndicator));
        goButton.click();
    }

    public boolean isPhotoLoaded() {
        return !isElementPresent(By.xpath(noPhoto));
    }

    public String getUserName() {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(userName));
        return userName.getText();
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
