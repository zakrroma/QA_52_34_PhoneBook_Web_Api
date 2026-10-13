package data_providers;

import dto.ContactDto;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ContactDataProvider {
    @DataProvider
    public Iterator<ContactDto> wrongPhoneNumberProvider() {
        List<ContactDto> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/test/resources/" +
                        "wrong_phone_number.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] row = line.split(",");
                list.add(ContactDto.builder()
                        .name(row[0])
                        .lastName(row[1])
                        .email(row[2])
                        .phone(row[3])
                        .address(row[4])
                        .description(row[5])
                        .build());
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("created an exception");
        }
        return list.listIterator();
    }
}
