package datasets;

import org.testng.annotations.DataProvider;

public class DataPerson {

    @DataProvider(name = "dataset_all")
    public static Object[][] getPersonData() {
        return new Object[][]{
                {"Juana14", "Selenium.123"}

        };
    }
    @DataProvider(name = "dataset_contact")
    public static Object[][] getContactData() {
        return new Object[][]{
                {"larosa.melania@gmail.com", "Melania","Me comunico para pedir asesoria técnica"}
        };
    }
}
