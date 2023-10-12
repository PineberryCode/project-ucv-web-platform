package project.projectucvwebsystem.service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import project.projectucvwebsystem.entity.User;

@Service
public class JWTService {

    @Value("${security.jwt.secret-key}")
    private String SECRET_KEY;
    
    public String GenerateToken (User user, Map<String, Object> extraClaims) {
        LocalDateTime emitedTime = LocalDateTime.now();
        LocalDateTime expirationTime = emitedTime.plusMinutes(60);
        Date issuedAt = Date.from(emitedTime.atZone(ZoneId.systemDefault()).toInstant());
        Date expiration = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(user.getUsername())
        .setIssuedAt(issuedAt)
        .setExpiration(expiration)
        .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
        .signWith(GenerateKey(), SignatureAlgorithm.HS256)
        .compact();
    }

    private Key GenerateKey () {
        byte[] secretAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(secretAsBytes);
    }

    private Claims extraAllClaims (String jwt) {
        return Jwts.parserBuilder().setSigningKey(GenerateKey()).build()
        .parseClaimsJws(jwt).getBody();
    }

    public String extractUsername (String jwt) {
        return extraAllClaims(jwt).getSubject();
    }

}
