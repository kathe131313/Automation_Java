package pages;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class ContactPage {
    private static WebDriver driver;

    public ContactPage(WebDriver driver) {
        ContactPage.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "recipient-email")
    private WebElement cemail;

    public void setMail(String mail) {
        cemail.sendKeys(mail);
        cemail.sendKeys(Keys.ENTER);

    }
    @FindBy(id = "recipient-name")
    private WebElement rname;

    public void setName(String name) {
        rname.sendKeys(name);
        rname.sendKeys(Keys.ENTER);

    }
    @FindBy(id = "message-text")
    private WebElement mesa;

    public void setMesa(String name) {
        mesa.sendKeys(name);
        mesa.sendKeys(Keys.ENTER);
    }

    @FindBy(xpath = "//*[@id=\"exampleModal\"]/div/div/div[3]/button[2]")
    private WebElement btnSend;
    public void clickbtnContact() {
        btnSend.click();
    }

}
