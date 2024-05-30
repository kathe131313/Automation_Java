package scripts;
import datasets.DataPerson;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.*;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class ProyectFinalTest {
    WebDriver driver;
    private boolean acceptNextAlert = true;


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    /* Este test prueba que el sistema seleccione el tipo de producto, luego el producto y luego lo adiciona al carrito*/
    /*                       Esto lo hace con los tres (3) tipos de producto. Acá van tres tests                       */
    @Parameters({"listproductParam","productParam","priceParam","addcartParam", "totapParam"})
    @Test
    public void testProduct(String listproduct, String product, String price, String addcar,String totap) {
        driver.get("https://www.demoblaze.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        homePage.selecProduct(listproduct);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
        ProductPage productPage = homePage.clickProduct(product);
        assertEquals(productPage.getPrice(), price);
        productPage.clickAddCar(addcar);
        /* Verificar el carrito de compras*/
        CarritoPage carritoPage = homePage.clickCar();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
        wait.until(ExpectedConditions.visibilityOf(carritoPage.totap));
        assertEquals(carritoPage.getTota(), totap);



    }

    /* Test 4: Registrar usuario nuevo                                     */
    @Test(dataProvider = "dataset_all", dataProviderClass = DataPerson.class)
    public void testSignUp(String username, String pass)  {


        driver.get("https://www.demoblaze.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        SignUpPage signUp = homePage.clickSignUp();
        signUp.setusername(username);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        signUp.setpass(pass);
        signUp.clickbtnSignUp();

    }
    /* Test 5:  Hacer Login con los Usuarios Registrados en el test anterior   *********/
    @Test(dataProvider = "dataset_all", dataProviderClass = DataPerson.class)
    public void testLogin(String username, String pass)  {

        driver.get("https://www.demoblaze.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        LoginPage loginPage = homePage.clickLogin();
        loginPage.setloginuser(username);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        loginPage.setloginpass(pass);
        loginPage.clickbtnLogin();
    }

    /* Test 6:  Hacer contacto con la empresa   *********/

    @Test(dataProvider = "dataset_contact", dataProviderClass = DataPerson.class)
    public void testContact(String mail, String name, String mensaje)  {

        driver.get("https://www.demoblaze.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30
        ));
        ContactPage contact = homePage.clickContact();
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
    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
