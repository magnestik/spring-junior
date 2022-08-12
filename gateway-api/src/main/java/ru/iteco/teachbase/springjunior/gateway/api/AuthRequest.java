package ru.iteco.teachbase.springjunior.gateway.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRequest {
    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    private String audience;

    @JsonProperty("grant_type")
    private String grantType;
}
