package com.jsb.handson.refactoredtests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RefactoredFurtherAutomationTest {

    public static String URL = "https://opensource-demo.orangehrmlive.com/";
    public static By txt_username = By.xpath("//*[@id='txtUsername']");
    public static By txt_password = By.xpath("//input[@id='txtUsername']/parent::div/following-sibling::div[1]/input");
    public static By btn_login = By.xpath("//input[@id='btnLogin']");

    public static By lnk_recruitment = By.xpath("//a[@id=\"menu_recruitment_viewRecruitmentModule\"]");
    public static By slt_vacancy = By.id("candidateSearch_jobVacancy");

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        inputValues(driver, txt_username, "Admin");
        inputValues(driver, txt_password, "admin123");
        click(driver, btn_login);

        click(driver, lnk_recruitment);
        selectDropDownByText(driver, slt_vacancy, "Senior QA Lead");
        Thread.sleep(5000);
        selectDropDownByText(driver, slt_vacancy, "Associate IT Manager");
        Thread.sleep(5000);
        selectByDropDownValue(driver, slt_vacancy, "3");
        Thread.sleep(5000);
        selectByDropDownIndex(driver, slt_vacancy, 7);

        driver.close();
    }

    public static void click(WebDriver driver, By by) {
        explicitlyWait(driver, by);
        driver.findElement(by).click();
    }

    public static void inputValues(WebDriver driver, By by, String inputText) {
        explicitlyWait(driver, by);
        driver.findElement(by).sendKeys(inputText);
    }

    public static void selectDropDownByText(WebDriver driver, By by, String text) {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }

    private static void selectByDropDownValue(WebDriver driver, By by, String value) {
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value);
    }

    private static void selectByDropDownIndex(WebDriver driver, By by, Integer index) {
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(index);
    }

    public static void explicitlyWait(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        if (by.equals(txt_username)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(txt_username));
        } else if (by.equals(txt_password)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(txt_password));
        } else if (by.equals(btn_login)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(btn_login));
        } else if (by.equals(lnk_recruitment)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(lnk_recruitment));
        } else {
            wait.until(ExpectedConditions.presenceOfElementLocated(slt_vacancy));
        }
    }
}
