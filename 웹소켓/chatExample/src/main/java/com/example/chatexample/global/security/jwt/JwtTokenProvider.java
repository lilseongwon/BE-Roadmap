package com.example.chatexample.global.security.jwt;

import com.corundumstudio.socketio.SocketIOClient;
import com.example.chatexample.domain.auth.domain.RefreshToken;
import com.example.chatexample.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.chatexample.global.exception.ExpiredTokenException;
import com.example.chatexample.global.exception.InvalidTokenException;
import com.example.chatexample.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final AuthDetailsService authDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    public String createAccessToken(String email) {
        return createToken(email, "access", jwtProperties.getAccessExp());
    }

    public String createRefreshToken(String email) {

        String refreshToken = createToken(email, "refresh", jwtProperties.getRefreshExp());

        refreshTokenRepository.save(RefreshToken.builder()
                .email(email)
                .token(refreshToken)
                .expiration(jwtProperties.getRefreshExp() * 1000)
                .build());

        return refreshToken;
    }

    private String createToken(String email, String typ, Long exp) {

        return Jwts.builder()
                .setSubject(email)
                .claim("typ", typ)
                .signWith(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes()), SignatureAlgorithm.HS256)
                .setExpiration(new Date(System.currentTimeMillis() + exp * 1000))
                .setIssuedAt(new Date())
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getEmail(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        try {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix())
                && bearerToken.length() > jwtProperties.getPrefix().length() + 1) {
            return bearerToken.substring(jwtProperties.getPrefix().length() + 1);
        }
        return null;
    }

    public String resolveToken(SocketIOClient socketIOClient) {

        String bearerToken = socketIOClient.getHandshakeData().getHttpHeaders().get(jwtProperties.getHeader());

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix())
                && bearerToken.length() > jwtProperties.getPrefix().length() + 1) {
            return bearerToken.substring(jwtProperties.getPrefix().length() + 1);
        }
        return null;
    }

}