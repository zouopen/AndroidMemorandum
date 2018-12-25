package com.example.sidebar.Utils;

import com.example.sidebar.Beans.ErrorBeans;
import com.example.sidebar.Beans.LoginDataBeans;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * GSON 解析
 */
public class GsonUtils {
    /**
     *
     * @param response 请求登录返回的response
     * @return 返回解析过的List
     * @throws JSONException
     */
    public static ErrorBeans ErrorBeans(String response) throws JSONException {
        if (response != null){
            ErrorBeans errorBeans = new ErrorBeans();
//            先转jsonObject
            JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
//             获取响应码
            int asInt = jsonObject.get("errorCode").getAsInt();
            if (asInt == 0) {
                JsonObject asJsonObject = jsonObject.getAsJsonObject("data");
                LoginDataBeans loginDataBeans = new LoginDataBeans();
                String username = asJsonObject.get("username").getAsString();
                loginDataBeans.setUsername(username);
                errorBeans.setData(loginDataBeans);
                return errorBeans;
            }else{
                errorBeans = LoginBean(response);
                return errorBeans;
            }
        }
        return null;
    }

    /**
     *
     * @param response 请求注册返回的response
     * @return 返回解析过的List
     * @throws JSONException
     */
    private static ErrorBeans LoginBean(String response) throws JSONException {
        if (response != null){
            ErrorBeans beans = new ErrorBeans();
            JSONObject jsonObject1 = new JSONObject(response);
            int errorCode     = jsonObject1.getInt("errorCode");
            String  ErrorMsg  = jsonObject1.getString("errorMsg");
            beans.setErrorCode(errorCode);
            beans.setErrorMsg(ErrorMsg);
            return beans;
        }else {
            return null;
        }
    }
    public static ErrorBeans errorBeans(String response) throws JSONException{
        if (response != null){
            JsonObject jsonObject = new JsonParser().parse(response).getAsJsonObject();
            int asInt = jsonObject.get("errorCode").getAsInt();
            Gson gson = new Gson();
            if (asInt == 1){
                ErrorBeans errorBeans = gson.fromJson(response,ErrorBeans.class);
            }else {
                return LoginBean(response);
            }
        }
        return null;
    }

}
