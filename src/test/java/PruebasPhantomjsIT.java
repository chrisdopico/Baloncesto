import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PruebasPhantomjsIT {
    private static PhantomJSDriver driver = null;

    @Test
    void tituloIndexTest() {
        System.out.println("Primera Prueba");
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
    void setVotosToCeroTest() {
        System.out.println("Segunda Prueba");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");

        // Se realiza la limpieza de votos
        driver.findElement(By.name("B2")).click();
        // Regresa a página principal
        driver.findElement(By.tagName("a")).click();
        // Se abre la página para visualizar los votos
        driver.findElement(By.name("B3")).click();

        // Se obtiene la lista de los valores de las votaciones de cada uno de los
        // jugadores
        WebElement tableVotes = driver.findElement(By.tagName("table"));
        ArrayList<WebElement> rows = new ArrayList<>(tableVotes.findElements(By.tagName("tr")));
        // Se elimina la cabecera de las columnas de la tabla
        rows.remove(0);
        ArrayList<WebElement> listWebElementsVotes = new ArrayList<>();
        // Se obtiene la lista de votos de cada jugador de cada fila
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            // Se coge solo el campo de los votos de la fila
            listWebElementsVotes.add(cells.get(2));
        }

        boolean expectedResult = true;
        boolean verify = true;
        int counter = 0;
        // Se comprueba que el valor de los votos de cada jugador están a cero
        while (verify && counter < listWebElementsVotes.size()) {
            if (!listWebElementsVotes.get(counter).getText().trim().equals("0")) {
                verify = false;
            }
            counter++;
        }

        assertEquals(expectedResult, verify, "No se ha realizado la puesta a 0 de los votos.");

    }

    @Test
    void setVoteOtherPlayerTest() {
        System.out.println("Tercera Prueba");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");

        // Se marca el radio button para votar por otro jugador
        driver.findElement(By.name("R1")).click();
        // Se introduce en su campo de texto el nombre de un jugador
        String namePlayer = "Brandon Davies";
        driver.findElement(By.name("txtOtros")).sendKeys(namePlayer);
        // Se realiza el envío del voto realizado
        driver.findElement(By.name("B1")).click();
        // Se vuelve a la página principal
        driver.findElement(By.tagName("a")).click();

        // Se abre la página para visualizar los votos
        driver.findElement(By.name("B3")).click();
        driver.findElement(By.tagName("a")).click();

        boolean expectedResult = true;
        // Se obtiene la lista de los valores de las votaciones de cada uno de los
        // jugadores
        WebElement tableVotes = driver.findElement(By.tagName("table"));
        ArrayList<WebElement> rows = new ArrayList<>(tableVotes.findElements(By.tagName("tr")));
        // Se elimina la cabecera de las columnas de la tabla
        rows.remove(0);

        int counter = 0;
        boolean verify = false;
        // Comprueba entre los distintos jugadores con votos al jugador al que se le ha
        // dado un voto y se comprueba que ha recibido dicho voto
        while (!verify && counter < rows.size()) {
            List<WebElement> cells = rows.get(counter).findElements(By.tagName("td"));
            // Comprueba que sea el jugador introducido para la votación y mira que tiene un
            // voto
            if (cells.get(1).getText().trim().equals(namePlayer) && cells.get(2).getText().trim().equals("1")) {
                verify = true;
            }
            counter++;
        }

        assertEquals(expectedResult, verify,
                "El jugador no ha recibido el voto.");
    }

}
