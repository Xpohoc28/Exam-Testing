package pages.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;

/**
 * Класс для работы со страницей Check Box на сайте DemoQA.
 * Наследуется от BasePage и реализует функциональность для работы с чекбоксами.
 */
public class CheckBoxPage extends BasePage {

    /**
     * Конструктор класса CheckBoxPage.
     * @param driver экземпляр WebDriver для работы с браузером
     */
    public CheckBoxPage(WebDriver driver) {
        super(driver);
    }

    /**
     * URL страницы с чекбоксами
     */
    private static final String URL_TEXT_BOX_PAGE = "https://demoqa.com/checkbox";

    /**
     * XPath шаблон для поиска input элемента чекбокса по его названию.
     * Использует форматирование строки для подстановки названия чекбокса.
     */
    private static final String CHECK_BOX_INPUT_XPATH = "//input" +
            "[@type='checkbox'][following-sibling::span[text()='%s']]";

    /**
     * XPath шаблон для поиска видимой части чекбокса (квадратика для нажатия).
     * Использует базовый шаблон CHECK_BOX_INPUT_XPATH и добавляет путь к видимой части.
     */
    private static final String CHECK_BOX_XPATH = CHECK_BOX_INPUT_XPATH +
            "/following-sibling::span[@class='rct-checkbox']";

    /**
     * XPath шаблон для поиска кнопки "Свернуть/развернуть" рядом с чекбоксом.
     * Использует базовый шаблон и добавляет путь к кнопке toggle.
     */
    private static final String BUTTON_TOGGLE = CHECK_BOX_INPUT_XPATH + "/../preceding-sibling::button[@title='Toggle']";

    /**
     * XPath шаблон для проверки состояния списка чекбоксов (свернут/развернут).
     * Использует базовый шаблон и добавляет проверку класса элемента.
     */
    private static final String LIST_CHECKBOX_XPATH = CHECK_BOX_INPUT_XPATH + "/ancestor::li[contains(@class,'%s')]";

    /**
     * Открывает страницу с чекбоксами.
     * Использует метод openUrl из базового класса.
     */
    public void openCheckBoxPage(){
        openUrl(URL_TEXT_BOX_PAGE);
    }

    /**
     * Проверяет состояние чекбокса (отмечен/не отмечен).
     * @param checkBoxName название чекбокса
     * @return true, если чекбокс отмечен
     */
    public boolean getCheckBoxState(String checkBoxName){
        return findElement(By.xpath(String.format(CHECK_BOX_INPUT_XPATH,checkBoxName))).isSelected();
    }

    /**
     * Устанавливает чекбокс в нужное состояние.
     * Если текущее состояние не соответствует требуемому, выполняет клик.
     * @param checkBoxName название чекбокса
     * @param state требуемое состояние (true - отмечен, false - не отмечен)
     */
    public void setCheckBox(String checkBoxName, boolean state){
        if (!getCheckBoxState(checkBoxName) == state){
            click(By.xpath(String.format(CHECK_BOX_XPATH,checkBoxName)));
        }
    }

    /**
     * Проверяет, отображается ли чекбокс на странице.
     * @param checkBoxName название чекбокса
     * @return true, если чекбокс отображается
     */
    public boolean isCheckBoxDisplay(String checkBoxName){
        return isElementDisplay(By.xpath(String.format(CHECK_BOX_XPATH,checkBoxName)));
    }

    /**
     * Проверяет, свернут ли список чекбоксов.
     * @param checkBoxName название родительского чекбокса
     * @return true, если список свернут
     */
    public boolean isListCheckBoxClosed(String checkBoxName){
        return isElementDisplay(By.xpath(String.format(LIST_CHECKBOX_XPATH,checkBoxName,"collapsed")));
    }

    /**
     * Открывает список чекбоксов, нажимая на кнопку toggle.
     * Выбрасывает исключение, если чекбокс не отображается.
     * @param checkBoxName название чекбокса
     */
    public void openListCheckBox(String checkBoxName){
        // Проверка наличия чекбокса на странице
        if (!isCheckBoxDisplay(checkBoxName)){
            failure();
        }
        // Если список свернут, нажимаем на кнопку toggle
        if (isListCheckBoxClosed(checkBoxName)){
            click(By.xpath(String.format(BUTTON_TOGGLE,checkBoxName)));
        }
    }
}