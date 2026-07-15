package helper;

import models.User;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Select;

import java.util.List;

public class TestDataGenerator {

    public static final Faker FAKER = new Faker();

    public static List<String> names = List.of("Michael", "Sean", "James", "George");

    User user = Instancio.of(User.class)
            .ignore(Select.field(User::getId))
            .generate(Select.field(User::getName), x-> x.oneOf(names))
            .create();

    List<User> users = Instancio.ofList(User.class)
            .size(5)
            .create();

}