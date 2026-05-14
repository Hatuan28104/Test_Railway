package pageObjects.Railway;
import common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ChangePasswordPage {
    public ChangePasswordPage() {

        if (!isChangePasswordPageDisplayed()) {
            throw new IllegalStateException("This is not the Change Password page");
        }
    }

    public boolean isChangePasswordPageDisplayed() {
        String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        return currentUrl.contains("/Account/ChangePassword.cshtml");
    }

    public void enterCurrentPassword(String currentPassword) {
        WebElement currentPasswordField = Constant.WEBDRIVER.findElement(By.id("currentPassword"));
        currentPasswordField.clear();
        currentPasswordField.sendKeys(currentPassword);
    }

    public void enterNewPassword(String newPassword) {
        WebElement newPasswordField = Constant.WEBDRIVER.findElement(By.id("newPassword"));
        newPasswordField.clear();
        newPasswordField.sendKeys(newPassword);
    }

    public void enterConfirmPassword(String confirmPassword) {
        WebElement confirmPasswordField = Constant.WEBDRIVER.findElement(By.id("confirmPassword"));
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(confirmPassword);
    }

    public void ChangePasswordButton() {
        WebElement changePasswordButton = Constant.WEBDRIVER.findElement(By.xpath("//input[@value='Change Password']"));
        changePasswordButton.click();
    }
    public void ChangePassword(String currentPassword, String newPassword, String confirmPassword) {
        enterCurrentPassword(currentPassword);
        enterNewPassword(newPassword);
        enterConfirmPassword(confirmPassword);
        ChangePasswordButton();
    }

    public String getConfirmationMessage() {
        WebElement confirmationMessageElement = Constant.WEBDRIVER.findElement(By.xpath("//p[@class='message success']"));
        return confirmationMessageElement.getText();
    }
}

