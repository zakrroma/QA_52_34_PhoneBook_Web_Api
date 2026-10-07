package utils;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

public interface BaseApi {
    String BASE_URL =
            "https://contactapp-telran-backend.herokuapp.com";
    String REG_URL =
            "/v1/user/registration/usernamepassword";
    String LOGIN_URL =
            "/v1/user/login/usernamepassword";
    String ADD_CONTACTS_URL =
            "/v1/contacts";
    String GET_CONTACTS_URL =
            "/v1/contacts";
    String UPDATE_CONTACTS_URL =
            "/v1/contacts";
    String DELETE_CONTACTS_URL =
            "/v1/contacts";
    String CLEAR_CONTACTS_URL =
            "/v1/contacts/clear";

    MediaType JSON = MediaType.get("application/json");
    OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    String AUTH = "Authorization";
    Gson GSON = new Gson();
    MediaType TEXT = MediaType.get("text/plain");
    MediaType HTML = MediaType.get("text/html");
    MediaType ZIP = MediaType.get("application/zip");
}
