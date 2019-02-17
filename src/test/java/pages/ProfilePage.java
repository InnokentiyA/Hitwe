package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.RandomImage;

public class ProfilePage {

    private static WebDriver driver;
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

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
        Wait wait = new WebDriverWait(driver, 25).ignoring(NoSuchElementException.class);
        addPhoto.click();
        //todo
        try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        uploadImg.sendKeys(RandomImage.generate(540, 320));
        wait.until(ExpectedConditions.elementToBeClickable(goButton));
        wait.until(ExpectedConditions.invisibilityOf(loadIndicator));
        goButton.click();
    }

    public boolean isPhotoLoaded() {
        if (!isElementPresent(By.xpath("//div[@class = 'prof-ava']/div[@class = 'avatar-placeholder no-photo']"))) {
            return true;
        } else return false;
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public String getUserName() {
        Wait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(userName));
        return userName.getText();
    }
}
