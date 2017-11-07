package dataModels;

import core.Constants;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import static core.Constants.DOMEN;

/**
 * Created by Asta on 22.03.2017.
 */
public class DataProviders {

    @DataProvider
    public static Object[][] urlsWithoutLogin(){
        return new Object[][]{
                {DOMEN},
                {DOMEN + "new-applicant"},
                {DOMEN + "new-employer"},
                {DOMEN + "employer"},
                {DOMEN + "tips"},
                {DOMEN + "tips-На+какие+вопросы+работодателя+можно+не+отвечать-193"},
                {DOMEN + "?ts=tips"},
                {DOMEN + "vacancies-all"},
                {DOMEN + "ДБ+АО+Банк+Хоум+Кредит-jobs-12"},
                {DOMEN + "Алматы"},
                {DOMEN + "вакансии/It"},
                {DOMEN + "Продавец+консультант-job-694"},
                {DOMEN + "вакансии/Повар"},
                {DOMEN + "kk"},
                {DOMEN + "kk/Алматы"},
        };
    }

    @Parameters("stage")
    @DataProvider
    public static Object[][] urlsApplicant(ITestContext context){
        String resumeId;
        if (Boolean.parseBoolean(context.getCurrentXmlTest().getParameter("stage"))){
            resumeId = Constants.VISUAL_RESUME_ID_DEV;
        }else{
            resumeId = Constants.VISUAL_RESUME_ID;
        }
        return new Object[][]{
                {DOMEN + "applicant/notebook"},
                {DOMEN + "applicant/who_watched"},
                {DOMEN + "resume/InvitedResumesNotebookApplicant"},
                {DOMEN + "response/sendResumeList"},
                {DOMEN + "PickedVacancy/index"},
                {DOMEN + "subscription/index"},
                {DOMEN + "user/Change_email"},
                {DOMEN + "user/Change_password"},
                {DOMEN + "resume/editPreview/id/" + resumeId},
                {DOMEN + "vacancies-all"},
        };
    }

    @Parameters("stage")
    @DataProvider
    public static Object[][] urlsEmployer(ITestContext context){
        String vacancyId, managerId;
        if (Boolean.parseBoolean(context.getCurrentXmlTest().getParameter("stage"))){
            managerId = "1061161";
            vacancyId = "127983";
        }else{
            managerId = "1070953";
            vacancyId = "168868";
        }
        return new Object[][]{
                {DOMEN + "vacancy/list/manager_id/227586/tab/1"},
                {DOMEN + "pickedResume/list"},
                {DOMEN + "Руководитель+отдела-resume-745397?vacancy_id=160102&response_id=5788295&"},
                {DOMEN + "resume-all"},
                {DOMEN + "payment/dbAccess"},
                {DOMEN + "payment/publication"},
                {DOMEN + "payment/branding"},
                {DOMEN + "payment/targetingMailing"},
                {DOMEN + "payment/logoOnTop"},
                {DOMEN + "response/receivedResumeList/vacancy_id/" + vacancyId},
                {DOMEN + "vacancy-edit-" + vacancyId},
                {DOMEN + "profile-user"},
                {DOMEN + "profile-employer"},
                {DOMEN + "employer/managersList"},
                {DOMEN + "employer/editManager/manager_id/" + managerId},
                {DOMEN + "subscription/index"},
                {DOMEN + "payment/index"},
                {DOMEN + "tips-9+привычек+очень+влиятельных+людей-293"}
        };
    }


    @DataProvider
    public static Object[][] wrongEmails() {
        return new Object[][]{
                {""},
                {"qwerty"},
                {"qwerty@"},
                {"qwerty@qwe"},
                {"qwerty@qwe."},
                {"qwerty@.dfsd"},
                {"qwerty.bbb.ddd"},
                {"http://qwerty@mail.ru"},
                {"http://qwerty-mail.ru"},
                {"@mail.ru"},
                {"qwerty.@mail.ru"},
                {"имейл@мейл.ру"}
        };
    }

    @DataProvider
    public static Object[][] wrongPasswords() {
        return new Object[][]{
                {""},
                {" "},
                {"         "},
                {"q"},
                {"qw"},
                {"qwe"},
                {"qwer"},
                {"qwert"},
                {"!@#$%^&*()=-+"}
        };
    }

    @DataProvider
    public static Object[][] wrongUrls() {
        return new Object[][]{
                {"qwerty"},
                {"qwerty.org"},
                {"http://"},
                {"http://../"},
                {"http:/qwerty.org/"},
                {"https//qwerty.org/"},
                {"http://qwerty/org/"},
                {"qwerty@qqq.org"},
                {"http://qwerty"},
                {"http://сайт"},
                {"сайт.ру"},
        };
    }

    @DataProvider
    public static Object[][] companyBrandedPages() {
        return new Object[][]{
                {"Аксай+нан-jobs-31753"},
                {"ТОО+Мечта+Маркет-jobs-45867"},
                {"АО+Элеватормельстрой-jobs-31581"},
                {"АО+Нефтяная+Страховая+Компания+НСК-jobs-25991"},
                {"Europharma+Akniet+Pharmaceuticals-jobs-25555"},
                {"Прима+Distribution+ТОО-jobs-1637"},
                {"ДБ+АО+Банк+Хоум+Кредит-jobs-12"},
                {"ТОО+BI+Holding+БиАй+Холдинг-jobs-51519"},
                {"ТОО+Art+Asia-jobs-42745"},
                {"ТОО+Этажи+Алматы-jobs-48677"},
                {"Интернет+Портал+NURkz-jobs-21015"},
                {"Договор24-jobs-41023"},
                {"ТОО+Эмиль-jobs-2742"},
                {"AO+ТЕХНОДОМ-jobs-2593"}
        };
    }

    @DataProvider
    public static Object[][] cityBrandedPages() {
        return new Object[][]{
                {"Алматы"},
                {"Астана"},
                {"Караганда"},
                {"Шымкент"},
                {"Актау"},
                {"Актобе"},
                {"Атырау"},
                {"Кокшетау"},
                {"Костанай"},
                {"Кызылорда"},
                {"Павлодар"},
                {"Петропавловск"},
                {"Талдыкорган"},
                {"Тараз"},
                {"Уральск"},
                {"Усть+Каменогорск"},
                {"Рудный"},
                {"Семей"},
                {"Темиртау"},
                {"Экибастуз"}
        };
    }

    @DataProvider
    public static Object[][] wrongLoginData() {
        return new Object[][]{
                {"qwerty@qwerty.ru", "qwerty"},
                {Constants.APPLICANT_LOGIN_EMAIL, "qwerty"},
                {"qwerty@qwerty.ru", Constants.PASSWORD},
                {"", Constants.PASSWORD},
                {Constants.APPLICANT_LOGIN_EMAIL, ""}
        };
    }

    @DataProvider
    public static Object[][] subscriptionIntervalSend(){
        return new Object[][]{
                {0},
                {1},
                {2},
        };
    }

    @DataProvider
    public static Object[][] pagesForPickVacancyVerifying() {
        return new Object[][]{
                {Constants.DOMEN + "vacancies-all"},
                {Constants.DOMEN + "searchApplicant/Search"},
                {Constants.DOMEN + "Флирчи-jobs-14157"},
                {Constants.DOMEN + "Алматы"},
                {Constants.DOMEN + "вакансии/Банки+инвестиции+лизинг"}
        };
    }

    @DataProvider
    public static Object[][] pagesForPickResumeVerifying() {
        return new Object[][]{
                {Constants.DOMEN + "resume-all"},
                {Constants.DOMEN + "searchEmployer/Search"},
                {Constants.DOMEN + "резюме-професии/Пилот"},
                {Constants.DOMEN + "резюме/Жаркент"},
                {Constants.DOMEN + "резюме-професии/Автобизнесавтосервис"}
        };
    }
}
