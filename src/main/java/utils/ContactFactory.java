package utils;

import net.datafaker.Faker;

public class ContactFactory {
    static Faker faker = new Faker();

    public static ContactDto positiveContact() {
        ContactDto contact = ContactDto.builder()
                .name(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .phone(faker.number().digits(12))
                .address(faker.address().fullAddress())
                .description("test_description")
                .build();
        return contact;
    }
}