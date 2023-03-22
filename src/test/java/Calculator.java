import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class Calculator {
    public static WebDriver browser;
    public static final String URL = "http://localhost:8080/prisijungti";

    public static void main(String[] args) throws InterruptedException {
        setUp(URL);
//        boolean registerResult = registerNewAccount("aaa3", "aaa", "aaa");
//        System.out.println("Jūsų registracija: " + registerResult);
        boolean loginResult = login("aaa", "aaa");
        System.out.println("Prisijungimo rezultatas: " + loginResult);
        String calculateResult = calculate(30, "*",2);
//        System.out.println("Gautas rezultatas " + calculateResult);
        boolean searchResult = searchData(30);
        System.out.println("Paieškos rezultatas: " + searchResult);
//        String updateResult = update(-100, "*", 100, 10000);
//        System.out.println("Gautas rezultatas: " + updateResult);
//        boolean deleteResult = delete(17);
//        System.out.println("Trynimo rezultatas: " + deleteResult);


    }

    public static void setUp(String url) {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--start-maximized");
        browser = new ChromeDriver(options);
        browser.get(URL);

    }

    public static boolean registerNewAccount(String name, String password, String repeatPassword) throws InterruptedException {
        Thread.sleep(2000);
        WebElement clickCreateNewAccount = browser.findElement(By.xpath("/html/body/div/form/div/h4/a"));
        clickCreateNewAccount.click();
        WebElement username = browser.findElement(By.id("username"));
        username.sendKeys(name);
        WebElement newPassword = browser.findElement(By.id("password"));
        newPassword.sendKeys(password);
        WebElement repeatNewPassword = browser.findElement(By.id("passwordConfirm"));
        repeatNewPassword.sendKeys(repeatPassword);
        Thread.sleep(2000);
        WebElement create = browser.findElement(By.className("btn"));
        create.click();
        try {
            WebElement registerResult = browser.findElement(By.xpath("/html/body/h2"));
            return registerResult.getText().equals("Galimos operacijos: sudėti, atimti, dauginti, dalinti");
        } catch (NoSuchElementException e) {
            return false;
        }

    }


    public static boolean login(String name, String password) throws InterruptedException {
        WebElement fillName = browser.findElement(By.name("username"));
        fillName.sendKeys(name);
        WebElement fillPassword = browser.findElement(By.name("password"));
        fillPassword.sendKeys(password);
        Thread.sleep(2000);
        WebElement clickJoin = browser.findElement(By.className("btn"));
        clickJoin.click();
        try {
            WebElement loginResult = browser.findElement(By.xpath("/html/body/div/form/div/span[2]"));
            return !loginResult.getText().equals("Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi");
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public static String calculate(int number1, String symbol, int number2) {
        WebElement firstNumber = browser.findElement(By.id("sk1"));
        firstNumber.clear();
        firstNumber.sendKeys(Integer.toString(number1));
        WebElement secondNumber = browser.findElement(By.id("sk2"));
        secondNumber.clear();
        secondNumber.sendKeys(Integer.toString(number2));
        WebElement selectSymbol = browser.findElement(By.name("zenklas"));
        selectSymbol.click();
        List<WebElement> options = selectSymbol.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (option.getAttribute("value").equals(symbol)) {
                option.click();
                break;
            }
        }
        WebElement count = browser.findElement(By.xpath("//*[@id=\"number\"]/input[3]"));
        count.click();

        try {
            WebElement wrongMessageFirstNumber = browser.findElement(By.id("sk1.errors"));
            return wrongMessageFirstNumber.getText();
        } catch (NoSuchElementException e) {

        }
        try {
            WebElement wrongMessageSecondNumber = browser.findElement(By.id("sk2.errors"));
            return wrongMessageSecondNumber.getText();
        } catch (NoSuchElementException e) {

        }

        WebElement getMessage = browser.findElement(By.xpath("/html/body/h4"));
        return getMessage.getText();

    }

    public static boolean searchData(int id) {
        WebElement doneOperations = browser.findElement(By.xpath("/html/body/nav/div/ul[1]/li/a"));
        doneOperations.click();

        try {
            WebElement show = browser.findElement(By.xpath("//a[contains(@href, \"rodyti?id=" + id + "\")]"));
            show.click();
            return true;
        } catch (NoSuchElementException e){

        }
        return false;

    }

    public static boolean update(int number1, String symbol, int number2, int result) {
        WebElement doneOperations = browser.findElement(By.xpath("/html/body/nav/div/ul[1]/li/a"));
        doneOperations.click();
        WebElement changeData = browser.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[5]/a[1]"));
        changeData.click();
        WebElement firstNumber = browser.findElement(By.name("sk1"));
        firstNumber.clear();
        firstNumber.sendKeys(Integer.toString(number1));
        WebElement sign = browser.findElement(By.name("zenklas"));
        sign.clear();
        sign.sendKeys(symbol);
        WebElement secondNumber = browser.findElement(By.name("sk2"));
        secondNumber.clear();
        secondNumber.sendKeys(Integer.toString(number2));
        WebElement rezult = browser.findElement(By.name("rezult"));
        rezult.clear();
        rezult.sendKeys(Integer.toString(result));
        WebElement update = browser.findElement(By.xpath("//*[@id=\"command\"]/p[5]/input"));
        update.click();

        try {
            WebElement updateSuccessful = browser.findElement(By.xpath("/html/body/div/table/tbody/tr[5]/td[2]"));
            return true;
        } catch (NoSuchElementException e) {

        }
        return false;
    }

    public static boolean delete(int id) throws InterruptedException {
        WebElement doneOperations = browser.findElement(By.xpath("/html/body/nav/div/ul[1]/li/a"));
        doneOperations.click();
        try {
            WebElement delete = browser.findElement(By.xpath("//a[contains(@href, \"trinti?id=" + id + "\")]"));
            delete.click();
            Thread.sleep(2000);
            browser.switchTo().alert().accept();
            return true;
        } catch (NoSuchElementException e) {

        }
        return false;

    }

    public static void closeBrowser() throws InterruptedException {
        Thread.sleep(2000);
        browser.close();

    }
}
