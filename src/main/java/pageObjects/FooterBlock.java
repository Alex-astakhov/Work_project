package pageObjects;

import core.MethodsFactory;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

/**
 * Created by Asta on 21.12.2016.
 */
public class FooterBlock extends MethodsFactory {
    private By mobileVersionLink = By.id("footer_switch_to_mobile");
    private By desktopVersionLink = By.id("redirect_to_desktop_link");

    @Step("Click MOBILE version")
    public void clickMobileVersion(){
        click(mobileVersionLink);
    }

    @Step("Click DESKTOP version")
    public void clickDesktopVersion(){
        click(desktopVersionLink);
    }
}
