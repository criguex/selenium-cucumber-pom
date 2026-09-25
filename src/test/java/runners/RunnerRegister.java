package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features= "src/test/resources/features/Register.feature",
        glue = {"stepdefinitions/register"},
        plugin = {"pretty", "html:target/cucumber-reports/report.html",
                "json:target/cucumber.json",
                "junit:target/cucumber-reports/cucumber.xml"
        },
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        monochrome = false
)

public class RunnerRegister {
}
