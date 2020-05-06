package com.example.apiauthenticator;

import lombok.Getter;
import java.util.HashMap;
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
        String[] urlParts = url.split("\\?");
        String baseUrl = urlParts[0];
        String[] params = urlParts[1].split("&");

        Map<String, String> paramMap = new HashMap<>();
        for(String param:params){
            String[] paramSet = param.split("=");
            paramMap.put(paramSet[0], paramSet[1]);
        }

        //----------拼接字符串通过加密算法生成新token---------
        String token = ;
        return new ApiRequest(paramMap.get("appId"), token, baseUrl, paramMap.get("ts"));
    }


}
