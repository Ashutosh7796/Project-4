package com.Salaryfy.JWT;

import com.Salaryfy.Security.UserDetailsCustom;
import io.jsonwebtoken.Claims;
import java.security.Key;
import java.util.Date;

public interface JwtService {

    Claims extractClaims(String token);

    Key getKey();

    String generateToken(UserDetailsCustom userDetailsCustom);

    boolean isValidToken(String token);
    boolean isTokenExpired(String token);

    Date extractExpiration(String token);

}
