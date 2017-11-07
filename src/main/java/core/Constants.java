package core;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alex Astakhov on 27.11.2016.
 */
public class Constants {
    public static String DOMEN;
    public static String HOST;
    public static boolean BROWSER_LOG;
    static final int PAGE_LOAD_TIMEOUT = 300;
    static final int IMPLICITLY_WAIT_TIMEOUT = 5;
    static final int IMPLICITLY_WAIT_TIMEOUT_FOR_FRAME = 20;
    static final long TIMEOUT_FOR_ELEMENT_DISAPPEARING = 5000;
    public static final long TIMEOUT_FOR_EMAIL = 120000;
    public static final String APPLICANT_REG_EMAIL = "deleted";
    public static final String APPLICANT_LOGIN_EMAIL = "deleted";
    public static final String APPLICANT_VISUAL_TEST_EMAIL = "deleted";
    public static final String APPLICANT_API_TEST_EMAIL = "deleted";
    public static final String PASSWORD = "deleted";

    public static final String ADMIN_EMAIL = "deleted";
    public static final String ADMIN_PASSWORD = "deleted";

    public static final String DEV_EMAIL = "deleted";
    public static final String DEV_PASSWORD = "deleted";

    public static final String APPLICANT_MAIL_RU_EMAIL = "deleted";
    public static final String APPLICANT_SOCIAL_NET_FIRST_NAME = "deleted";
    public static final String APPLICANT_SOCIAL_NET_LAST_NAME = "deleted";

    public static final String EMPLOYER_EMAIL = "deleted";
    public static final String EMPLOYER_PASSWORD = "deleted";
    public static final String EMPLOYER_ID = "deleted";
    public static final String EMPLOYER_VISUAL_MANAGER = "deleted";
    public static final String EMPLOYER_MANAGER = "deleted";
    public static final String MANAGER_EMAIL_TO_ADD = "deleted";
    public static final String EMPLOYER_BOSS = "deleted";
    public static final String CRM_MANAGER = "deleted";

    public static final By CSS_POP_UP_SAVE = By.cssSelector("[data-test=save-popup]");
    public static final By CSS_POP_UP_CANCEL = By.cssSelector("[data-test=cancel-popup]");

    public static final String SCREENSHOT_PATH = "C:\\screenshots";
    public static final int APPLICANT_RESUME_ID = 1414327;
    public static final int APPLICANT_RESUME_ID_DEV = 1151274;
    public static final String VISUAL_RESUME_ID = "1192929";
    public static final String VISUAL_RESUME_ID_DEV = "1147002";

    public static final By POPUP_CONTENT = By.cssSelector("[data-test=popup-content]");

    public static final String API_REG_ID = "AutoTest";
    public static final String API_VERSION = "v1";
    public static final int API_RESUME_ID_DEV = 0;
    public static final int API_RESUME_ID_PROD = 0;
    public static final int API_RESUME_ID_DEV_FOR_EDIT = 0;
    public static final int API_RESUME_ID_PROD_FOR_EDIT = 0;


    public static final List<String> CITIES_WITH_REGIONS = new ArrayList<>(Arrays.asList("26402", "26403", "26313", "26394"));
}
