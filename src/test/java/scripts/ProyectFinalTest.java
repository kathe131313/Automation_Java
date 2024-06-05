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
import static org.testng.Assert.assertNotEquals;

public class ProyectFinalTest {
    WebDriver driver;
    private boolean acceptNextAlert = true;


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.demoblaze.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

        /* Test 1: Registrar usuario nuevo                                     */
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
/************************* Evaluar la alerta *********************************/
        try {
            // Esperar a que aparezca la alerta
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(80));
            wait.until(ExpectedConditions.alertIsPresent());

            // Obtener la instancia de la alerta
            Alert alert = driver.switchTo().alert();

            // Obtener el texto de la alerta
            String alertText = alert.getText();
            System.out.println("Texto de la alerta: " + alertText);
            if (alertText == "Sign up successful.") {
                assertEquals(alert, "Sign up successful");
            }
            if (alertText == "This user already exist.") {
                assertEquals(alert, "This user already exist.");
            }

            //  aceptar la aler)
            alert.accept();
        } catch (org.openqa.selenium.NoAlertPresentException e) {
            System.out.println("No se encontró ninguna alerta.");
        }

    }
    /* Test 2:  Hacer Login con los Usuarios Registrados en el test anterior   *********/
    @Test(dataProvider = "dataset_all", dataProviderClass = DataPerson.class)
    public void testLogin(String username, String pass)  {

        driver.get("https://www.demoblaze.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        LoginPage loginPage = homePage.clickLogin();
        loginPage.setloginuser(username);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        loginPage.setloginpass(pass);
        loginPage.clickbtnLogin();
        String mensajeEsperado = "Welcome "+ username;
        String mensajeRecibido = homePage.getWelcome();
        System.out.println("El mensaje recibido: " + mensajeRecibido);
        /* El usuario hace login sin error    */
        assertEquals(mensajeRecibido, mensajeEsperado, "El usuario no pudo hacer login");
        System.out.println("El mensaje de bienvenida es: " + homePage.getWelcome());

    }

    /* Test 3:  Hacer contacto con la empresa   *********/

    @Test(dataProvider = "dataset_contact", dataProviderClass = DataPerson.class)
    public void testContact(String mail, String name, String mensaje)  {

        driver.get("https://www.demoblaze.com/");
        HomePage homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        ContactPage contact = homePage.clickContact();
        contact.setMail(mail);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        contact.setName(name);
        contact.setMesa(mensaje);
        contact.clickbtnContact();
        String alert = closeAlertAndGetItsText();
        assertEquals(alert, "Thanks for the message!!");
        System.out.println("El mensaje alert es: " + alert);


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
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(300));
            wait.until(ExpectedConditions.alertIsPresent());
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



}
