package tests;

import baseEntities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NewTestClass extends BaseTest {

    @Test(description = "Новый пробный тест")
    public void NewTest() {
        Assert.assertTrue(true);
    }
}