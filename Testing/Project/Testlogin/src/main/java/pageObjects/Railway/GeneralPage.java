package pageObjects.Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import common.Constant.Constant;
public class GeneralPage {
    // Locators
    private final By tabLogin = By.xpath("//div[@id='menu']//a[@href='/Account/Login.cshtml']");
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By lblwelcomeMessage = By.xpath("//div[@class='account']/strong");
    private final By lblErrorMessage = (By.xpath("//p[@class='message error LoginForm']"));
    //Elements
    protected WebElement getTabLogin() {
        return Constant.WEBDRIVER.findElement(tabLogin);
    }


    protected WebElement getTabLogout() {
        return Constant.WEBDRIVER.findElement(tabLogout);
    }
    protected WebElement getLblwelcomeMessage() {
        return Constant.WEBDRIVER.findElement(lblwelcomeMessage);
    }
    protected WebElement getLblErrorMessage() {
        return Constant.WEBDRIVER.findElement(lblErrorMessage);
    }
    //Methods
    public String getWelcomeMessage()
    {
        return this.getLblwelcomeMessage().getText();
    }
    public LoginPage gotoLoginPage()
    {
        this.getTabLogin().click();
        return new LoginPage();
    }
}



