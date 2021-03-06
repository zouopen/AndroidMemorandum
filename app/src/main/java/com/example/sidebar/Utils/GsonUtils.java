package com.example.sidebar.Utils;

import com.example.sidebar.Beans.ErrorBeans;
import com.example.sidebar.Beans.LoginDataBeans;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * GSON 解析
 */
public class GsonUtils {
    /**
     *
     * @param response 请求登录返回的response
     * @return 返回解析过的List
     * @throws JSONException
     * @deprecated 不推荐使用
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
                String icon = asJsonObject.get("icon").getAsString();
                loginDataBeans.setUsername(username);
//                errorBeans.setData(loginDataBeans);
                return errorBeans;
            }else{
                JSONObject jsonObject1 = new JSONObject(response);
                int errorCode     = jsonObject1.getInt("errorCode");
                String  ErrorMsg  = jsonObject1.getString("errorMsg");
                errorBeans.setErrorCode(errorCode);
                errorBeans.setErrorMsg(ErrorMsg);
                return errorBeans;
            }
        }
        return null;
    }

    /**
     * 更新版GSON解析
     * @param response
     * @return
     */
    public static ErrorBeans errorBeans(String response){
        return new Gson().fromJson(response,ErrorBeans.class);
    }
}
