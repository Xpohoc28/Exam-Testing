package base;

import browser.Browser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * Базовый класс для всех тестов в проекте.
 * Содержит общую логику инициализации и завершения тестов.
 * Наследуется всеми тестовыми классами.
 */
public class BaseTest {

    /**
     * Экземпляр WebDriver для взаимодействия с браузером.
     * Модификатор protected позволяет использовать его в классах-наследниках.
     */
    protected WebDriver driver;

    /**
     * Метод, выполняемый перед запуском всех тестов в классе.
     * Инициализирует WebDriver с помощью класса Browser.
     * Вызывается один раз для всех тестов в классе.
     */
    @BeforeClass
    public void setupClass(){
        System.out.println("Setting up before class in BaseTest.");
        // Создаем экземпляр WebDriver с помощью фабричного метода
        driver = Browser.createDriver();
    }

    /**
     * Метод, выполняемый после завершения всех тестов в классе.
     * Вызывается один раз после выполнения всех тестов в классе.
     */
    @AfterClass
    public void tearDownClass(){
        System.out.println("Tests completed. Browser will remain open.");
        // Браузер остается открытым
    }
}