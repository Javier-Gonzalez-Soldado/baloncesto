import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PruebasPhantomjsIT {
    private static WebDriver driver = null;

    @Test
    public void tituloIndexTest() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");
        assertEquals("Votacion mejor jugador liga ACB", driver.getTitle(),
                "El titulo no es correcto");
        System.out.println(driver.getTitle());
        driver.close();
        driver.quit();
    }

    @Test
    public void votosACeroYVerVotosTest() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("votosACero")).click();

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("verVotos")).click();

        // WebElement tabla = driver.findElement(By.id("tablaVotos"));
        // List<WebElement> filas = tabla.findElements(By.tagName("tr"));
        // System.out.println("Objeto Filas: " + filas);
        // for (WebElement fila : filas) {
        //     WebElement celdaDelVoto = fila.findElements(By.tagName("td")).get(1);
        //     assertEquals("0", celdaDelVoto.getText(),
        //             "El numero de votos no es correcto");
        // }

        // Obtiene todas las celdas de la columna "Votos"
        // Utiliza la clase "votes" si has asignado una clase a las celdas de votos
        // Puedes ajustar el selector según la estructura real de tu HTML
        List<WebElement> votosCells = driver.findElements(By.xpath("//table[@id='tablaVotos']//td[2]"));

        // Verifica que todas las celdas tengan el valor "0"
        boolean todasCeldasSonCero = votosCells.stream().allMatch(cell -> cell.getText().equals("0"));

        assertEquals(true,todasCeldasSonCero, "El numero de votos no es correcto");


        driver.close();
        driver.quit();
    }
}