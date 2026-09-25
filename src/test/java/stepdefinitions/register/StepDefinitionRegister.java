package stepdefinitions.register;

import hooks.Context;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CreateCostumerPage;
import pages.HomePage;

public class StepDefinitionRegister {
    Context context;
    HomePage homePage;
    CreateCostumerPage createCostumerPage;


    public StepDefinitionRegister(Context context) {
        this.context = context;
        this.homePage = new HomePage(context.driver);
        this.createCostumerPage = new CreateCostumerPage(context.driver);
        context.login();
    }

    @After
    public void tearDown() {
        if (context.driver != null) {
            context.driver.quit();
        }
    }

    @Given("User is on the login page of the site and click link create")
    public void userIsOnTheLoginPageOfTheSiteAndClickLinkCreate() {
        homePage.clickOnCreatedCustomer();
    }
    @When("User clicks on create button and select costumers")
    public void userClicksOnCreateButtonAndSelectCostumers() {
        createCostumerPage.fillBasicData();
        createCostumerPage.fillContact();
    }
    @And("enters the info in the third party creation form")
    public void entersTheInfoInTheThirdPartyCreationForm() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("it shows a success message")
    public void itShowsASuccessMessage() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
