package pageObjects.Railway;
import common.Constant.Constant;
import org.openqa.selenium.By;



public class HomePage extends  GeneralPage{
    // Locators
    private final By tabBookTicket = By.xpath("//a[@href='/Page/BookTicketPage.cshtml']");
    private final By tabMyTicket = By.xpath("//a[@href='/Page/ManageTicket.cshtml']");
    private final By tabChangePassword = By.xpath("//*[text()='Change password']"); // ✅ đã sửa đúng
    private final By tabLogout = By.xpath("//div[@id='menu']//a[@href='/Account/Logout']");
    private final By tabRegister = By.xpath("//a[@href='/Account/Register.cshtml']"); // Locator cho trang đăng ký
    private final By tabTimetable = By.xpath("//a[@href='TrainTimeListPage.cshtml']"); // Locator cho ta
    // Elements
    // Method
    public HomePage open()
    {
        Constant.WEBDRIVER.navigate().to(Constant.RAILWAY_URL);
        return this;
    }


    public BookTicketPage gotoBookTicketPage() {
        Constant.WEBDRIVER.findElement(tabBookTicket).click();
        return new BookTicketPage();
    }


    public boolean isMyTicketTabDisplayed() {
        return Constant.WEBDRIVER.findElement(this.tabMyTicket).isDisplayed();
    }


    public boolean isChangePasswordTabDisplayed() {
        return Constant.WEBDRIVER.findElement(this.tabChangePassword).isDisplayed();
    }


    public boolean isLogoutTabDisplayed() {
        return Constant.WEBDRIVER.findElement(this.tabLogout).isDisplayed();
    }


    public MyTicketPage gotoMyTicketPage() {
        Constant.WEBDRIVER.findElement(this.tabMyTicket).click();
        return new MyTicketPage();
    }


    public ChangePasswordPage gotoChangePasswordPage() {
        Constant.WEBDRIVER.findElement(this.tabChangePassword).click();
        return new ChangePasswordPage();
    }


    public RegisterPage gotoRegisterPage() {
        Constant.WEBDRIVER.findElement(tabRegister).click();
        return new RegisterPage();
    }


    public TimetablePage gotoTimetablePage() {
        Constant.WEBDRIVER.findElement(tabTimetable).click();
        return new TimetablePage();
    }
}





