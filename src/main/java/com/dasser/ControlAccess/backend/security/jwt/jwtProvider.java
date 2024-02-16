package com.dasser.ControlAccess.backend.security.jwt;
import com.dasser.ControlAccess.backend.security.Dto.jwtDto;
import com.dasser.ControlAccess.backend.security.Principal.PrincipalUser;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;

import java.security.Key;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class jwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(jwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expirationDays;


    public String generateToken(Authentication authentication) {
       ;
        PrincipalUser principal =(PrincipalUser) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .claim("roles", getRoles(principal))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+expirationDays*180000))
                .signWith(getSecret(secret))
                .compact();
    }

    public String getNombreUsuarioFromToken(String token) {

        return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody().getSubject();

    }
    private List<String> getRoles(PrincipalUser principal) {
        return principal.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("token mal formado");
        } catch (UnsupportedJwtException e) {
            logger.error("token no soportado");
        } catch (ExpiredJwtException e) {
            logger.error("token expirado");
        } catch (IllegalArgumentException e) {
            logger.error("token vacío");
        } catch (SignatureException e) {
            logger.error("fail en la firma");
        }
        return false;




    }
    public String refreshToken(jwtDto jwtDto) throws ParseException {
        try {
            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(jwtDto.getToken());
        } catch (ExpiredJwtException e) {
            JWT jwt = JWTParser.parse(jwtDto.getToken());
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            String nombreUsuario = claims.getSubject();
            List<String> roles = (List<String>) claims.getClaim("roles");

            return Jwts.builder()
                    .setSubject(nombreUsuario)
                    .claim("roles", roles)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + expirationDays))
                    .signWith(getSecret(secret))
                    .compact();
        }
        return null;
    }
    private Key getSecret(String secret){
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
