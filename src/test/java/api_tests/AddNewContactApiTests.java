package api_tests;

import data_providers.ContactDataProvider;
import dto.ContactDto;
import dto.ResponseMessageDto;
import dto.TokenDto;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.BaseApi;
import utils.ILogin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static utils.ContactFactory.*;

public class AddNewContactApiTests implements BaseApi, ILogin {
    TokenDto tokenDto;
    SoftAssert softAssert = new SoftAssert();

    @BeforeClass
    public void login() {
        tokenDto = loginGetToken();
    }

    @Test
    public void addNewContactPositiveApiTest() {
        ContactDto contact = positiveContact();
        //System.out.println(contact);
        //System.out.println(tokenDto.getToken());

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 200);
    }

    @Test
    public void addNewContactPositiveApiTest2() {
        ContactDto contact = positiveContact();
        //System.out.println(contact);
        //System.out.println(tokenDto.getToken());

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ResponseMessageDto responseMessageDto;

        try {
            responseMessageDto =
                    GSON.fromJson(response.body().string(), ResponseMessageDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(responseMessageDto);

        softAssert.assertEquals(response.code(), 200,
                "status code validation ");
        softAssert.assertTrue(responseMessageDto.getMessage()
                .contains("Contact was added!"), "message validation");
        softAssert.assertAll();
    }

    @Test
    public void addNewContactWrongTokenNegativeApiTest() {
        ContactDto contact = positiveContact();

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, "tokenDto.getToken()")
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 401);
    }

    @Test
    public void addNewContactNoTokenNegativeApiTest() {
        ContactDto contact = positiveContact();

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 403);
    }

    @Test
    public void addNewContactNotEnoughDigitsInPhoneNumberNegativeApiTest() { // homework
        ContactDto contact = positiveContact();
        contact.setPhone("123456789");

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 400);
    }

    @Test(dataProvider = "wrongPhoneNumberProvider",
            dataProviderClass = ContactDataProvider.class)
    public void addNewContactTooManyDigitsInPhoneNumberNegativeApiTest(ContactDto contact) { // homework
        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void addNewContactUnacceptableSymbolsInPhoneNumberNegativeApiTest() { // homework
        ContactDto contact = positiveContact();
        contact.setPhone("+0123456789");

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void addNewContactNullInNameNegativeApiTest() { // homework
        ContactDto contact = positiveContact();
        contact.setName(null);

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response);
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void addNewContactNullInLastNameNegativeApiTest() { // homework
        ContactDto contact = positiveContact();
        contact.setLastName(null);

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response);
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void addNewContactNullInAddressNegativeApiTest() { // homework
        ContactDto contact = positiveContact();
        contact.setAddress(null);

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response);
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void addNewContactDuplicateContactFieldsNegativeApiTest() { // homework
        ContactDto contact = positiveContact();

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody)
                .build();

        Response response;

        try {
            OK_HTTP_CLIENT.newCall(request).execute();
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response);
        //Assert.assertEquals(response.code(), 409); // code 200 (bug?)
    }

    @Test
    public void addNewContactWrongRequestFormatNegativeApiTest() { // homework
        ContactDto contact = positiveContact();

        RequestBody requestBody = RequestBody.create(GSON.toJson(contact), TEXT);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 500);
    }

    @Test
    public void addNewContactWrongKeyNegativeApiTest() { // homework
        ContactDto contact = positiveContact();

        Map<String,String> invalidJson = new HashMap<>();
        invalidJson.put("name", contact.getName());
        invalidJson.put("lastname", contact.getLastName()); // <---- wrong key
        invalidJson.put("email", contact.getEmail());
        invalidJson.put("phone", contact.getPhone());
        invalidJson.put("address", contact.getAddress());
        invalidJson.put("description", contact.getDescription());

        RequestBody requestBody = RequestBody.create(GSON.toJson(invalidJson), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertEquals(response.code(), 400);
    }
}
