package assertion;

import core.MethodsFactory;
import dataModels.apiModels.v1.MainResponse;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Asta on 23.01.2017.
 * Класс содержит проверки для тестов.
 */
public class Assertion extends MethodsFactory {

    public static void urlContains(String text, int timeout){  // проверка на содержание в URL определенного строкового выражения
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        try{
            wait.until(ExpectedConditions.urlContains(text));
        } catch (TimeoutException e){
            System.out.println("URL doesn't contain " + text + " during " + timeout + " sec.");
            System.out.println("Actual URL: " + driver().getCurrentUrl());
            throw new AssertionError("URL doesn't contain " + text + " during " + timeout + " sec.\n" + "Actual URL: " + driver().getCurrentUrl());
        }
    }
    public static void urlContains(String text, int timeout, String message){ // проверка на содержание в URL определенного строкового выражения с таймаутом
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        try{
            wait.until(ExpectedConditions.urlContains(text));
        } catch (TimeoutException e){
            System.out.println("URL doesn't contain " + text + " during " + timeout + " sec.");
            System.out.println("Actual URL: " + driver().getCurrentUrl());
            throw new AssertionError(message + "ErrorData: URL doesn't contain " + text + " during " + timeout + " sec.\n" + "Actual URL: " + driver().getCurrentUrl());
        }
    }

    public static void urlDoesNtContains(String text, int timeout){  // проверка на отсутствие в URL определенного строкового выражения
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        try{
            wait.until(ExpectedConditions.urlContains(text));
            throw new AssertionError("URL contains " + text + " during " + timeout + " sec.\n" + "Actual URL: " + driver().getCurrentUrl());
        } catch (TimeoutException e){
            System.out.println("URL doesn't contain " + text + " during " + timeout + " sec.");
            System.out.println("Actual URL: " + driver().getCurrentUrl());
        }
    }
    public static void urlDoesNtContains(String text, int timeout, String message){ // проверка на отсутствие в URL определенного строкового выражения с таймаутом
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        try{
            wait.until(ExpectedConditions.urlContains(text));
            throw new AssertionError(message + "ErrorData: URL contains " + text + " during " + timeout + " sec.\n" + "Actual URL: " + driver().getCurrentUrl());
        } catch (TimeoutException e){
            System.out.println("URL doesn't contain " + text + " during " + timeout + " sec.");
            System.out.println("Actual URL: " + driver().getCurrentUrl());
        }
    }

    public static void urlToBe(String text, int timeout){  // проверка на соответствие URL определенному строковому выражению
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        try{
            wait.until(ExpectedConditions.urlToBe(text));
        } catch (TimeoutException e){
            System.out.println("URL is not " + text + " during " + timeout + " sec.");
            System.out.println("Actual URL: " + driver().getCurrentUrl());
            throw new AssertionError("URL is not " + text + " during " + timeout + " sec.\n" + "Actual URL: " + driver().getCurrentUrl());
        }
    }


    public static void assertApiSuccessResponse(MainResponse response){
        if (response.status == null)
            throw new AssertionError("STATUS is empty! Response is invalid!\n" + response);
        if (response.status.equals(0) || response.error.code != 0)
            throw new AssertionError("Response is unsuccessful! STATUS = " + response.status
                    + " (expected 1), ERROR CODE = " + response.error.code + " (expected 0), message: " + response.error.message);
    }

    public static void assertApiFailureResponse(MainResponse response, int expectedErrorCode){
        if (response.status == null || response.error.code == null)
            throw new AssertionError("STATUS or ERROR is empty! Response is invalid!\n" + response);
        if (response.status.equals(1) || !response.error.code.equals(expectedErrorCode))
            throw new AssertionError("Response is successful or has other error code! STATUS = "
                    + response.status + " (expected 0), ERROR CODE = " + response.error.code + " (expected " + expectedErrorCode + ")");
    }
}
