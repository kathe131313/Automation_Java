package scripts;

import datasets.DataPerson;
import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ProyectFinalTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }


    @Parameters({"listproductParam","productParam","priceParam","addcartParam"})
    @Test
    public void testPhone(String listproduct,String product, String price, String addcar) {
        driver.get("https://www.demoblaze.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        homePage.selecProduct(listproduct);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        ProductPage productPage = homePage.clickProduct(product);
        productPage.clickAddCar(addcar);
        assertEquals(driver.findElement(By.linkText(product)).getText(), product);
        //assertEquals(closeAlertAndGetItsText(), "Product added");


    }

    /* Test 1: Registrar usuario nuevo                                     */
    @Test(dataProvider = "dataset_all", dataProviderClass = DataPerson.class)
    public void testSignUp(String username, String pass)  {


        driver.get("https://www.demoblaze.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        SignUp signUp = homePage.clickSignUp();
        signUp.setusername(username);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        signUp.setpass(pass);
        signUp.clickbtnSignUp();

    }
    /* Test 2:  Hacer Login con los Usuarios Registrados en el test anterior   *********/
    @Test(dataProvider = "dataset_all", dataProviderClass = DataPerson.class)
    public void testLogin(String username, String pass)  {

        driver.get("https://www.demoblaze.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Login login = homePage.clickLogin();
        login.setloginuser(username);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        login.setloginpass(pass);
        login.clickbtnLogin();
    }

    /* Test 3:  Hacer contacto con la empresa   *********/

    @Test(dataProvider = "dataset_contact", dataProviderClass = DataPerson.class)
    public void testContact(String mail, String name, String mensaje)  {

        driver.get("https://www.demoblaze.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30
        ));
        Contact contact = homePage.clickContact();
        contact.setMail(mail);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        contact.setName(name);
        contact.setMesa(mensaje);
        contact.clickbtnContact();
    }
    /*************************Evidencia de Pruebas"*********************************************************/
    @Attachment(type = "image/png")
    @AfterMethod(alwaysRun = true)
    public byte[] takeScreenshot() {
        byte[] image = new byte[0];
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            image = screenshot.getScreenshotAs(OutputType.BYTES);
            System.out.println("Successfully captured a screenshot");
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
        return image;
    }



}
