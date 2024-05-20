package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;
public class ProductPage {
    private static WebDriver driver;

    public ProductPage(WebDriver driver) {
        ProductPage.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(css = "#tbodyid > h3")
    private WebElement price;
    public String getPrice() {

        return price.getText();
    }
    public ProductPage clickAddCar(String addcar) {
        WebElement clickAdd = driver.findElement(By.linkText(addcar));
        clickAdd.click();
        return new ProductPage(driver);
    }

}
