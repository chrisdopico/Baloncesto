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
        System.out.println("Primer test");
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
        System.out.println("Segundo test");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");
        driver.findElement(By.name("B2")).click();
        // driver.findElement(By.tagName("a")).click();
        driver.findElement(By.name("B3")).click();
        WebElement tablaVotos = driver.findElement(By.tagName("table"));
        ArrayList<WebElement> rows = new ArrayList<>(tablaVotos.findElements(By.tagName("tr")));
        rows.remove(0);
        ArrayList<WebElement> listWebElementsVotes = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
            listWebElementsVotes.add(cells.get(2));
        }

        boolean expectedResult = true;
        boolean verify = true;
        int counter = 0;
        while (verify && counter < listWebElementsVotes.size()) {
            if (!listWebElementsVotes.get(counter).getText().trim().equals("0")) {
                verify = false;
            }
            counter++;
        }

        assertEquals(expectedResult, verify);

    }

    @Test
    void setVoteOtherPlayerTest() {
        System.out.println("Tercer test");
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
        driver = new PhantomJSDriver(caps);
        driver.navigate().to("http://localhost:8080/Baloncesto/");

        driver.findElement(By.id("textoOtros")).click();
        String namePlayer = "Brandon Davies";
        driver.findElement(By.name("txtOtros")).sendKeys(namePlayer);
        driver.findElement(By.name("B1")).click();
        // driver.findElement(By.tagName("a")).click();
        driver.findElement(By.name("B3")).click();

        boolean expectedResult = true;
        WebElement tablaVotos = driver.findElement(By.tagName("table"));
        ArrayList<WebElement> rows = new ArrayList<>(tablaVotos.findElements(By.tagName("tr")));
        rows.remove(0);

        int counter = 0;
        boolean verify = false;
        while (!verify && counter < rows.size()) {
            List<WebElement> cells = rows.get(counter).findElements(By.tagName("td"));
            if (cells.get(1).getText().trim().equals(namePlayer) && cells.get(2).getText().trim().equals("1")) {
                verify = true;
            }
            counter++;
        }

        assertEquals(expectedResult, verify);
    }

}
