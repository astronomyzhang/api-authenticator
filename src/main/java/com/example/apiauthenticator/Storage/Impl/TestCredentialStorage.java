package com.example.apiauthenticator.Storage.Impl;

import com.example.apiauthenticator.Storage.CredentialStorage;

public class TestCredentialStorage implements CredentialStorage {
    @Override
    public String getPasswordByAppId(String appId) {
        return "1234567";
    }
}
