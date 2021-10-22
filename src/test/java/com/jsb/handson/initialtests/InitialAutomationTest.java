package com.jsb.handson.initialtests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InitialAutomationTest {

    public static String URL = "https://opensource-demo.orangehrmlive.com/";
    public static By txt_username = By.xpath("//*[@id='txtUsername']");
    public static By txt_password = By.xpath("//input[@id='txtUsername']/parent::div/following-sibling::div[1]/input");
    public static By btn_login = By.xpath("//a[text()='Forgot your password?']");

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("capabilityKey", "capabilityValue");

        WebDriver driver = new ChromeDriver();

        //now we have maximized the browser window
        driver.manage().window().maximize();

        //i have to access a URL

        driver.get(URL);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(txt_username));

//		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
//		driver.findElement(By.xpath("//input[@id='txtPassword']/preceding::input[1]")).sendKeys("Admin");
        driver.findElement(txt_username).sendKeys("Admin");

        wait.until(ExpectedConditions.presenceOfElementLocated(txt_password));
        driver.findElement(txt_password).sendKeys("admin123");
//		driver.findElement(By.className("button")).click();

//		driver.findElement(By.linkText("Forgot your password?")).click();
//		driver.findElement(By.partialLinkText("Forgot")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(btn_login));
        driver.findElement(btn_login).click();
        driver.close();

        ChromeDriver driver1 = new ChromeDriver();


    }

}
