package pages;

import com.google.common.base.Predicate;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/div[1]/div/div[2]/div[1]/div[1]/div[2]")
    WebElement addPhoto;

    @FindBy(xpath = "//*[@id='photo' and @type = 'file']")
    WebElement uploadImg;

    @FindBy(xpath = "//*[@id=\"Dialog\"]/div/div/div[1]/button")
    WebElement goButton;

    public void loadImage() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addPhoto.click();

//        WebDriverWait wait = new WebDriverWait(driver, 60);
//        WebElement uploadImg = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='photo' and @type = 'file']")));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        uploadImg.sendKeys("C:\\Users\\Inna\\IdeaProjects\\tests\\output.png");

        goButton.click();
    }

    public void waitForElementAttributeEqualsString(WebElement element, String attribute, String expectedString,
                                                    WebDriver driver, int specifiedTimeout) {
        WebDriverWait wait = new WebDriverWait(driver, specifiedTimeout);
        ExpectedCondition<Boolean> elementAttributeEqualsString = arg0 -> element.getAttribute(attribute).equals(expectedString);
        wait.until(elementAttributeEqualsString);
    }
}
