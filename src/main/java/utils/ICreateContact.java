package utils;

import dto.ContactDto;
import dto.ResponseMessageDto;
import dto.TokenDto;
import dto.UserData;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import static utils.ContactFactory.positiveContact;
import static utils.PropertiesReader.getProperty;

public interface ICreateContact extends BaseApi {
    default TokenDto getTokenToCreateContact() {
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

        TokenDto tokenDto;

        try {
            tokenDto = GSON.fromJson(response.body().string(), TokenDto.class);
            return  tokenDto;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    default String createContact(TokenDto  tokenDto) {
        ContactDto contact = positiveContact();

        RequestBody requestBody2 = RequestBody.create(GSON.toJson(contact), JSON);

        Request request2 = new Request.Builder()
                .url(BASE_URL + ADD_CONTACTS_URL)
                .addHeader(AUTH, tokenDto.getToken())
                .post(requestBody2)
                .build();

        Response response2;

        try {
            response2 = OK_HTTP_CLIENT.newCall(request2).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ResponseMessageDto responseMessageDto;

        try {
            responseMessageDto = GSON.fromJson(response2.body().string(),
                    ResponseMessageDto.class);
            String contactID = responseMessageDto.getMessage().split("ID: ")[1];
            return contactID;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
