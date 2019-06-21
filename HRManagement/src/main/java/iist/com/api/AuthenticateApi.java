package iist.com.api;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iist.com.dto.AuthenticationRequestDto;
import iist.com.dto.AuthenticationResponseDto;
import iist.com.dto.ResponseDto;
import iist.com.utils.Constrant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/auth")
public class AuthenticateApi {

	@Autowired
	AuthenticationManager auth;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateLogin(@RequestBody AuthenticationRequestDto dto){
		Authentication request = new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword());
		Authentication user = auth.authenticate(request);
		SecurityContextHolder.getContext().setAuthentication(user);
		
		User account = (User) user.getPrincipal();
        List<String> roles = account.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        
        Claims claims = Jwts.claims().setSubject(account.getUsername());
        claims.put("roles", roles);
		String token = Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, Constrant.SECRET)
				.setHeaderParam("typ", Constrant.TOKEN_ISSUER).setAudience(Constrant.TOKEN_AUDIENCE)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + Constrant.EXPIRATIONTIME))
				.compact();
		AuthenticationResponseDto auth = new AuthenticationResponseDto();
		auth.setToken(token);
		auth.setTokenType(Constrant.TOKEN_PREFIX);
		auth.setUsername(account.getUsername());
		ResponseDto<AuthenticationResponseDto> response = new ResponseDto<AuthenticationResponseDto>();
		response.setContent(auth);
		response.setMessage(HttpStatus.OK.name());
		return new ResponseEntity<>(response,HttpStatus.OK);
		
		
	}
}
