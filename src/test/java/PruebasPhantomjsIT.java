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

        // Pulsa el botón de poner votos a cero
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("votosACero")).click();

        // Pulsa el botón de ver votos
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.id("verVotos")).click();

        // Obtiene todas las celdas de la columna "Votos"
        List<WebElement> votosCells = driver.findElements(By.xpath("//table[@id='tablaVotos']//td[2]"));

        // Verifica que todas las celdas tengan el valor "0"
        boolean todasCeldasSonCero = votosCells.stream().allMatch(cell -> cell.getText().equals("0"));

        assertEquals(true,todasCeldasSonCero, "El numero de votos no es correcto");


        driver.close();
        driver.quit();
    }

    // @Test
    // public void votarOtroYComprobarVotosTest() {
    //     DesiredCapabilities caps = new DesiredCapabilities();
    //     caps.setJavascriptEnabled(true);
    //     caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
    //     caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
    //             new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
    //     driver = new PhantomJSDriver(caps);
    //     driver.navigate().to("http://localhost:8080/Baloncesto/");

    //     //Introducimos el nombre del jugador nuevo
    //     driver.findElement(By.name("txtOtros")).sendKeys("Pepe");

    //     //Seleccionamos el radio button de Otros
    //     driver.findElement(By.id("rdbtnOtros")).click();

    //     //Pulsamos el boton de votar
    //     driver.findElement(By.name("btnVotar")).click();

    //     //Volvemos a la página principal
    //     driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    //     driver.navigate().to("http://localhost:8080/Baloncesto/");

    //     //Pulsamos el boton de ver votos
    //     driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    //     driver.findElement(By.id("verVotos")).click();

    //     // Encuentra la celda correspondiente al jugador "Pepe" en la columna de votos
    //     driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    //     WebElement celdaPepe = driver.findElement(By.xpath("//table[@id='tablaVotos']//td[text()='Llull']/following-sibling::td"));

    //     //Comparamo el valor de la celda con el valor esperado
    //     assertEquals("1", celdaPepe.getText(), "El numero de votos no es correcto");

    //     driver.close();
    //     driver.quit();
    // }

}