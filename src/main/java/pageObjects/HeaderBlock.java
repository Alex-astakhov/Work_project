package pageObjects;

import core.Constants;
import core.MethodsFactory;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Asta on 14.02.2017.
 */
public class HeaderBlock extends MethodsFactory{

    private By managerName = By.cssSelector(".b-personal-manager__text-name");
    private By managerCart = By.cssSelector(".b-personal-manager__content");
    private By userMenu = By.cssSelector("[data-test=user-menu-action]");
    private By menuCVs = By.cssSelector("[data-test=menu-resume-link]");
    private By menuSubscription = By.cssSelector("[data-test=menu-my-mailing]");
    private By menuChangeEmail = By.cssSelector("[data-test=menu-cnange-email]");
    private By menuChangePassword = By.cssSelector("[data-test=menu-change-password]");
    private By menuEmployerVacancies = By.cssSelector("#menu_my_vacancies_list");
    private By menuEmployerUserProfile = By.cssSelector("#menu_employer_user_profile");
    private By menuEmployerCompanyProfile = By.cssSelector("#menu_employer_company_profile");
    private By menuEmployerManagers = By.cssSelector("#menu_my_managers");
    private By menuEmployerMailing = By.cssSelector("#menu_employer_mainling");
    private By menuEmployerServices = By.cssSelector("#menu_employer_services");
    private By menuEmployerCart = By.cssSelector("#menu_employer_cart");
    private By menuEmployerActiveServices = By.cssSelector("#menu_active_services");
    private By menuExit = By.cssSelector("#menu_exit");

    @Step("Open resumes page")
    public void openCVs(){
        clickUserMenu();
        clickCVs();
    }

    @Step("Open cart pop-up")
    public void openCartPopup(){
        clickUserMenu();
        clickMenuCart();
    }

    @Step("Open subscriptions")
    public void openSubscription(){
        clickUserMenu();
        clickSubscription();
    }

    @Step("Open email changing page")
    public void openEmailChange(){
        clickUserMenu();
        clickChangeEmail();
    }

    @Step("Open password changing page")
    public void openPasswordChange(){
        clickUserMenu();
        clickChangePassword();
    }

    @Step("Open vacancies page")
    public void openVacancies(){
        clickUserMenu();
        clickMyVacancies();
    }

    @Step("Open user profile page")
    public void openUserProfile(){
        clickUserMenu();
        clickUserProfile();
    }

    @Step("Open company profile page")
    public void openCompanyProfile(){
        clickUserMenu();
        clickCompanyProfile();
    }

    @Step("Open managers page")
    public void openManagers(){
        clickUserMenu();
        clickManagers();
    }

    @Step("Open mailings page")
    public void openMailing(){
        clickUserMenu();
        clickMailing();
    }

    @Step("Open Services page")
    public void openServices(){
        clickUserMenu();
        clickServices();
    }

    @Step("Open active vacancies page")
    public void openActiveSrvices(){
        clickUserMenu();
        clickActiveServices();
    }

    @Step("Logout")
    public void logout(){
        waitForPageLoadedWithJQuery();
        clickUserMenu();
        clickMenuExit();
        waitForUrlToBe(Constants.DOMEN);
        waitForPageLoadedWithJQuery();
    }

    @Step("Click at manager")
    public void clickManagerName(){
        click(managerName);
        waitForElementVisibility(managerCart);
    }

    @Step("Click user menu")
    public void clickUserMenu(){
        waitForContentLoaded(500);
        click(userMenu);
    }

    @Step("Menu click RESUMES")
    public void clickCVs(){
        click(menuCVs);
    }

    @Step("Menu click CART")
    public void clickMenuCart(){
        click(menuEmployerCart);
    }

    @Step("Menu click LOGOUT")
    public void clickMenuExit(){
        click(menuExit);
    }

    @Step("Menu click SUBSCRIPTIONS")
    public void clickSubscription(){
        click(menuSubscription);
    }

    @Step("Menu click CHANGE EMAIL")
    public void clickChangeEmail(){
        click(menuChangeEmail);
    }

    @Step("Menu click CHANGE PASSWORD")
    public void clickChangePassword(){
        click(menuChangePassword);
    }

    @Step("Menu click MY VACANCIES")
    public void clickMyVacancies(){
        click(menuEmployerVacancies);
    }

    @Step("Menu click USER PROFILE")
    public void clickUserProfile(){
        click(menuEmployerUserProfile);
    }

    @Step("Menu click COMPANY'S PROFILE")
    public void clickCompanyProfile(){
        click(menuEmployerCompanyProfile);
    }

    @Step("Menu click MANAGERS")
    public void clickManagers(){
        click(menuEmployerManagers);
    }

    @Step("Menu click MAILING")
    public void clickMailing(){
        click(menuEmployerMailing);
    }

    @Step("Click SERVICES")
    public void clickServices(){
        click(menuEmployerServices);
    }

    @Step("Click ACTIVE SERVICES")
    public void clickActiveServices(){
        click(menuEmployerActiveServices);
    }

}
