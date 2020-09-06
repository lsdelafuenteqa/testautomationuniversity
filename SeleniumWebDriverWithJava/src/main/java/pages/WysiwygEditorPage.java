package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class WysiwygEditorPage {

    private WebDriver driver;
    //It isn't a By element because the frame method return a String and not a WebElement
    private String editorIframeId = "mce_0_ifr";
    private By textArea = By.id("tinymce");
    private By decreaseIndentButton = By.cssSelector("#mceu_12 button");

    public WysiwygEditorPage(WebDriver driver){
        this.driver = driver;
    }

    public void clearTextArea(){
        switchToEditArea();
        driver.findElement(textArea).clear();
        switchToTheMainArea();
    }

    public void setTextArea(String text){
        switchToEditArea();
        driver.findElement(textArea).sendKeys(text);
        switchToTheMainArea();
    }

    public String getTextFromEditor(){
        switchToEditArea();
        String text = driver.findElement(textArea).getText();
        switchToTheMainArea();
        return text;
    }

    public void decreaseIndention(){

        driver.findElement(decreaseIndentButton).click();
    }

    private void switchToEditArea(){
        driver.switchTo().frame(editorIframeId);
    }

    //It a good practice exit to the IFrame when the test finish
    private void switchToTheMainArea(){
        driver.switchTo().parentFrame();
    }
}
