package pageObjects.Railway;


import common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class ForgotPasswordPage extends GeneralPage {
    // Locators
    private final By _txtEmail = By.id("email"); // Thay đổi ID cho đúng
    private final By _btnSendInstructions = By.id("sendInstructions"); // Thay đổi ID cho đúng


    // Elements
    public WebElement getTxtEmail() {
        return Constant.WEBDRIVER.findElement(_txtEmail);
    }


    public WebElement getBtnSendInstructions() {
        return Constant.WEBDRIVER.findElement(_btnSendInstructions);
    }


    // Methods
    public void enterEmail(String email) {
        this.getTxtEmail().clear();
        this.getTxtEmail().sendKeys(email);
    }


    public void clickSendInstructionsButton() {
        this.getBtnSendInstructions().click();
    }
}

