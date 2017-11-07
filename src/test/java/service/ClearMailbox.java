package service;

import core.Constants;
import core.Email;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;

/**
 * Created by Asta on 09.02.2017.
 */
@Features("Clear mailbox")
public class ClearMailbox {

    @Test
    public void clear() throws InterruptedException {
        new Email(Constants.DEV_EMAIL, Constants.DEV_PASSWORD).clearMailBox();
        new Email(Constants.APPLICANT_LOGIN_EMAIL, Constants.PASSWORD).clearMailBox();
        new Email(Constants.APPLICANT_REG_EMAIL, Constants.PASSWORD).clearMailBox();
        new Email(Constants.EMPLOYER_MANAGER, Constants.PASSWORD).clearMailBox();
    }
}
