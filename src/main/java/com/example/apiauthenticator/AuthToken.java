package com.example.apiauthenticator;

import lombok.Builder;
import lombok.Getter;
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Getter
@Builder
public class AuthToken {
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1*60*1000;
    private String token;

    private long createTime;

    private long expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;

    public AuthToken(String token, long createTime){
        this.token = token;
        this.createTime = createTime;
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval){
        this(token, createTime);
        this.expiredTimeInterval = expiredTimeInterval;
    }

    public static AuthToken authToken(String baseUrl, long createTime, Map<String, String> params){
        //params should be arranged as some kind of order
        //how to ignore the order of params

        //拼接字符串
        String candidate = baseUrl + "?appId=" + params.get("appId") + "&passwd=" + params.get("passwd") + "&ts=" + Long.toString(createTime);

        return AuthToken.builder()
                .token(getSHA256Str(candidate)).createTime(createTime)
                .build();
    }

    private static String getSHA256Str(String str){
        MessageDigest messageDigest;
        String endStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA256");
            byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
            endStr = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return endStr;
    }

    /**
     *check whether the timestamp interval exceed the expiredTimeInterval
     *@author Garwen
     *@date 2020-05-06 17:45
     *@param
     *@throws
     */
    public boolean isExpired(){
        return System.currentTimeMillis() - createTime > expiredTimeInterval;
    }

    /**
     *check whether the two token is the same
     *@author Garwen
     *@date 2020-05-06 18:00
     *@param token
     *@return boolean
     *@throws
     */
    public boolean match(AuthToken token){
        return this.token.equals(token);
    }
}
