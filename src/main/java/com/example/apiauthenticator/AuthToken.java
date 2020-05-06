package com.example.apiauthenticator;

import lombok.Getter;
import java.util.Map;

@Getter
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

    }

    /**
     *check whether the timestamp interval exceed the expiredTimeInterval
     *@author Garwen
     *@date 2020-05-06 17:45
     *@param null
     *@return
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
