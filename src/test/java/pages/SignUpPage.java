package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SignUpPage {

    private static WebDriver driver;

    public SignUpPage(WebDriver driver) {
        SignUpPage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "sign-username")
    private WebElement username;

    public void setusername(String user) {
        username.sendKeys(user);
        username.sendKeys(Keys.ENTER);
        //new SignUpPage(driver);

    }

    @FindBy(id = "sign-password")
    private WebElement password;

    public void setpass(String pass) {
        password.sendKeys(pass);
        password.sendKeys(Keys.ENTER);
    }
    //Localizar el css u otro selector que no sea el xpath
    @FindBy(xpath = "//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")
    private WebElement btnSignUp;

    public void clickbtnSignUp() {
        btnSignUp.click();
    }


}


