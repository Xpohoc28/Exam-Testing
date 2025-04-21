package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.time.Duration;

import static browser.Config.BROWSER_TYPE;

/**
 * Класс Browser отвечает за создание и настройку экземпляра WebDriver.
 * Реализует паттерн Factory Method для создания драйверов разных браузеров.
 */
public class Browser {

    /**
     * Создает и настраивает экземпляр WebDriver в зависимости от типа браузера.
     *
     * @return Настроенный экземпляр WebDriver
     * @throws IllegalArgumentException если указан неподдерживаемый тип браузера
     */
    public static WebDriver createDriver(){

        WebDriver driver;

        // Выбор и создание драйвера в зависимости от типа браузера
        switch (BROWSER_TYPE){
            case "chrome":
                // Настройка ChromeDriver с помощью WebDriverManager
                WebDriverManager.chromedriver().setup();

                // Создание и настройка опций для Chrome
                ChromeOptions chromeOptions = new ChromeOptions();
                // Запуск браузера в headless режиме (без GUI)
                chromeOptions.addArguments("--headless");
                // Установка стратегии загрузки страницы
                chromeOptions.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");

                // Создание экземпляра ChromeDriver с настройками
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                // Настройка FirefoxDriver с помощью WebDriverManager
                WebDriverManager.firefoxdriver().setup();

                // Создание и настройка опций для Firefox
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                // Запуск браузера в headless режиме
                firefoxOptions.addArguments("--headless");
                // Установка стратегии загрузки страницы
                firefoxOptions.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");

                // Создание экземпляра FirefoxDriver с настройками
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                // Выброс исключения при неподдерживаемом типе браузера
                throw new IllegalArgumentException("Некорректное имя браузера: " + BROWSER_TYPE);
        }

        // Настройка окна браузера
        driver.manage().window().maximize();

        // Установка неявного ожидания для всех операций поиска элементов
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        return driver;
    }
}