package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SignUp {

    private static WebDriver driver;

    public SignUp(WebDriver driver) {
        SignUp.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "sign-username")
    private WebElement username;

    public void setusername(String user) {
        username.sendKeys(user);
        username.sendKeys(Keys.ENTER);
        new SignUp(driver);

    }

    @FindBy(id = "sign-password")
    private WebElement password;

    public void setpass(String pass) {
        password.sendKeys(pass);
        password.sendKeys(Keys.ENTER);
        new SignUp(driver);
    }
    //Localizar el css u otro selector que no sea el xpath
    @FindBy(xpath = "//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")
    private WebElement btnSignUp;

    public void clickbtnSignUp() {
        btnSignUp.click();
    }


}


