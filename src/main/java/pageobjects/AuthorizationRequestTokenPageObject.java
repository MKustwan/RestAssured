package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthorizationRequestTokenPageObject {
    private @FindBy(xpath = "//div[@id='onetrust-close-btn-container']/button")
    WebElement closeBtnContainer;
    private @FindBy(xpath = "//div[@class='button']/a[@href='/login']")
    WebElement login;
    private @FindBy(id = "username")
    WebElement usernameInput;
    private @FindBy(id = "password")
    WebElement passwordInput;
    private @FindBy(id = "login_button")
    WebElement loginButton;
    private @FindBy(xpath = "//input[@name='submit']")
    WebElement inputSubmit;

    public AuthorizationRequestTokenPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void authorizationRequestToken(String username, String password) {
        closeBtnContainer.click();
        login.click();
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        inputSubmit.click();
    }
}
