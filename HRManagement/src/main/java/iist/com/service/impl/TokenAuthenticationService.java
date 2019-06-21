package iist.com.service.impl;
 
import java.util.Collections;
import java.util.Date;
 
import javax.servlet.http.HttpServletRequest;
 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import iist.com.dto.AccountDto;
import iist.com.utils.Constrant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 
public class TokenAuthenticationService {
     
 
    public static String addAuthentication(AccountDto account) {
    	Claims claims = Jwts.claims().setSubject(account.getUsername());
    	claims.put("roles", account.getRoles());
        String JWT = Jwts.builder()
        		.setClaims(claims)
        		.setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + Constrant.EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, Constrant.SECRET).compact();
        return JWT;
    }
 
    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(Constrant.HEADER_STRING);
        if (token != null) {
            // parse the token.
        	
            String user = Jwts.parser().setSigningKey(Constrant.SECRET).parseClaimsJws(token.replace(Constrant.TOKEN_PREFIX, "")).getBody()
                    .getSubject();
 
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
        }
        return null;
    }
     
}