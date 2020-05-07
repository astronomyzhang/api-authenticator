package com.example.apiauthenticator;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class Tstest {
    @Test
    public static void main(String[] args){
        log.info("The timestamp is : {}", System.currentTimeMillis());
    }
}
