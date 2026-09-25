package hooks;

import drivers.BasesClassDriver;
import pages.HomePage;
import pages.LoginPage;
import test_data.LoginData;

public class Context extends BasesClassDriver {
    LoginPage loginPage;

    public Context() {
        setup();
    }

    public void login()
    {

        loginPage = new LoginPage(driver);
        loginPage.fillLoginForm(LoginData.USERNAME, LoginData.PASSWORD);
    }
}
