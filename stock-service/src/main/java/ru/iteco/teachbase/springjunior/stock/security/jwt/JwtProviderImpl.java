package ru.iteco.teachbase.springjunior.stock.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

@Slf4j
@Component
public class JwtProviderImpl implements JwtProvider {
    @Value("${security.jwt.expiration}")
    private int expiration;
    @Value("${security.jwt.secret}")
    private String secret;

    @Override
    public String generateToken(String username) {
        Date expirationDate = Date.from(Instant.now().plusSeconds(expiration));
        Key key =  Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        return Jwts.builder()
            .claim(Claims.SUBJECT, username)
            .setExpiration(expirationDate)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
                .build().parseClaimsJws(token);
            return claimsJws != null;
        } catch (Exception e) {
            log.error("Invalid JWT", e);
            return false;
        }
    }

    @Override
    public String getUsernameFromToken(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)))
            .build()
            .parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }
}
