package ru.iteco.teachbase.springjunior.gateway.api;

public interface TokenApiService {
    String getToken(String clientId, String clientSecret, String audience);
}
