package core;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.config.AllureModelUtils;
import ru.yandex.qatools.allure.events.TestCaseEvent;
import ru.yandex.qatools.allure.model.TestCaseResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import static com.google.common.io.Files.toByteArray;

/**
 * Created by Alex Astakhov on 21.05.2016.
 * Класс содержит методы общего назначения, такие как клик, выбор из селекта, различные ожидания событий, ввод в инпут и т.д.
 * От него наследуютсяс классы, описывающие страницы (пакет pageObjects) и BrowserFactory, в котором инициализируется сам браузер.
 */
public class MethodsFactory {

    static ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();
    public WebDriver driver;
    private Logger logger = LoggerFactory.getLogger("WebDriver");

    protected static WebDriver driver(){// метод возвращает переменную вебдрайвера в параллельном контексте
        return DRIVER.get();            // это нужно на случай параллельного запуска тестов в нескольких браузерах
    }

    @Step("Type to {0} field {1}")
    protected void type(By locator, String s){    // ввод текста в текстовое поле
        logger.info("Typing by '" + locator + "' <" + s + ">...");
        waitForElementVisibility(locator);
        logger.info("   Clear field" );
        driver().findElement(locator).clear();
        logger.info("   Send keys");
        driver().findElement(locator).sendKeys(s);
    }

    @Step("Type to {0} field {1}")
    protected void type(WebElement w, String s){   // ввод текста в текстовое поле
        logger.info("Typing by '" + w + "' " + s + "...");
        logger.info("   Clear field" );
        w.clear();
        logger.info("   Send keys - '" + s + "'");
        w.sendKeys(s);
    }

    @Step("Get current URL")
    public String getCurrentUrl(){
        waitForPageToBeLoaded();
        return driver().getCurrentUrl();
    } // возвращает текущще значение URL

    @Step("Scroll to element {0}")
    private void scrollToElement(By by){    // прокрутка страницы к элементу
        logger.info("   Scroll to element '" + by + "'");
        ((JavascriptExecutor)driver()).executeScript("arguments[0].scrollIntoView();", driver().findElement(by));
    }
    @Step("Scroll to element {0}")
    private void scrollToElement(WebElement element){   // прокрутка страницы к элементу
        logger.info("   Scroll to element '" + element + "'");
        ((JavascriptExecutor)driver()).executeScript("arguments[0].scrollIntoView();", element);
    }

    @Step("Click {0}")
    protected void click(By by){     // клик на элементе
        logger.info("Clicking on locator '" + by + "'...");
        waitForElementPresence(by);
        scrollToElement(by);
        logger.info("   Click - " + by);
        driver().findElement(by).click();
    }

    @Step("Click {0}")
    protected void click(WebElement element){    // клик на элементе
        logger.info("Clicking on locator '" + element + "'...");
        //waitForElementVisibility(element);
        scrollToElement(element);
        logger.info("   Click - " + element);
        element.click();
    }

    @Step("Focus on element")
    public void focusOnElement(By by){    // фокус на элементе
        logger.info("Focus on locator '" + by + "'...");
        JavascriptExecutor jse = (JavascriptExecutor) driver();
        waitForElementPresence(by);
        WebElement element = driver().findElement(by);
        logger.info("   Set focus on element");
        jse.executeScript("arguments[0].focus();", element);
    }

    @Step("Get URL: {0}")
    protected void get(String url){    // переход по URL
        logger.info("Get URL '" + url + "'");
        driver().get(url);
        waitForPageToBeLoaded();
    }

    @Step("Refresh page")
    public void refreshPage(){     // обноление страницы
        logger.info("Refresh page");
        driver().navigate().refresh();
        waitForPageToBeLoaded();
    }

    @Step("Get attribute {1} of element {0}")
    protected String getAttributeOfElement(By locator, String attrName){   // возвращает значение атрибута элемента
        logger.info("   Get attribute '" + attrName + "' at locator '" + locator + "'");
        return driver().findElement(locator).getAttribute(attrName);
    }
    @Step("Get attribute {1} of element {0}")
    protected String getAttributeOfElement(WebElement element, String attrName){   // возвращает значение атрибута элемента
        logger.info("   Get attribute '" + attrName + "' at element '" + element + "'");
        return element.getAttribute(attrName);
    }

    @Step("Get attribute 'value' of element {0}")
    protected String getValueAttrOfElement(By locator){    // возвращает значение атрибута "value"
        logger.info("   Get attribute 'value' at locator '" + locator + "'");
        return driver().findElement(locator).getAttribute("value");
    }

    @Step("Get attribute 'value' of element {0}")
    protected String getValueAttrOfElement(WebElement element){   // возвращает значение атрибута "value"
        logger.info("   Get attribute 'value' at element '" + element + "'");
        return element.getAttribute("value");
    }

    @Step("Get text by locator {0}")
    protected String getTextByLocator(By by){     // возвращает текст со страницы из указанного элемента
        logger.info("   Get text at locator '" + by + "'");
        return driver().findElement(by).getText();
    }

    @Step("Get text from web element {0}")
    protected String getTextFromWebElement(WebElement element){  // возвращает текст со страницы из указанного элемента
        logger.info("   Get text at element '" + element + "'");
        return element.getText();
    }

    @Step("Wait for visibility of element {0}")
    protected void waitForElementVisibility(By by){     // ожидание видимости элемента на странице
        WebDriverWait wait = new WebDriverWait(driver(), 10);
        logger.info("   Wait for element visibility at locator '" + by + "'");
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    @Step("Wait for visibility of element {0}")
    protected void waitForElementVisibility(WebElement element){   // ожидание видимости элемента на странице
        WebDriverWait wait = new WebDriverWait(driver(), 10);
        logger.info("   Wait for element visibility '" + element + "'");
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Step("Wait for invisibility of element {0}")
    protected void waitForElementInvisibility(By by){    // ожидание невидимости элемента на странице
        WebDriverWait wait = new WebDriverWait(driver(), 3);
        logger.info("   Wait for element invisibility at locator '" + by + "'");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    @Step("Get all web elements by locator {0}")
    protected List<WebElement> getAllElements(By by){    // возвращает лист всех элементов по указанному локатору
        logger.info("Getting all elements by locator '" + by + "'");
        waitForPageToBeLoaded();
        logger.info("   Find all elements");
        return driver().findElements(by);
    }

    @Step("Wait for element disappear by locator {0} with timeout {1}")
    private void waitForElementDisappear(By by, long timeout) {  // ожидание исчезновения элемента со страницы
        logger.info("Wait for element by locator '" + by + "' disappears with timeout " + timeout + " millis");
        long start = Calendar.getInstance().getTimeInMillis();
        long finish = start + timeout;
        while (start < finish){
            if (driver().findElements(by).size() == 0) {
                return;
            }
            start = Calendar.getInstance().getTimeInMillis();
        }
        throw new TimeoutException("Element " + by + " didn't disappear during " + timeout + " milliseconds!");
    }

    @Step("Wait for preloader disappearing")
    protected void waitForPreloaderDisappear(long millis){    // ожидание исчезновения прелоадера
        delay(500);
        waitForElementDisappear(By.cssSelector(".b-preload-block.load"), millis);
    }

    @Step("Wait for element disappear by locator {0} with default timeout")
    protected void waitForElementDisappear(By by){     // ожидание исчезновения элемента со страницы
        logger.info("Wait for element by locator '" + by + "' disappears");
        long start = Calendar.getInstance().getTimeInMillis();
        long finish = start + Constants.TIMEOUT_FOR_ELEMENT_DISAPPEARING;
        while (start < finish) {
            if (driver().findElements(by).size() == 0) {
                return;
            }
            start = Calendar.getInstance().getTimeInMillis();
        }
        throw new TimeoutException("Element " + by + " didn't disappear during " + Constants.TIMEOUT_FOR_ELEMENT_DISAPPEARING + " milliseconds!");
    }

    @Step("Wait for presence of element {0}")
    protected void waitForElementPresence(By by){    // ожидание появления элемента на странице
        logger.info("   Wait for presence of element by locator '" + by + "'");
        WebDriverWait wait = new WebDriverWait(driver(), Constants.IMPLICITLY_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Step("Wait for presence of element {0} with timeout {1}")
    protected void waitForElementPresence(By by, int timeout){   // ожидание появления элемента на странице
        logger.info("   Wait for presence of element by locator '" + by + "' with timeout " + timeout + "sec.");
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    @Step("Wait for URL to contain: {0}")
    protected void waitForUrlContains(String text){    // ожидание условия, при котором URL содержит определенную строку
        logger.info("Wait for URL contains '" + text + "'");
        WebDriverWait wait = new WebDriverWait(driver(), 5);
        wait.until(ExpectedConditions.urlContains(text));
    }

    @Step("Wait for URL to contain: {0} during {1} sec.")
    protected void waitForUrlContains(String text, int timeout){   // ожидание условия, при котором URL содержит определенную строку
        logger.info("Wait for URL contains '" + text + "' with timeout" + timeout + "sec.");
        WebDriverWait wait = new WebDriverWait(driver(), timeout);
        wait.until(ExpectedConditions.urlContains(text));
    }

    @Step("Wait for URL to be: {0}")
    protected void waitForUrlToBe(String text){   // ожидание условия, при котором URL равен определенной строке
        logger.info("Wait for URL to be '" + text + "'");
        WebDriverWait wait = new WebDriverWait(driver(), 5);
        wait.until(ExpectedConditions.urlToBe(text));
    }
    @Step("Wait for elements {0} count become lower than {1}")
    protected void waitForAmountReduction(By by, int beginAmount) {  // ожидание уменьшения количества указанных элементов на странице
        logger.info("Wait for elements located by '" + by + "' amount becomes lower then " + beginAmount);
        WebDriverWait wait = new WebDriverWait(driver(), 5);
        try {
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(by, beginAmount));
        } catch (TimeoutException e){
            System.out.println("Exception waiting for amount to be less than " + beginAmount);
        }
    }

    @Step("Wait for elements {0} count become higher than {1}")
    protected void waitForAmountIncrease(By by, int beginAmount){  // ожидание увеличения количества указанных элементов на странице
        logger.info("Wait for elements located by '" + by + "' amount becomes more then " + beginAmount);
        WebDriverWait wait = new WebDriverWait(driver(), 3);
        try {
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, beginAmount));
        } catch (TimeoutException e){
            System.out.println("Amount of elements <" + by + "> wasn't increased!");
            throw e;
        }
    }

    @Step("Wait for element {0} to be clickable")
    protected void waitForElementIsClickable(By by, int sec){  // ожидание кликабельности элемента
        logger.info("   Wait for element at '" + by + "' to be clickable");
        WebDriverWait wait = new WebDriverWait(driver(), sec);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    @Step("Wait for page to be completely loaded")
    public void waitForPageToBeLoaded(){    // ожидание полной загрузки страницы (ReadyState=Complete)
        WebDriverWait wait = new WebDriverWait(driver(), 30);
        logger.info("   Wait for page to be loaded (ReadyState=complete)");
        try {
            wait.until(pageLoaded());
            delay(200);
            return;
        } catch (TimeoutException e) {
            logger.info("----------- <<< ERROR: PAGE WASN'T LOADED AFTER " + 30 + " SECONDS !!! >>> -----------");
            driver().navigate().refresh();
        }
        wait = new WebDriverWait(driver(), Constants.PAGE_LOAD_TIMEOUT);
        try {
            wait.until(pageLoaded());
        } catch (TimeoutException e) {
            logger.info("----------- <<< ERROR: PAGE WASN'T LOADED AFTER " + Constants.PAGE_LOAD_TIMEOUT + " SECONDS !!! >>> -----------");
            e.printStackTrace();
        }
    }

    @Step("Sleep for {0} millis")
    protected void delay(long millis){
        logger.info("   Sleep for " + millis + " millis");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ExpectedCondition<Boolean> contentLoaded(){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return !((JavascriptExecutor) driver()).executeScript("" +
                        "try{window.performance = window.performance || window.mozPerformance || window.msPerformance || window.webkitPerformance || {};" +
                        "return(window.performance.timing.domContentLoadedEventEnd);}catch(e){alert(e);}").toString().isEmpty();
            }

            @Override
            public String toString() {
                return "page ready state = complete!";
            }
        };
    }

    @Step("Wait for page content to be loaded + timeout after load {0} millis")
    public void waitForContentLoaded(long millisAfterLoad){  // ожидание полной загрузки контента с таймаутом после загрузки
        try {
            logger.info("Waiting for content to be loaded with timeout after loading - " + millisAfterLoad + " millis");
            WebDriverWait wait = new WebDriverWait(driver(), Constants.PAGE_LOAD_TIMEOUT);
            waitForPageToBeLoaded();
            try {
                wait.until(contentLoaded());
            } catch (WebDriverException ignored) {}
            try {
                Thread.sleep(millisAfterLoad);
                waitForPageToBeLoaded();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (TimeoutException e){
            logger.info("----------- <<< ERROR: PAGE WASN'T LOADED AFTER " + Constants.PAGE_LOAD_TIMEOUT + " SECONDS !!! >>> -----------");
            e.printStackTrace();
        }
    }

    @Step("Wait for page to be loaded with jQuery")
    public void waitForPageLoadedWithJQuery(){   // ожидание полной загрузки страницы с jQuery
        logger.info("Wait for page to be loaded with jQuery");
        WebDriverWait wait = new WebDriverWait(driver(), Constants.PAGE_LOAD_TIMEOUT);
        waitForPageToBeLoaded();
        try {
            wait.until(jQueryLoaded());
        } catch (WebDriverException ignored){}
    }

    private static ExpectedCondition<Boolean> pageLoaded(){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return ((JavascriptExecutor) driver()).executeScript("return document.readyState").equals("complete");
            }

            @Override
            public String toString() {
                return "Page is still loading...";
            }
        };
    }

    private static ExpectedCondition<Boolean> jQueryLoaded(){
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                return ((long) ((JavascriptExecutor) driver()).executeScript("return jQuery.active") == 0);
            }

            @Override
            public String toString() {
                return "Page is still loading...";
            }
        };
    }

    @Step("Accept alert")
    protected boolean acceptAlert(){   // согласие на браузерный алерт
        logger.info("Accept alert");
        WebDriverWait wait = new WebDriverWait(driver(), 3);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver().switchTo().alert();
            alert.accept();
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    @Step("Get list of options from select {0}")
    protected List<String> getAllOptionsValuesFromSelect(By listLocator){  // возвращает список опций из селекта
        logger.info("Get list of options from SELECT");
        Select select = new Select(driver().findElement(listLocator));
        List<WebElement> options = select.getOptions();
        List<String> values = new ArrayList<>();
        for (WebElement e: options) {
            values.add(getValueAttrOfElement(e));
        }
        return values;
    }

    @Step("Select from droplist {0} by index {1}")
    protected void selectionFromDropListByIndex(By listLocator, int index){ // выбор опции в селекте по порядковому номеру
        logger.info("Select option with index '" + index +"' in SELECT located by '" + listLocator + "'");
        Select select = new Select(driver().findElement(listLocator));
        select.selectByIndex(index);
    }

    @Step("Select from droplist {0} by index {1}")
    protected void selectionFromDropListByIndex(WebElement listElement, int index){  // выбор опции в селекте по порядковому номеру
        logger.info("Select option with index '" + index +"' in SELECT '" + listElement + "'");
        Select select = new Select(listElement);
        select.selectByIndex(index);
    }

    @Step("Select from droplist {0} by value {1}")
    protected void selectionFromDropListByValue(By listLocator, String value){  // выбор опции в селекте по значению атрибута "value"
        logger.info("Select option with value '" + value +"' in SELECT located by '" + listLocator + "'");
        waitForElementPresence(listLocator);
        scrollToElement(listLocator);
        Select select = new Select(driver().findElement(listLocator));
        select.selectByValue(value);
    }

    @Step("Select from droplist {0} by value {1}")
    protected void selectionFromDropListByValue(WebElement listElement, String value){  // выбор опции в селекте по значению атрибута "value"
        logger.info("Select option with value '" + value +"' in SELECT '" + listElement + "'");
        scrollToElement(listElement);
        Select select = new Select(listElement);
        select.selectByValue(value);
    }

    @Step("Get selected item from select {0}")
    protected WebElement getSelectedItem(By listLocator){  // возвращает выбранную опцию в селекте
        logger.info("   Get selected option from SELECT at '" + listLocator + "'");
        Select select = new Select(driver().findElement(listLocator));
        return select.getFirstSelectedOption();
    }
    @Step("Get selected item from select {0}")
    protected WebElement getSelectedItem(WebElement element){  // возвращает выбранную опцию в селекте
        logger.info("   Get selected option from SELECT at '" + element + "'");
        Select select = new Select(element);
        return select.getFirstSelectedOption();
    }
    @Step("Get value of selected item from select {0}")
    protected String getValueOfSelectedItem(By listLocator){  // возвращает атрибут "value" выбранной опции в селекта
        logger.info("Get value of selected option from SELECT at '" + listLocator + "'");
        return getValueAttrOfElement(getSelectedItem(listLocator));
    }

    @Step
    public static String getUserAgent(){  // возвращает текущий юзер агент
        return (String) ((JavascriptExecutor) driver()).executeScript("return navigator.userAgent;");
    }


    @Step("Verify presence of element {0} with default timeout")
    protected boolean presenceOfElement(By by){   // проверка наличия элемента на странице
        logger.info("   Verify if element at '" + by + "' is present");
        return driver().findElements(by).size() > 0;
    }

    @Step("Verify presence of element {0} with timeout {0}")
    protected boolean presenceOfElement(By by, int timeout){  // проверка наличия элемента на странице
        logger.info("   Verify if element at '" + by + "' is present with timeout " + timeout + "sec.");
        try {
            waitForElementPresence(by, timeout);
        } catch (TimeoutException e){
            System.out.println("Element didn't appear.");
        }
        return driver().findElements(by).size() > 0;
    }

    @Step("Get amount of elements with locator {0}")
    protected int getAmountOfElements(By by){  // возвращает кличество элементов на странице по локатору
        logger.info("Get amount of elements located by '" + by + "'");
        waitForPageToBeLoaded();
        return driver().findElements(by).size();
    }

    @Step("Get amount of elements with locator {0}")
    protected int getAmountOfElements(By by, long millis){  // возвращает кличество элементов на странице по локатору после таймаута
        logger.info("Get amount of elements located by '" + by + "' with timeout " + millis + " millis");
        waitForPageToBeLoaded();
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver().findElements(by).size();
    }

    @Step("Verify is element located by {0} has attribute 'checked'")
    protected boolean isChecked(By by){   // проверка отмечен ли чекбокс
        logger.info("Verify if checkbox at '" + by + "' is checked");
        return driver().findElement(by).getAttribute("checked") != null;
    }

    @Step("Uncheck checkbox {0}")
    protected void uncheck(By by, By checkAttr){  // снять чеккер с чекбокса, если он установлен
        logger.info("Uncheck checbox at '" + by + "' if it's checked by attribute '" + checkAttr + "'");
        if (isChecked(checkAttr))
            click(by);
    }

    @Step("Switch to iframe {0}")
    protected void switchToFrame(String frameName){  // передача управления на фрейм
        logger.info("   Switch to frame '" + frameName + "'");
        waitForPageToBeLoaded();
        waitForElementPresence(By.cssSelector("iframe"), Constants.IMPLICITLY_WAIT_TIMEOUT_FOR_FRAME);
        driver().switchTo().frame(frameName);
    }

    @Step("Default switch to iframe")
    private void switchToFrame(){ // передача управления на фрейм (дефолтный)
        logger.info("   Switch to default iframe");
        waitForPageToBeLoaded();
        waitForElementPresence(By.cssSelector("iframe"), Constants.IMPLICITLY_WAIT_TIMEOUT_FOR_FRAME);
        waitForElementVisibility(By.cssSelector("iframe"));
        driver().switchTo().frame(driver().findElement(By.cssSelector("iframe")));
    }

    @Step("Switch to default content")
    protected void switchToDefaultContent(){  // возврат управления на страницу с фрейма
        logger.info("   Switch to default content");
        driver().switchTo().defaultContent();
    }

    @Step("Type {0} in tinymce")
    protected void typeInTinymce(String text){  // ввод текста в tinyMCE
        logger.info("Type '" + text + "' to tinymce");
        waitForElementPresence(By.cssSelector("[aria-hidden]"));
        delay(500);
        switchToFrame();
        type(By.cssSelector("#tinymce"), text);
        switchToDefaultContent();
    }
    @Step("Get text from tinymce")
    protected String getTextFromTinymce(){
        logger.info("Get text from tinymce");
        waitForContentLoaded(500);
        switchToFrame();
        String result = getTextByLocator(By.cssSelector("#tinymce")).replace("\n", "").trim();
        switchToDefaultContent();
        return result;
    }

    @Step("Scroll to element {1} in popup {0}")
    protected void scrollToElementOnPopup(By popup, By element){  // скролл к элементу внутри попапа
        logger.info("Scroll to element at '" + element + "' on pop-up '" + popup + "'");
        WebElement popupElem = driver().findElement(popup);
        WebElement elementTo = driver().findElement(element);
        JavascriptExecutor js = (JavascriptExecutor) driver();
        js.executeScript("arguments[0].scrollTop = arguments[1].getBoundingClientRect().top - arguments[0].getBoundingClientRect().top",
                popupElem, elementTo);
    }

    @Step("Hide element {0} with JS")
    protected void hideElement(By by){  // скруть элемент
        logger.info("   Hide element at '" + by + "'");
        try {
            WebElement element = driver().findElement(by);
            JavascriptExecutor js = (JavascriptExecutor) driver();
            js.executeScript("arguments[0].style.display = 'none';", element);
        } catch (NoSuchElementException e){
            System.out.println("Can't find element!");
        }
    }

    @Step("Hide all banners")
    protected void hideAllBanners(){
        logger.info("Hide all banners");
        hideElements(By.cssSelector(".b-banner"));
    }

    @Step("Hide all elements with locator {0}")
    protected void hideElements(By by){  // скрыть все элементы по локатору
        logger.info("   Hide all element located by '" + by + "'");
        List<WebElement> elements = driver().findElements(by);
        JavascriptExecutor js = (JavascriptExecutor) driver();
        for (WebElement element: elements) {
            js.executeScript("arguments[0].style.display = 'none';", element);
        }
    }

    @Step("Hide all elements with locator {0}")
    protected void hideElements(List<By> locatorsList){  // скрыть все элементы по списку локаторов
        logger.info("   Hide all element located list of locators");
        List<WebElement> elements = new ArrayList<>();
        for (By by: locatorsList) {
            elements.add(driver().findElement(by));
        }
        JavascriptExecutor js = (JavascriptExecutor) driver();
        for (WebElement element: elements) {
            js.executeScript("arguments[0].style.display = 'none';", element);
        }
    }

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] pngAttachment(String fileName){  // снятие и прикрепление скриншота к аллюр отчету
        Robot bot = null;
        try {
            bot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        bot.mouseMove(0, 0);
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver());
        File scrFile = new File(fileName + ".png");
        try {
            ImageIO.write(screenshot.getImage(), "png", scrFile);
            byte[] bites = Files.readAllBytes(Paths.get(scrFile.getPath()));
            scrFile.delete();
            return bites;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Attachment(value = "page.html", type = "text/html")
    public static byte[] htmlAttachment(){  // прикрепление страницы к аллюр отчету
        try {
            File file = new File("page.html");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.write(driver().getPageSource());
            return toByteArray(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Step("Get html source")
    protected String getHtmlSource(){
        return driver().getPageSource();
    }

    @Step("Logout by URL")
    protected void quickLogout(){
        logger.info("Log out with ");
        get(Constants.DOMEN + "logout");
        waitForUrlToBe(Constants.DOMEN);
        waitForPageToBeLoaded();
    }

    public static void printBrowserLog(boolean withWarnings) {  // вывод консоли браузера
        LogEntries logEntries = driver().manage().logs().get(LogType.BROWSER);
        JavascriptExecutor js = (JavascriptExecutor)driver();
        for (LogEntry entry : logEntries) {
            if (!withWarnings) {
                if (entry.getLevel().getName().equals("SEVERE")) {
                    String message = "CONSOLE: " + new Date(entry.getTimestamp()) + " " + entry.getLevel().getName() + " " + entry.getMessage();
                    System.out.println(message);
                    js.executeScript("console.clear();");
                }
            }else
                System.out.println("CONSOLE: " + new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
        js.executeScript("console.clear();");
    }

    static List<String> getBrowserLog(boolean withWarnings) {  // возвращает список ошибок из консоли браузера
        LogEntries logEntries = driver().manage().logs().get(LogType.BROWSER);
        List<String> logs = new ArrayList<>();
        for (LogEntry entry : logEntries) {
            if (!withWarnings){
                if (entry.getLevel().getName().contains("SEVERE")) {
                    logs.add(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
                }
            }
            else {
                logs.add(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            }
        }
        JavascriptExecutor js = (JavascriptExecutor)driver();
        js.executeScript("console.clear();");
        return logs;
    }

    public static void allureSetStory(String story) {
        Allure.LIFECYCLE.fire(new TestCaseEvent() {
            @Override
            public void process(TestCaseResult testCaseResult) {
                testCaseResult.getLabels().add(AllureModelUtils.createStoryLabel(story));
            }
        });
    }
}
