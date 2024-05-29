package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private static WebDriver driver;

    /********************** Ubicar el elemento  "Sign Up"*********************************************************/
    @FindBy(id = "signin2")
    private WebElement signup;

    public HomePage(WebDriver driver) {
        HomePage.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /********************** Método click a "Sign Up"********************************************************/

    @Step("Haciendo click en Sign Up")
    public SignUpPage clickSignUp() {
        signup.click();
        return new SignUpPage(driver);

    }
    /********************** Método click a "Login"********************************************************/
    @FindBy(id = "login2")
    private WebElement login;

    @Step("Haciendo click en Login")
    public LoginPage clickLogin() {
        login.click();
        return new LoginPage(driver);

    }
    /********************** Método click a "Contact"********************************************************/
    @FindBy(css = "#navbarExample > ul > li:nth-child(2) > a")
    private WebElement contact;

    @Step("Haciendo click en Contact")
    public ContactPage clickContact() {
        contact.click();
        return new ContactPage(driver);

    }

    @FindBy(css = "a#itemc:nth-of-type(2)")
    private WebElement phones;
    @FindBy(css = "a#itemc:nth-of-type(3)")
    private WebElement laptops;
    @FindBy(css = "a#itemc:nth-of-type(4)")
    private WebElement monitores;


    public void clickPhone(){
            phones.click();
    }
    public void clickLaptops(){
        laptops.click();
    }
    public void clickMonitores(){
        monitores.click();
    }
    public HomePage selecProduct(String listproduct) {
        WebElement clickProd = driver.findElement(By.linkText(listproduct));
        clickProd.click();
        return new HomePage(driver);
    }
    public ProductPage clickProduct(String product) {
        WebElement clickProd = driver.findElement(By.linkText(product));
        clickProd.click();
        return new ProductPage(driver);
    }
    @FindBy(id = "cartur")
    private WebElement cartur;

    public CarritoPage clickCar(){
        cartur.click();
        return new CarritoPage(driver);

    }


}
