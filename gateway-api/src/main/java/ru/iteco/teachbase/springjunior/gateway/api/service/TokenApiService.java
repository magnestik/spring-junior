package ru.iteco.teachbase.springjunior.gateway.api.service;

public interface TokenApiService {
    String getToken(String clientId, String clientSecret, String audience);
}
