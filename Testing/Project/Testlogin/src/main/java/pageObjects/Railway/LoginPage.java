package pageObjects.Railway;
import common.Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class LoginPage extends GeneralPage{
    //Locators
    private final By _txtUsername = By.xpath("//input[@id='username']");
    private final By _txtPassword = By.xpath("//input[@id='password']");
    private final By _btnLogin = By.xpath("//input[@value='login']");
    private final By _lblLoginErrorMsg = By.xpath("//p[@class='message error LoginForm']");
    private final By _lnkForgotPassword = By.xpath("//a[@href='/Account/ForgotPassword.cshtml']");


    //Elements
    public WebElement getTxtUsername()
    {
        return Constant.WEBDRIVER.findElement(_txtUsername);
    }


    public WebElement getTxtPassword()
    {
        return Constant.WEBDRIVER.findElement(_txtPassword);
    }


    public WebElement getBtnLogin()
    {
        return Constant.WEBDRIVER.findElement(_btnLogin);
    }


    public WebElement getLnlLoginErrorMsg()
    {
        return Constant.WEBDRIVER.findElement(_lblLoginErrorMsg);
    }


    public WebElement getLnkForgotPassword() {
        return Constant.WEBDRIVER.findElement(_lnkForgotPassword);
    }






    //Methods
    public HomePage login(String username, String password)
    {
        this.getTxtUsername().sendKeys(username);
        this.getTxtPassword().sendKeys(password);
        this.getBtnLogin().click();
        return new HomePage();
    }


    public String getErrorMessage() {
        return this.getLnlLoginErrorMsg().getText();
    }


    public ForgotPasswordPage gotoForgotPasswordPage() {
        this.getLnkForgotPassword().click();
        return new ForgotPasswordPage();
    }
    public String getLoginErrorMessage() {
        WebElement errorElement = Constant.WEBDRIVER.findElement(By.xpath("//p[@class='message error LoginForm']"));
        return errorElement.getText();
    }


}



