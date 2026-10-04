package utils;

import dto.TokenDto;
import dto.UserData;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

import static utils.PropertiesReader.getProperty;

public interface ILogin extends BaseApi {
    default TokenDto loginGetToken() {
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
}
