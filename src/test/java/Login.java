import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class Login extends BaseTest {
    @Test
    public void testTitle()  {
        int[] list = {1,2,3,4,5,6,7,8,9,10};
        for(int i : list){
            int j = 0;
            if ( i % 2 == 0  ) {
                System.out.println("even is " +  i);
            } else  {
                System.out.println("odd is " + i );
            }
        }

        String currentURL = driver.getTitle();
        Assert.assertEquals(currentURL,"Google");
    }
    @Test
    public void testAPI() {
        String apiURL = "https://dummy.restapiexample.com/api/v1/employees";
        Response response = RestAssured.get(apiURL);
        int statusCode = response.getStatusCode();
        String content = response.getBody().asString();
        JsonPath jsonPath = response.jsonPath();
        List<Integer> ids = jsonPath.getList("data.id");
        System.out.println(content);
        System.out.println("Extracted IDs: " + ids);
        Assert.assertEquals(statusCode, 200, "API did not return status code 200");
    }
    @Test(dataProvider = "excelData", dataProviderClass = ExcelDataProvider.class)
    public void loginTest(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/logged-in-successfully/"));
    }
}

