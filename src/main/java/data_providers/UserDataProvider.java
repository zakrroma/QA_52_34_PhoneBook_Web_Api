package data_providers;

import dto.UserData;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDataProvider {
    @DataProvider
    public Iterator<UserData> wrongEmailPasswordProvider() {
        List<UserData> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/test/resources/" +
                        "wrong_email_password.csv"))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] row = line.split(",");
                list.add(UserData.builder()
                        .username(row[0])
                        .password(row[1])
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