package Utils;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class ChromeDriverManager {
    private WebDriver driver;

    public ChromeDriverManager() {
        Dimension d = new Dimension(1382, 744);

        try {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().setSize(d);
        } catch (SessionNotCreatedException e) {
            driver = new ChromeDriver();
            driver.manage().window().setSize(d);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void closeDriver() {
        driver.close();
        driver.quit();
    }
}