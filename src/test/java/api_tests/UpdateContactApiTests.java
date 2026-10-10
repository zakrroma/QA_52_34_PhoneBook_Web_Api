package api_tests;

import dto.ContactDto;
import dto.TokenDto;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BaseApi;
import utils.ICreateContact;

import java.io.IOException;

import static utils.ContactFactory.positiveContact;

public class UpdateContactApiTests
        implements BaseApi, ICreateContact {
    TokenDto tokenDto;
    String contactID;

    @BeforeClass
    public void loginAndCreateContact() {
        tokenDto = getTokenToCreateContact();
        System.out.println(tokenDto);
        contactID = createContact(tokenDto);
        System.out.println(contactID);
    }

    @Test
    public void updateContactPositiveApiTest() {
        ContactDto contact = positiveContact();
        contact.setId(contactID);
        System.out.println(contact);
        System.out.println(tokenDto.getToken());

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + UPDATE_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .put(requestBody)
                .build();
        System.out.println(request);

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response);
        Assert.assertEquals(response.code(), 200);
    }
}
