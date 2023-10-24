package Steps;

import Pages.LoginPage;
import Pages.ProfilePage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class StepDefinition  {


    public static  WebDriver driver;
    public LoginPage loginPage;
    public ProfilePage profilePage;

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        loginPage= new LoginPage();
        profilePage = new ProfilePage(); }


        @Given("User is on login page")
    public void userIsOnLoginPage() {

    driver.navigate().to("https://the-internet.herokuapp.com/login");

   }


    @When("User inputs valid username {string}")
    public void user_inputs_username(String username) {
        loginPage.inputUsername(username);
    }

  /*  @When("User inputs username")
    public void userInputsUsername() {
       loginPage.inputUsername("tomsmith");

    } */

    @And("User inputs valid password {string}")
    public void user_inputs_password(String password) {
        loginPage.inputPassword(password);
    }


 /*   @And("User inputs password")
    public void userInputsPassword() {
        loginPage.inputPassword("SuperSecretPassword!");

    } */

    @And("User clicks on Login button")
    public void userClicksOnLoginButton (){
       loginPage.clickOnLoginButton();

    }


    @Then("User is logged in")
    public void userIsLoggedin() {

        Assert.assertTrue(profilePage.logOutButton.isDisplayed());

    }

    @Then("User is not logged in")
    public void user_is_not_logged_in() {
        Assert.assertTrue(loginPage.error.isDisplayed());
        Assert.assertEquals(loginPage.error.getText(), "Your username is invalid!\n" + "×");
    }


}
