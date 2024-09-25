import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    @BeforeTest
        public void setUp() throws IOException {
        //Chrome driver
        WebDriverManager.chromedriver().setup();
        //Set up chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //Navigate to website
        driver.manage().window().maximize();
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream("src/test/resource/variables");
        properties.load(in);
        in.close();

        String url = properties.getProperty("url");
        driver.get(url);


    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
