package pageObjects.Railway;
import common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class RegisterPage extends GeneralPage {
    private final By inputEmail = By.id("email");
    private final By inputPassword = By.id("password");
    private final By inputConfirmPassword = By.id("confirmPassword");
    private final By inputPID = By.id("pid");
    private final By buttonRegister = By.xpath("//input[@value='Register']");
    private final By confirmationMessage = By.xpath("//p['Registration Confirmed! You can now log in to the site.']");
    private final By errorMessage = By.xpath("//p[@class='message error']");


    public void enterEmail(String email) {
        WebElement emailField = Constant.WEBDRIVER.findElement(inputEmail);
        emailField.clear();
        emailField.sendKeys(email);
    }


    public void enterPassword(String password) {
        WebElement passwordField = Constant.WEBDRIVER.findElement(inputPassword);
        passwordField.clear();
        passwordField.sendKeys(password);
    }


    public void enterConfirmPassword(String confirmPassword) {
        WebElement confirmPasswordField = Constant.WEBDRIVER.findElement(inputConfirmPassword);
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }


    public void enterPID(String PID) {
        WebElement pidField = Constant.WEBDRIVER.findElement(inputPID);
        pidField.clear();
        pidField.sendKeys(PID);
    }


    public void clickRegisterButton() {
        WebElement registerButton = Constant.WEBDRIVER.findElement(buttonRegister);
        registerButton.click();
    }


    public String getConfirmationMessage() {
        return Constant.WEBDRIVER.findElement(confirmationMessage).getText();
    }


    public String getErrorMessage() {
        WebElement errorMessageElement = Constant.WEBDRIVER.findElement(errorMessage);
        return errorMessageElement.getText();
    }


    public RegisterPage register(String email, String password, String confirmPassword, String PID) {
        enterEmail(email);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        enterPID(PID);
        clickRegisterButton();
        return this;
    }
}



