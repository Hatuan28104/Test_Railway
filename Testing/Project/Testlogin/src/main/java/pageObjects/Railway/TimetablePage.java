package pageObjects.Railway;


import common.Constant.Constant;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;


public class TimetablePage {
    public BookTicketPage clickBookTicketLink(String departFrom, String arriveAt) {


        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='MyTable WideTable']")));
        List<WebElement> rows = Constant.WEBDRIVER.findElements(By.xpath("//table[@class='MyTable WideTable']/tbody/tr"));
        for (WebElement row : rows) {


            String actualDepartFrom = row.findElement(By.xpath(".//td[2]")).getText();
            String actualArriveAt = row.findElement(By.xpath(".//td[3]")).getText();


            if (actualDepartFrom.equals(departFrom) && actualArriveAt.equals(arriveAt)) {


                WebElement bookTicketLink = row.findElement(By.xpath(".//td/a[contains(text(), 'book ticket')]"));
                ((JavascriptExecutor) Constant.WEBDRIVER).executeScript("arguments[0].scrollIntoView(true);", bookTicketLink);
                bookTicketLink.click();
                return new BookTicketPage();
            }
        }


        throw new NoSuchElementException("No booking link found for the route from " + departFrom + " to " + arriveAt);
    }
}



