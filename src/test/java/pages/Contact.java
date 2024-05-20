package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;
public class Contact {
    private static WebDriver driver;

    public Contact(WebDriver driver) {
        Contact.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "recipient-email")
    private WebElement cemail;

    public void setMail(String mail) {
        cemail.sendKeys(mail);
        cemail.sendKeys(Keys.ENTER);
        new Contact(driver);

    }
    @FindBy(id = "recipient-name")
    private WebElement rname;

    public void setName(String name) {
        rname.sendKeys(name);
        rname.sendKeys(Keys.ENTER);
        new Contact(driver);

    }
    @FindBy(id = "message-text")
    private WebElement mesa;

    public void setMesa(String name) {
        mesa.sendKeys(name);
        mesa.sendKeys(Keys.ENTER);
        new Contact(driver);

    }

    @FindBy(xpath = "//*[@id=\"exampleModal\"]/div/div/div[3]/button[2]")
    private WebElement btnSend;
    public void clickbtnContact() {
        btnSend.click();
    }

}
