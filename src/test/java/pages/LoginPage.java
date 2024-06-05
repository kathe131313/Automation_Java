package pages;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private static WebDriver driver;

    public LoginPage(WebDriver driver) {
        LoginPage.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "loginusername")
    private WebElement loginuser;

    public void setloginuser(String user) {
        loginuser.sendKeys(user);
        loginuser.sendKeys(Keys.ENTER);

    }
    @FindBy(id = "loginpassword")
    private WebElement logipass;

    public void setloginpass(String user) {
        logipass.sendKeys(user);
        logipass.sendKeys(Keys.ENTER);

    }

    @FindBy(xpath = "//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")
    private WebElement btnLogin;

public void clickbtnLogin () {
    btnLogin.click();
    //new HomePage(driver);
}

}
