import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorApplicationTests {
    public static final int searchId = 33;
    public static final int deleteId = 33;
    @Before
    public void setUp() {
        Calculator.setUp(Calculator.URL);
    }

    @Test
    public void registerNewAccountPositiveTest() throws InterruptedException {
        boolean registerResult = Calculator.registerNewAccount("aaa7", "aaa", "aaa");
        Assert.assertEquals(true, registerResult);
        Calculator.closeBrowser();
    }

    @Test
    public void registerNewAccountNegativeTest() throws InterruptedException {
        boolean registerResult = Calculator.registerNewAccount("aaa3", "aaa", "aaa");
        Assert.assertEquals(false, registerResult);
        Calculator.closeBrowser();
    }


    @Test
    public void loginPositiveTest() throws InterruptedException {
        boolean loginResult = Calculator.login("giedre", "giedre");
        Assert.assertEquals(true, loginResult);
        Calculator.closeBrowser();
    }

    @Test
    public void loginNegativeTest() throws InterruptedException {
        boolean loginResult = Calculator.login("giedr1e", "giedre");
        Assert.assertEquals(false, loginResult);
        Calculator.closeBrowser();

    }

    @Test
    public void calculatePositiveTest() throws InterruptedException {
        Calculator.login("giedre", "giedre");
        String message = Calculator.calculate(4, "*", 4);
        Assert.assertEquals("4 * 4 = 16", message);
        Calculator.closeBrowser();

    }

    @Test
    public void calculateNegativeTest() throws InterruptedException {
        Calculator.login("giedre", "giedre");
        String message = Calculator.calculate(-4, "*", 4);
        Assert.assertEquals("Validacijos klaida: skaičius negali būti neigiamas", message);
        Calculator.closeBrowser();
    }

    @Test
    public void searchPositiveTest() throws InterruptedException {
        Calculator.login("giedre", "giedre");
        boolean searchResult = Calculator.searchData(searchId);
        Assert.assertEquals(true, searchResult);
        Calculator.closeBrowser();
    }

    @Test
    public void searchNegativeTest() throws InterruptedException {
        Calculator.login("giedre", "giedre");
        boolean searchResult = Calculator.searchData(-1);
        Assert.assertEquals(false, searchResult);
        Calculator.closeBrowser();

    }

    @Test
    public void updatePositiveTest() throws InterruptedException {
        Calculator.login("giedre", "giedre");
        boolean calsulateResult = Calculator.update(3, "-", 1, 2);
        Assert.assertEquals(true, calsulateResult);
        Calculator.closeBrowser();

    }

    @Test
    public void updateNegativeTest() throws InterruptedException {
        Calculator.login("giedre", "giedre");
        boolean calculateResult = Calculator.update(9, "*", -2, -18);
        Assert.assertEquals(false, calculateResult);

        Calculator.closeBrowser();
    }

    @Test
    public void deletePositiveTest() throws InterruptedException {
        Calculator.login("giedre", "giedre");
        boolean deleteResult = Calculator.delete(deleteId);
        Assert.assertEquals(true, deleteResult);
        Calculator.closeBrowser();

    }

    @Test
    public void deleteNegativeTest() throws InterruptedException {
        Calculator.login("giedre", "giedre");
        boolean deleteResult = Calculator.delete(-17);
        Assert.assertEquals(false, deleteResult);
        Calculator.closeBrowser();

    }


}

