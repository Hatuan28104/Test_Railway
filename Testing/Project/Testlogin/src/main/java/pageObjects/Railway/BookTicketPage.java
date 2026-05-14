package pageObjects.Railway;


import common.Constant.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
public class BookTicketPage {
    private final By _ddlDepartDate = By.xpath("//select[@name='Date']");
    private final By _ddlDepartStation = By.xpath("//select[@name='DepartStation']");
    private final By _ddlArriveStation = By.xpath("//select[@name='ArriveStation']");
    private final By _ddlSeatType = By.xpath("//select[@name='SeatType']");
    private final By _ddlTicketAmount = By.xpath("//select[@name='TicketAmount']");
    private final By _btnBookTicket = By.xpath("//input[@type='submit' and @value='Book ticket']");
    private final By _lblSuccessMsg = By.xpath("//div[@id='content']/h1");


    public WebElement getDdlDepartDate() {
        return Constant.WEBDRIVER.findElement(_ddlDepartDate);
    }


    public WebElement getDdlDepartStation() {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));


        try {
            return wait.until(ExpectedConditions.elementToBeClickable(_ddlDepartStation));
        } catch (TimeoutException e) {
            System.out.println("Đã hết thời gian chờ khi tìm tab '_ddlDepartStation': " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy tab '_ddlDepartStation': " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Một lỗi khác đã xảy ra: " + e.getMessage());
        }
        return null;


    }


    public WebElement getDdlArriveStation() {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(30));


        try {
            return wait.until(ExpectedConditions.elementToBeClickable(_ddlArriveStation));
        } catch (TimeoutException e) {
            System.out.println("Đã hết thời gian chờ khi tìm tab '_ddlArriveStation': " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Không tìm thấy tab '_ddlArriveStation': " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Một lỗi khác đã xảy ra: " + e.getMessage());
        }
        return null;
    }
    public String getSelectedDepartStation() {
        WebElement departStationDropdown = getDdlDepartStation();
        Select select = new Select(departStationDropdown);
        return select.getFirstSelectedOption().getText();
    }


    public String getSelectedArriveStation() {
        WebElement arriveStationDropdown = getDdlArriveStation();
        Select select = new Select(arriveStationDropdown);
        return select.getFirstSelectedOption().getText();
    }
    public WebElement getDdlSeatType() {
        return Constant.WEBDRIVER.findElement(_ddlSeatType);
    }


    public WebElement getDdlTicketAmount() {
        return Constant.WEBDRIVER.findElement(_ddlTicketAmount);
    }


    public WebElement getBtnBookTicket() {
        return Constant.WEBDRIVER.findElement(_btnBookTicket);
    }


    public WebElement getLblSuccessMsg() {
        return Constant.WEBDRIVER.findElement(_lblSuccessMsg);
    }


    public void selectDepartDate(String departDate) {
        WebElement dateDropdown = getDdlDepartDate();


        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(dateDropdown));


        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", dateDropdown);


        dateDropdown.click();


        wait.until(ExpectedConditions.visibilityOfAllElements(dateDropdown.findElements(By.tagName("option"))));


        for (WebElement option : dateDropdown.findElements(By.tagName("option"))) {
            if (option.getText().equals(departDate)) {
                wait.until(ExpectedConditions.elementToBeClickable(option));
                option.click();
                break;
            }
        }
    }


    public void selectDepartFrom(String departFrom) {
        WebElement departDropdown = getDdlDepartStation();
        departDropdown.click();
        for (WebElement option : departDropdown.findElements(By.tagName("option"))) {
            if (option.getText().equals(departFrom)) {
                option.click();
                break;
            }
        }
    }


    public void selectArriveAt(String arriveAt) {
        WebElement arriveDropdown = getDdlArriveStation();
        arriveDropdown.click();
        for (WebElement option : arriveDropdown.findElements(By.tagName("option"))) {
            if (option.getText().equals(arriveAt)) {
                option.click();
                break;
            }
        }
    }


    public void selectSeatType(String seatType) {
        WebElement seatDropdown = getDdlSeatType();
        seatDropdown.click();
        for (WebElement option : seatDropdown.findElements(By.tagName("option"))) {
            if (option.getText().equals(seatType)) {
                option.click();
                break;
            }
        }
    }


    public void selectTicketAmount(int ticketAmount) {
        WebElement amountDropdown = getDdlTicketAmount();
        amountDropdown.click();
        for (WebElement option : amountDropdown.findElements(By.tagName("option"))) {
            if (option.getText().equals(String.valueOf(ticketAmount))) {
                option.click();
                break;
            }
        }
    }


    public void bookTicket(String departDate, String departFrom, String arriveAt, String seatType, int ticketAmount) {
        selectDepartDate(departDate);
        selectDepartFrom(departFrom);
        selectArriveAt(arriveAt);
        selectSeatType(seatType);
        selectTicketAmount(ticketAmount);
        WebElement bookTicketLink = getBtnBookTicket();
        ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", bookTicketLink);
        bookTicketLink.click();


    }


    public String getSuccessMessage() {
        return getLblSuccessMsg().getText();
    }




    public TicketInfor getTicketInfo() {
        WebElement tableRow = Constant.WEBDRIVER.findElement(By.xpath("//div[@class='DivTable']//tr[@class='OddRow']"));


        String departStation = tableRow.findElement(By.xpath("./td[1]")).getText();
        String arriveStation = tableRow.findElement(By.xpath("./td[2]")).getText();
        String seatType = tableRow.findElement(By.xpath("./td[3]")).getText();
        String departDate = tableRow.findElement(By.xpath("./td[4]")).getText();
        int amount = Integer.parseInt(tableRow.findElement(By.xpath("./td[7]")).getText());


        return new TicketInfor(departStation, arriveStation, seatType, departDate, amount);
    }


    public MyTicketPage gotoMyTicketsPage() {
        WebElement myTicketsLink = Constant.WEBDRIVER.findElement(By.xpath("//a[@href='/Page/ManageTicket.cshtml']"));
        myTicketsLink.click();
        return new MyTicketPage();


    }
}

