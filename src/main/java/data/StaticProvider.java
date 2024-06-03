package data;

import org.testng.annotations.DataProvider;

public class StaticProvider {
    public static final String MAX_PLUS_ONE = new String(new char[251]).replace('\0', '1');

    @DataProvider(name = "boundaryValues")
    public static Object[][] boundaryValues() {
        return new Object[][] {
                {"", true},
                {"a", true},
                {new String(new char[249]).replace('\0', '1'), true},
                {new String(new char[250]).replace('\0', '1'), true}
        };
    }

    @DataProvider(name = "dataExceedsTheLimit")
    public static Object[][] dataExceedsTheLimit() {
        return new Object[][] {
                {"!!!", false},
                {"$$$", false},
                {MAX_PLUS_ONE, false},
        };
    }

}
