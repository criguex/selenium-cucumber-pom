package stepdefinitions.login;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import hooks.Context;
import test_data.LoginData;


public class StepDefinitionsLogin {
    Context context;
    LoginPage loginPage;
    HomePage homePage;


    public StepDefinitionsLogin(Context context) {
        this.context = context;
        this.loginPage = new LoginPage(context.driver);
        this.homePage = new HomePage(context.driver);
    }


    @Given("user is on the login page of the site")
    public void userIsOnTheLoginPageOfTheSite() {
        context.setup();
    }
    @When("enters the credentials")
    public void entersTheCredentials() {
        loginPage.fillLoginForm(LoginData.USERNAME, LoginData.PASSWORD);
    }
    @Then("it shows the user profile and your home page")
    public void itShowsTheUserProfileAndYourHomePage() {
        homePage.checkTextUserName();
//        homePage.clickOnCreated();
    }

}
