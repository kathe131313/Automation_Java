package pages;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;

public class Login {
    private static WebDriver driver;

    public Login(WebDriver driver) {
        Login.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "loginusername")
    private WebElement loginuser;

    public void setloginuser(String user) {
        loginuser.sendKeys(user);
        loginuser.sendKeys(Keys.ENTER);
        new Login(driver);

    }
    @FindBy(id = "loginpassword")
    private WebElement logipass;

    public void setloginpass(String user) {
        logipass.sendKeys(user);
        logipass.sendKeys(Keys.ENTER);
        new Login(driver);

    }

    @FindBy(xpath = "//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")
    private WebElement btnLogin;

public void clickbtnLogin () {
    btnLogin.click();
}


    public void alertUserExiste() throws InterruptedException {
        // Switching to Alert
        Alert alert = driver.switchTo().alert();
        String alertMessage = alert.getText();
        // Displaying alert message
        System.out.println(alertMessage);
        sleep(5000);
        assertEquals(alertMessage, "El usuario ya existe");
        // Accepting alert
        alert.accept();
        new Login(driver);
    }
}
