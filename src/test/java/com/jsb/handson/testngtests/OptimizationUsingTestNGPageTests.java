package com.jsb.handson.testngtests;


import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static com.jsb.handson.pages.OrangeHRMLoginDashboardPage.lnk_recruitment;
import static com.jsb.handson.pages.OrangeHRMLoginPage.*;
import static com.jsb.handson.pages.OrangeHRMRecruitmentPage.slt_jobTitle;
import static com.jsb.handson.pages.OrangeHRMRecruitmentPage.slt_vacancy;

public class OptimizationUsingTestNGPageTests {

    public static String URL = "https://opensource-demo.orangehrmlive.com/";
    public static WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito","start-maximized");

        driver = new ChromeDriver(chromeOptions);
        driver.get(URL);
    }

    @Test(enabled = true)
    public void orangeHRM_incognitoModeloginTest() {
        inputValues(driver, txt_username, "Admin");
        inputValues(driver, txt_password, "admin123");
        click(driver, btn_login);
    }

    @Test(enabled = true)
    public void orangeHRM_multipleOptionsTest() throws InterruptedException {
        inputValues(driver, txt_username, "Admin");
        inputValues(driver, txt_password, "admin123");
        click(driver, btn_login);

        click(driver, lnk_recruitment);
        Thread.sleep(2000);
        getListOfJobTitles(driver, slt_jobTitle);
    }

    @Test(enabled = false)
    public void orangeHRM_proxyServerTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito","--headless","--start-minimized", "--disable-notifications");

        // Add the WebDriver proxy capability.
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("myhttpproxy:3337");
        chromeOptions.setCapability("proxy", proxy);

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.get(URL);
        inputValues(driver, txt_username, "Admin");
        inputValues(driver, txt_password, "admin123");
        click(driver, btn_login);

        click(driver, lnk_recruitment);
        Thread.sleep(2000);

        getListOfVacancies(driver, slt_vacancy);
    }

    @AfterMethod
    public void teardown(){
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

    private void getListOfJobTitles(WebDriver driver, By by) {
        Select select = new Select(driver.findElement(by));
        List<WebElement> jobTitlesList = select.getOptions();
        for(int i=0; i < jobTitlesList.size(); i++){
            System.out.println(jobTitlesList.get(i).getText());
        }
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
        } else if(by.equals(slt_vacancy)){
            wait.until(ExpectedConditions.presenceOfElementLocated(slt_vacancy));
        }else{
            wait.until(ExpectedConditions.presenceOfElementLocated(slt_jobTitle));
        }
    }
}
