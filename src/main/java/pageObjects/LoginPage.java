package pageObjects;

import core.Constants;
import core.MethodsFactory;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Alex Astakhov on 25.11.2016.
 */
public class LoginPage extends MethodsFactory {
    private String url = Constants.DOMEN + "login";
    private By emailField = By.id("Users_email");
    private By passwordField = By.id("Users_password");
    private By submitButton = By.cssSelector("[name=yt0]");


    @Step("Get loginUser page")
    public void getPage(){
        get(url);
    }

    @Step("Login with email: {0} and password: {1}")
    public void loginUser(String email, String password) {
        typeEmail(email);
        typePassword(password);
        click(submitButton);
    }

    @Step("Email with email: {0} and password: {1}")
    public void loginAdmin(String email, String password) {
        type(emailField, email);
        type(passwordField, password);
        click(submitButton);
        waitForUrlContains("admin");
    }

    @Step("Type email: {0}")
    public void typeEmail(String email){
        type(emailField, email);
    }

    @Step("Type password: {0}")
    public void typePassword(String password){
        type(passwordField, password);
    }

}
