package testcases.Railway;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import common.Constant.Constant;
import org.testng.annotations.Test;
import pageObjects.Railway.*;


public class LoginTest {
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Pre-condition");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();
    }
    @AfterMethod
    public void afterMethod() {
        System.out.println("Post-condition");
        Constant.WEBDRIVER.quit();
    }
    @Test
    public void TC01() {
        System.out.println("TC01 - User can log into Railway with valid username and password");
        // Mở trang chủ
        HomePage homePage = new HomePage();
        homePage.open();
        // Điều hướng đến trang Login
        LoginPage loginPage = homePage.gotoLoginPage();
        // Đăng nhập
        String username = "Hoanganhtuan2300@gmail.com";
        String password = "MHRUnFdaLHcDr5";

        String actualMsg = loginPage.login(username, password).getWelcomeMessage();
        String expectedMsg = "Welcome " + username;

        Assert.assertEquals(actualMsg, expectedMsg, "Welcome message is not displayed as expected");
    }
    @Test
    public void TC02() {
        System.out.println("TC02 - User can't login with blank 'Username' textbox");
        // Mở trang chủ
        HomePage homePage = new HomePage();
        homePage.open();
        // Điều hướng đến trang Login
        LoginPage loginPage = homePage.gotoLoginPage();
        // Đăng nhập với username rỗng và mật khẩu hợp lệ
        String username = "";
        String password = "MHRUnFdaLHcDr5";
        loginPage.login(username, password);

        // Lấy thông báo lỗi
        String actualErrorMsg = loginPage.getErrorMessage();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";

        // Kiểm tra thông báo lỗi
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg,
                "Error message is not displayed as expected when 'Username' is blank");
    }
    @Test
    public void TC03() {
        System.out.println("TC03 - User cannot login with invalid password");
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        // Dùng username hợp lệ và password không hợp lệ
        String username = "Hoanganhtuan2300@gmail.com";
        String password = "";
        loginPage.login(username, password);

        String actualErrorMsg = loginPage.getErrorMessage();
        String expectedErrorMsg = "There was a problem with your login and/or errors exist in your form.";
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg,
                "Error message is not displayed as expected when invalid password is entered");
    }

    @Test
    public void TC04() {
        System.out.println("TC04 - Verify that user is redirected to Login page after navigating to a protected page without login");
        HomePage homePage = new HomePage();
        homePage.open();
        BookTicketPage BookTicketPage = homePage.gotoBookTicketPage();
        String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        String expectedUrl = "http://railwayb1.somee.com/Account/Login.cshtml";
        Assert.assertTrue(currentUrl.startsWith(expectedUrl), "Login page displays instead of Book ticket page");
    }
    @Test
    public void TC05() {
        System.out.println("TC05 - Verify warning message after 5 failed login attempts with correct username and incorrect password");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        String validUsername = "Hoanganhtuan2300@gmail.com";
        String invalidPassword = "wrongpass";

        // Thử đăng nhập sai 5 lần
        for (int i = 1; i <= 5; i++) {
            loginPage.login(validUsername, invalidPassword);
        }

        // Kiểm tra cảnh báo sau lần thứ 5
        String actualMessage = loginPage.getLoginErrorMessage();
        String expectedMessage = "You have used 5 out of 5 login attempts. You have been locked out for 15 minutes.";

        Assert.assertEquals(actualMessage, expectedMessage,
                "Warning message after 5 failed login attempts is not as expected.");
    }

    @Test
    public void TC06() {
        System.out.println("TC06 - Additional pages display once user logged in");

        // Mở trang chủ
        HomePage homePage = new HomePage();
        homePage.open();

        // Điều hướng đến trang đăng nhập
        LoginPage loginPage = homePage.gotoLoginPage();

        // Đăng nhập bằng tài khoản hợp lệ
        loginPage.login("hoanganhtuan2300@gmail.com", "MHRUnFdaLHcDr5");

        // Kiểm tra sự hiển thị của các tab sau khi đăng nhập
        Assert.assertTrue(homePage.isMyTicketTabDisplayed(), "My Ticket tab is not displayed");
        Assert.assertTrue(homePage.isChangePasswordTabDisplayed(), "Change Password tab is not displayed");
        Assert.assertTrue(homePage.isLogoutTabDisplayed(), "Logout tab is not displayed");

        // Kiểm tra điều hướng đến trang "My Ticket"
        MyTicketPage myTicketPage = homePage.gotoMyTicketPage();
        Assert.assertTrue(myTicketPage.isMyTicketPageDisplayed(), "User is not directed to My Ticket page");

        // Kiểm tra điều hướng đến trang "Change Password"
        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();
        Assert.assertTrue(changePasswordPage.isChangePasswordPageDisplayed(), "User is not directed to Change Password page");
    }
    @Test
    public void TC07() {
        System.out.println("TC07 - User can create a new account");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();
        // Tạo thông tin
        String email = "newuser" + System.currentTimeMillis() + "@mail.com"; // random email
        String password = "Password123";
        String confirmPassword = "Password123";
        String PID = "123456789";
        // Điền tt
        registerPage.register(email, password, confirmPassword, PID);

        String expectedMessage = "Registration Confirmed! You can now log in to the site.";
        String actualMessage = registerPage.getConfirmationMessage();
        System.out.println("Actual confirmation message: '" + actualMessage + "'");

        Assert.assertEquals(actualMessage.trim(), expectedMessage, "Confirmation message is not displayed as expected.");
    }

    @Test
    public void TC08() {
        System.out.println("TC08 - User can't login with an account hasn't been activated");

        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();

        String unactivatedUsername = "unactivated_user@gmail.com";
        String password = "Password123";

        loginPage.login(unactivatedUsername, password);

        String actualErrorMsg = loginPage.getErrorMessage();
        String expectedErrorMsg = "Invalid username or password. Please try again.";

        Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error message is not displayed as expected for unactivated account");
    }

    @Test
    public void TC09() {
        System.out.println("TC09 - User can change password");

        // Khởi tạo và mở trang chủ
        HomePage homePage = new HomePage();
        homePage.open();

        // Đăng nhập vào hệ thống
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("hoanganhtuan2300@gmail.com", "MHRUnFdaLHcDr5");

        // Chuyển tới trang thay đổi mật khẩu
        ChangePasswordPage changePasswordPage = homePage.gotoChangePasswordPage();

        // Nhập thông tin mật khẩu
        String currentPassword = "MHRUnFdaLHcDr5";
        String newPassword = "MHRUnFdaLHcDr55";
        String confirmPassword = "MHRUnFdaLHcDr55";
        changePasswordPage.ChangePassword(currentPassword, newPassword, confirmPassword);

        // Kiểm tra thông báo xác nhận
        String actualMessage = changePasswordPage.getConfirmationMessage();
        String expectedMessage = "Your password has been updated!"; // Đã thêm dấu chấm than

        Assert.assertEquals(actualMessage, expectedMessage, "Confirmation message is not displayed as expected");
    }

    @Test
    public void TC10() {
        System.out.println("TC10 - User can't create account with 'Confirm password' not matching 'Password'");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        String email = "newuser@gmail.com";
        String password = "Password123";
        String confirmPassword = "DifferentPassword";
        String PID = "123456789";
        registerPage.register(email, password, confirmPassword, PID);

        String actualErrorMessage = registerPage.getErrorMessage();
        String expectedErrorMessage = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not displayed as expected when passwords do not match");
    }
    @Test
    public void TC11() {
        System.out.println("TC11 - User can't create account while password and PID fields are empty");

        HomePage homePage = new HomePage();
        homePage.open();

        RegisterPage registerPage = homePage.gotoRegisterPage();

        String email = "Quanmin203@gmail.com";
        String password = "";
        String confirmPassword = "";
        String PID = "";
        registerPage.register(email, password, confirmPassword, PID);

        String actualErrorMessage = registerPage.getErrorMessage();
        String expectedErrorMessage = "There're errors in the form. Please correct the errors and try again.";

        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message is not displayed as expected");

        System.out.println("TC11 - Error message displayed correctly when password and PID are empty");
    }
    @Test
    public void TC12() {
        System.out.println("TC12 Errors display when password reset token is blank");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        ForgotPasswordPage forgotPasswordPage = loginPage.gotoForgotPasswordPage();
        forgotPasswordPage.enterEmail("hoanganhtuan2300@gmail.com");
        Assert.assertTrue(false,"Bug");
    }
    @Test
    public void TC13() {
        System.out.println("TC13 Errors display if password and confirm password don't match when resetting password");
        HomePage homePage = new HomePage();
        homePage.open();
        LoginPage loginPage = homePage.gotoLoginPage();
        ForgotPasswordPage forgotPasswordPage = loginPage.gotoForgotPasswordPage();
        forgotPasswordPage.enterEmail("hoanganhtuan2300@gmail.com");
        Assert.assertTrue(false,"Bug");
    }
    @Test
    public void TC14() {
        System.out.println("TC14 - User can book 1 ticket at a time");

        // Mở trang chủ và đăng nhập
        HomePage homePage = new HomePage();
        homePage.open();

        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("hoanganhtuan2300@gmail.com", "MHRUnFdaLHcDr5");

        // Chuyển đến trang đặt vé
        BookTicketPage bookTicketPage = homePage.gotoBookTicketPage();

        // Đặt vé
        bookTicketPage.bookTicket("5/18/2025", "Sài Gòn", "Nha Trang", "Soft bed with air conditioner", 1);

        // Xác minh thông tin đặt vé
        String actualMsg = bookTicketPage.getSuccessMessage();
        String expectedMsg = "Ticket booked successfully!";
        Assert.assertEquals(actualMsg.trim(), expectedMsg.trim(), "Success message is not displayed as expected.");

        // Lấy thông tin vé đã đặt
        TicketInfor ticketInfor = bookTicketPage.getTicketInfo();

        // Kiểm tra thông tin vé
        Assert.assertEquals(ticketInfor.getDepartDate(), "5/18/2025", "Depart Date is not correct.");
        Assert.assertEquals(ticketInfor.getDepartStation(), "Sài Gòn", "Depart Station is not correct.");
        Assert.assertEquals(ticketInfor.getArriveStation(), "Nha Trang", "Arrive Station is not correct.");
        Assert.assertEquals(ticketInfor.getSeatType(), "Soft bed with air conditioner", "Seat Type is not correct.");
        Assert.assertEquals(ticketInfor.getAmount(), 1, "Ticket Amount is not correct.");
    }
    @Test
    public void TC15() {
        System.out.println("TC15 - User can open 'Book ticket' page by clicking on 'Book ticket' link in 'Train timetable' page");

        // Mở trang HomePage
        HomePage homePage = new HomePage();
        homePage.open();

        // Đăng nhập
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("hoanganhtuan2300@gmail.com", "MHRUnFdaLHcDr5");

        // Chuyển đến trang timetable
        TimetablePage timetablePage = homePage.gotoTimetablePage();

        // Click vào liên kết 'Book ticket' cho chuyến tàu từ 'Huế' đến 'Sài Gòn'
        BookTicketPage bookTicketPage = timetablePage.clickBookTicketLink("Huế", "Sài Gòn");

        // In ra để kiểm tra
        System.out.println("Selected Departure: " + bookTicketPage.getSelectedDepartStation());
        System.out.println("Expected Departure: " + "Huế");

        // Kiểm tra giá trị 'Departure Station' trên trang 'BookTicketPage'
        Assert.assertEquals(bookTicketPage.getSelectedDepartStation().trim(), "Huế", "Departure from unexpected value");

        // Kiểm tra giá trị 'Arrive Station' trên trang 'BookTicketPage'
        Assert.assertEquals(bookTicketPage.getSelectedArriveStation().trim(), "Sài Gòn", "Arrive at value is not as expected");
    }
    @Test
    public void TC16() {
        System.out.println("TC16 - User can cancel a ticket");

        // Step 1: Navigate to QA Railway Website
        HomePage homePage = new HomePage();
        homePage.open();

        // Step 2: Login with a valid account
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login("hoanganhtuan2300@gmail.com", "MHRUnFdaLHcDr5");

        // Step 3: Book a ticket
        TimetablePage timetablePage = homePage.gotoTimetablePage();
        BookTicketPage bookTicketPage = timetablePage.clickBookTicketLink("Sài Gòn", "Nha Trang");
        bookTicketPage.getBtnBookTicket();

        // Step 4: Navigate to "My Tickets" page
        MyTicketPage myTicketPage = bookTicketPage.gotoMyTicketsPage();
        Assert.assertTrue(myTicketPage.isMyTicketPageDisplayed(), "My Ticket page is not displayed.");

        // Step 5: Click on the "Cancel" button for the ticket to cancel
        myTicketPage.cancelTicket();

        // Step 6: Click on "OK" button on the confirmation message
        myTicketPage.confirmCancelTicket();

        Assert.assertFalse(myTicketPage.isTicketDisplayed(), "The canceled ticket is still displayed.");
    }
}
