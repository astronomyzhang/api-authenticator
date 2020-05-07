package com.example.apiauthenticator;

import com.example.apiauthenticator.Storage.CredentialStorage;
import com.example.apiauthenticator.Storage.Impl.TestCredentialStorage;
import lombok.Getter;
import java.util.Map;

@Getter
public class ApiRequest {
    private String appId;

    private String token;

    private String baseUrl;

    private long timestamp;

    public ApiRequest(String appId, String token, String baseUrl, long timestamp){
        this.appId = appId;
        this.token = token;
        this.baseUrl = baseUrl;
        this.timestamp = timestamp;
    }

    public static ApiRequest createFromFullUrl(String url){
        Map<String, String> paramMap = UrlUtils.resolveUrl(url);

        CredentialStorage cStorage = new TestCredentialStorage();
        String passwd = cStorage.getPasswordByAppId(paramMap.get("appId"));
        paramMap.put("passwd", passwd);

        //----------拼接字符串通过加密算法生成新token---------
        String token = AuthToken.authToken(paramMap.get("baseUrl"), System.currentTimeMillis(), paramMap).getToken();
        return new ApiRequest(paramMap.get("appId"), token, paramMap.get("baseUrl"), Long.valueOf(paramMap.get("ts")));
    }




}
