package api_tests;

import dto.ContactsDto;
import dto.TokenDto;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BaseApi;
import utils.ILogin;

import java.io.IOException;

public class GetContactsApiTests implements BaseApi, ILogin {
    TokenDto tokenDto;

    @BeforeClass
    public void login() {
        tokenDto = loginGetToken();
    }

    @Test
    public void getContactsPositiveApiTest() {
        Request request = new Request.Builder()
                .url(BASE_URL + GET_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .get()
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            ContactsDto contactsDto =
                    GSON.fromJson(response.body().string(), ContactsDto.class);
            System.out.println(contactsDto.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 200);
    }
}
