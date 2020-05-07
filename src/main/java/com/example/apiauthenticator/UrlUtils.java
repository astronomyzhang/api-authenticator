package com.example.apiauthenticator;

import java.util.HashMap;
import java.util.Map;

public class UrlUtils {
    public static Map<String, String> resolveUrl(String url){
        Map<String, String> paramMap = new HashMap<>();
        String[] urlParts = url.split("\\?");
        String baseUrl = urlParts[0];
        paramMap.put("baseUrl", baseUrl);
        String[] params = urlParts[1].split("&");
        for(String param:params){
            String[] paramSet = param.split("=");
            paramMap.put(paramSet[0], paramSet[1]);
        }

        return paramMap;
    }


}
