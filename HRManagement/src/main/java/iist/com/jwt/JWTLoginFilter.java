package iist.com.jwt;
 
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import iist.com.utils.Constrant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
 
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
 
    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }
 
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
 
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.printf("JWTLoginFilter.attemptAuthentication: username/password= %s,%s", username, password);
        System.out.println();
 
        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList()));
    }
 
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
 
    	User account = (User) authResult.getPrincipal();
        List<String> roles = account.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        
        Claims claims = Jwts.claims().setSubject(account.getUsername());
        claims.put("roles", roles);
		String token = Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, Constrant.SECRET)
				.setHeaderParam("typ", Constrant.TOKEN_ISSUER).setAudience(Constrant.TOKEN_AUDIENCE)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + Constrant.EXPIRATIONTIME))
				.compact();
        // Write Authorization to Headers of Response.
		response.addHeader(Constrant.HEADER_STRING, token);
 
        System.out.println("Authorization String=" + token);
    }
 
}