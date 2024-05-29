package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class CarritoPage {
    private static WebDriver driver;

    public CarritoPage(WebDriver driver) {
        CarritoPage.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(className = "panel-title")
    public WebElement totap;

    public String getTota() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
        return totap.getText();
    }


}
