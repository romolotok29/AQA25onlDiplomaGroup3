package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileUtil {

    public static void copyEnvironmentFile() throws IOException {
        File source = new File("src/test/resources/environment.xml");
        File targetDir = new File("target/allure-results");
        File target = new File(targetDir, "environment.xml");

        Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
