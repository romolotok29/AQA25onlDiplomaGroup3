package data;

import org.testng.annotations.DataProvider;

public class StaticProvider {

    @DataProvider(name = "boundaryValues")
    public static Object[][] boundaryValues() {
        return new Object[][] {
                {"", true},
                {"a", true},
                {new String(new char[249]).replace('\0', '1'), true},
                {new String(new char[250]).replace('\0', '1'), true}
        };
    }

}
