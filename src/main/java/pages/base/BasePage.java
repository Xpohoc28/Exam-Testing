package pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Базовый класс для всех страниц в проекте.
 * Реализует паттерн Page Object и содержит общие методы для работы с веб-элементами.
 * Наследуется всеми конкретными классами страниц.
 */
public class BasePage {

    /**
     * Экземпляр WebDriver для взаимодействия с браузером.
     * Модификатор protected позволяет использовать его в классах-наследниках.
     */
    protected WebDriver driver;

    /**
     * Конструктор базового класса страницы.
     * @param driver экземпляр WebDriver для работы с браузером
     */
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Локатор для поиска заголовка страницы.
     * Используется для проверки корректности загрузки страницы.
     */
    private static final By NAME_PAGE = By.xpath("//h1[@class='text-center']");

    /**
     * Открывает указанный URL в браузере.
     * @param url адрес страницы для открытия
     */
    public void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Находит элемент на странице и прокручивает к нему.
     * @param locator локатор для поиска элемента
     * @return найденный веб-элемент
     */
    public WebElement findElement(By locator) {
        // Поиск элемента на странице
        WebElement element = driver.findElement(locator);
        
        // Прокрутка страницы к элементу с помощью JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        
        return element;
    }

    /**
     * Находит все элементы, соответствующие XPath локатору.
     * @param locator XPath выражение для поиска элементов
     * @return список найденных веб-элементов
     */
    public List<WebElement> findElements(String locator) {
        return driver.findElements(By.xpath(locator));
    }

    /**
     * Получает текст из элемента.
     * @param locator локатор для поиска элемента
     * @return текст элемента
     */
    public String getText(By locator) {
        return findElement(locator).getText();
    }

    /**
     * Получает заголовок текущей страницы.
     * @return текст заголовка страницы
     */
    public String getPageName(){
        return getText(NAME_PAGE);
    }

    /**
     * Проверяет, доступен ли элемент для взаимодействия.
     * @param locator локатор для поиска элемента
     * @return true, если элемент доступен
     */
    public boolean isElementEnabled(By locator){
        return findElement(locator).isEnabled();
    }

    /**
     * Очищает текстовое поле.
     * @param locator локатор для поиска поля
     */
    public void clear(By locator) {
        findElement(locator).clear();
    }

    /**
     * Вводит текст в поле ввода.
     * @param locator локатор для поиска поля
     * @param text текст для ввода
     */
    public void sendKeys(By locator, String text) {
        findElement(locator).sendKeys(text);
    }

    /**
     * Выполняет клик по элементу.
     * @param locator локатор для поиска элемента
     */
    public void click(By locator) {
        findElement(locator).click();
    }

    /**
     * Выбрасывает AssertionError для явного указания ошибки в тесте.
     * Используется для проверок, которые должны завершиться неудачей.
     */
    public void failure() {
        throw new AssertionError();
    }

    /**
     * Проверяет, отображается ли элемент на странице.
     * @param locator локатор для поиска элемента
     * @return true, если элемент отображается
     */
    public boolean isElementDisplay(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (Exception ex){
            // Если элемент не найден, возвращаем false
            return false;
        }
    }

    /**
     * Ожидает отображение элемента в течение указанного времени.
     * @param locator локатор для поиска элемента
     * @param second время ожидания в секундах
     */
    public void waitElementIsDisplay(By locator, int second) {
        new WebDriverWait(driver, Duration.ofSeconds(second))
            .until(d -> isElementDisplay(locator));
    }
}