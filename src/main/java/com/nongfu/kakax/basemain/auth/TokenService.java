package com.nongfu.kakax.basemain.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class TokenService {

    @Value("${token.expireTime}")
    private int amount;
    @Value("${token.secret}")
    private String secretKey;

    public String createToken(String subject) {
        Calendar calendar = GregorianCalendar.getInstance();
        Date now = calendar.getTime();
        calendar.add(GregorianCalendar.MINUTE, amount);
        Date exp = calendar.getTime();

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Claims getBody(String token) {
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
    }

    public Date getIssuedAt(String token) {
        return getBody(token).getIssuedAt();
    }
    public Date getExpiration(String token) {
        return getBody(token).getExpiration();
    }
    public String getSubject(String token) {
        return getBody(token).getSubject();
    }

    public boolean isTokenExpirte(String token) {
        Date exp = getBody(token).getExpiration();
        return exp.before(new Date());
    }
}
