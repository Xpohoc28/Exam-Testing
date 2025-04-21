package UI;

import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;

import static org.testng.Assert.*;

public class WebFormTest extends BaseTest {

    @Test
    public void testWebForm() {
        // Переход на веб-форму
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        // Заполнение поля ввода текста полным именем
        WebElement textInput = driver.findElement(By.id("my-text-id"));
        textInput.sendKeys("Чаплыгин Виктор Андреевич");
        assertEquals(textInput.getAttribute("value"), "Чаплыгин Виктор Андреевич");

        // Заполнение текстовой области названием компании
        WebElement textarea = driver.findElement(By.name("my-textarea"));
        textarea.sendKeys("ГОООООООООЛ");
        assertEquals(textarea.getAttribute("value"), "ГОООООООООЛ");

        // Заполнение поля пароля
        WebElement password = driver.findElement(By.name("my-password"));
        password.sendKeys("Password");
        assertEquals(password.getAttribute("value"), "Password");

        // Выбор значения "two" из выпадающего списка
        WebElement dropdown = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdown);
        select.selectByValue("2");
        assertEquals(select.getFirstSelectedOption().getAttribute("value"), "2");

        // Выбор "Seattle" из списка данных
        WebElement datalist = driver.findElement(By.name("my-datalist"));
        datalist.sendKeys("Seattle");
        assertEquals(datalist.getAttribute("value"), "Seattle");

        // Установка обоих чекбоксов
        WebElement checkbox1 = driver.findElement(By.id("my-check-1"));
        WebElement checkbox2 = driver.findElement(By.id("my-check-2"));
        
        if (!checkbox1.isSelected()) checkbox1.click();
        if (!checkbox2.isSelected()) checkbox2.click();
        
        assertTrue(checkbox1.isSelected());
        assertTrue(checkbox2.isSelected());

        // Выбор радиокнопки по умолчанию
        WebElement radioButton = driver.findElement(By.id("my-radio-2"));
        radioButton.click();
        assertTrue(radioButton.isSelected());

        // Установка зеленого цвета
        WebElement colorPicker = driver.findElement(By.name("my-colors"));
        colorPicker.sendKeys("#00FF00");
        assertEquals(colorPicker.getAttribute("value").toLowerCase(), "#00ff00");

        // Установка даты 23 сентября 2024 года
        WebElement datePicker = driver.findElement(By.name("my-date"));
//        datePicker.sendKeys("2024/09/23");
        datePicker.sendKeys("09-23-2024");
        assertEquals(datePicker.getAttribute("value"), "09-23-2024");



        // Установка диапазона на максимальное значение
        WebElement range = driver.findElement(By.name("my-range"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].value = '10'; arguments[0].dispatchEvent(new Event('change'));", range);

        // Отправка формы
        WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
        submitButton.click();

        // Проверка сообщения об отправке
        WebElement message = driver.findElement(By.id("message"));
        assertEquals(message.getText(), "Received!");
    }
} 