package ru.iteco.teachbase.springjunior.stock.security.jwt;

public interface JwtProvider {

    String generateToken(String username);

    boolean validateToken(String token);

    String getUsernameFromToken(String token);
}
