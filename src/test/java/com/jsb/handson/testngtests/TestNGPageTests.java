package com.jsb.handson.testngtests;


import com.jsb.handson.pages.OrangeHRMLoginDashboardPage;
import com.jsb.handson.pages.OrangeHRMLoginPage;
import com.jsb.handson.pages.OrangeHRMRecruitmentPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

import static com.jsb.handson.pages.OrangeHRMLoginPage.*;
import static com.jsb.handson.pages.OrangeHRMRecruitmentPage.*;

public class TestNGPageTests {

    public static String URL = "https://opensource-demo.orangehrmlive.com/";

    @Test(priority = 1)
    public void orangeHRM_loginTest() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        inputValues(driver, txt_username, "Admin");
        inputValues(driver, txt_password, "admin123");
        click(driver, btn_login);

        driver.close();
    }

    @Test(priority = 0)
    public void orangeHRM_recruitmentSingleVacancyTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        inputValues(driver, txt_username, "Admin");
        inputValues(driver, txt_password, "admin123");
        click(driver, btn_login);

        click(driver, OrangeHRMLoginDashboardPage.lnk_recruitment);
        Thread.sleep(1000);
        selectDropDownByText(driver, slt_vacancy, "Senior QA Lead");
        Thread.sleep(1000);
        selectDropDownByText(driver, slt_vacancy, "Associate IT Manager");
        Thread.sleep(1000);
        selectByDropDownValue(driver, slt_vacancy, "3");
        Thread.sleep(1000);
        selectByDropDownIndex(driver, slt_vacancy, 7);

        driver.close();
    }

    @Test(priority = -1)
    public void orangeHRM_recruitmentMultipleVacanciesTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        inputValues(driver, txt_username, "Admin");
        inputValues(driver, txt_password, "admin123");
        click(driver, btn_login);

        click(driver, OrangeHRMLoginDashboardPage.lnk_recruitment);
        Thread.sleep(1000);
        selectDropDownByText(driver, slt_vacancy, "Senior QA Lead");
        Thread.sleep(1000);
        selectDropDownByText(driver, slt_vacancy, "Associate IT Manager");
        Thread.sleep(1000);
        selectByDropDownValue(driver, slt_vacancy, "3");
        Thread.sleep(1000);
        selectByDropDownIndex(driver, slt_vacancy, 7);

        getListOfVacancies(driver, slt_vacancy);

        driver.close();
    }

    private void getListOfVacancies(WebDriver driver, By by) {
        Select select = new Select(driver.findElement(by));
        List<WebElement> vacancies = select.getOptions();

        //Enhanced for loop
//        for(WebElement element : vacancies){
//            System.out.println(element.getText());
//        }
        //Normal for loop
        for(int i=0; i< vacancies.size(); i++){
            System.out.println(vacancies.get(i).getText());
        }
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
        } else if (by.equals(OrangeHRMLoginDashboardPage.lnk_recruitment)) {
            wait.until(ExpectedConditions.presenceOfElementLocated(OrangeHRMLoginDashboardPage.lnk_recruitment));
        } else {
            wait.until(ExpectedConditions.presenceOfElementLocated(slt_vacancy));
        }
    }
}
