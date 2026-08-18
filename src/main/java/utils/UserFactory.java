package utils;

import dto.UserData;
import net.datafaker.Faker;

public class UserFactory {
    static Faker faker = new Faker();

    /*
        public static void main(String[] args) {
            String firstName = faker.name().firstName();
            System.out.println("firstName: " + firstName);
            String lastName = faker.name().lastName();
            System.out.println("lastName: " + lastName);
            String email = faker.internet().emailAddress();
            System.out.println("email: " + email);
        }
    */
    public static UserData positiveUser() {
        UserData user = UserData.builder()
                .username(faker.internet().emailAddress())
                .password(PropertiesReader.getProperty(
                        "base.properties", "sign_up_pass"))
                .build();
        return user;
    }
}