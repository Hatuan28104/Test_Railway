package pageObjects.Railway;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import common.Constant.Constant;

public class MyTicketPage {

    private final By btnCancelTicket = By.xpath("//input[@value='Cancel']");
    private final By confirmationAlertOkButton = By.xpath("//button[contains(text(),'OK')]");  // XPath cho nút OK của hộp thoại xác nhận
    private final By ticketList = By.xpath("//div[@class='DivTable']");

    // Kiểm tra xem trang 'My Ticket' có đang hiển thị không
    public boolean isMyTicketPageDisplayed() {
        String currentUrl = Constant.WEBDRIVER.getCurrentUrl();
        return currentUrl.contains("http://railwayb1.somee.com/Page/ManageTicket.cshtml");
    }

    // Method để hủy vé
    public void cancelTicket() {
        WebElement btnCancel = Constant.WEBDRIVER.findElement(btnCancelTicket);

        // Scroll to element (đảm bảo nó nằm trong vùng nhìn thấy)
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", btnCancel);

        // Click bằng JavaScript để tránh bị che khuất
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].click();", btnCancel);

        // Chấp nhận alert (nếu có)
        try {
            Constant.WEBDRIVER.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No alert present to accept.");
        }
    }

    // Method để xác nhận hủy vé (nhấn nút OK trong hộp thoại xác nhận)
    public void confirmCancelTicket() {
        try {
            // Chờ nút OK xuất hiện với thời gian tối đa là 10 giây
            WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'OK')]")));

            // Kiểm tra xem nút OK có hiển thị và nhấn vào đó
            if (confirmButton.isDisplayed() && confirmButton.isEnabled()) {
                confirmButton.click();
            }
        } catch (Exception e) {
            System.out.println("Confirmation button not found or not clickable.");
            e.printStackTrace();
        }
    }

    // Kiểm tra xem vé có hiển thị trong danh sách không
    public boolean isTicketDisplayed() {
        return Constant.WEBDRIVER.findElements(ticketList).size() > 0;
    }
}
