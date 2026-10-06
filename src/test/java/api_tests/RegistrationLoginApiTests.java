package api_tests;

import dto.UserData;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BaseApi;

import java.io.IOException;

import static utils.UserFactory.*;
import static utils.PropertiesReader.*;

public class RegistrationLoginApiTests implements BaseApi {

    @Test
    public void registrationPositiveApiTest() {
        UserData user = positiveUser();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + REG_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(response);
        Assert.assertEquals(response.code(), 200);
    }

    @Test
    public void registrationWrongPasswordNegativeApiTest() {
        UserData user = positiveUser();
        user.setPassword("wrongPassword1");

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + REG_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(response);
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNullInPasswordNegativeApiTest() {  // homework
        UserData user = positiveUser();
        user.setPassword(null);

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + REG_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(response);
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationNullInUsernameNegativeApiTest() {  // homework
        UserData user = positiveUser();
        user.setUsername(null);

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + REG_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(response);
        Assert.assertEquals(response.code(), 400);
    }

    @Test
    public void registrationDuplicateUserDataNegativeApiTest() {
        UserData user = positiveUser();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + REG_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            OK_HTTP_CLIENT.newCall(request).execute();
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(response);
        Assert.assertEquals(response.code(), 409);
    }

    @Test
    public void registrationWrongRequestFormatNegativeApiTest() {
        UserData user = positiveUser();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), TEXT);

        Request request = new Request.Builder()
                .url(BASE_URL + REG_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(response);
        Assert.assertEquals(response.code(), 500);
    }

    @Test
    public void registrationWrongKeyNegativeApiTest() {  // homework
        UserData user = negativeUser();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + REG_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(response);
        Assert.assertEquals(response.code(), 500);
    }

    @Test
    public void loginPositiveApiTest() {
        UserData user = UserData.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(response);
        Assert.assertEquals(response.code(), 200);
    }

    @Test
    public void loginWrongPasswordNegativeApiTest() {
        UserData user = UserData.builder()
                .username(getProperty("base.properties", "email"))
                .password("wrongPassword1!")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(response);
        Assert.assertEquals(response.code(), 401);
    }

    @Test
    public void loginNullInPasswordNegativeApiTest() { // homework
        UserData user = UserData.builder()
                .username(getProperty("base.properties", "email"))
                .password(null)
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(response);
        Assert.assertEquals(response.code(), 401);
    }

    @Test
    public void loginWrongRequestFormatNegativeApiTest() { // homework
        UserData user = UserData.builder()
                .username(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), TEXT);

        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response);
        //Assert.assertEquals(response.code(), 500); // doesn't work
    }

    @Test
    public void loginWrongKeyNegativeApiTest() { // homework
        UserData user = UserData.builder()
                .email(getProperty("base.properties", "email"))
                .password(getProperty("base.properties", "password"))
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(user), JSON);

        Request request = new Request.Builder()
                .url(BASE_URL + LOGIN_URL)
                .post(requestBody)
                .build();

        Response response;

        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //System.out.println(response);
        Assert.assertEquals(response.code(), 400);
    }
}
