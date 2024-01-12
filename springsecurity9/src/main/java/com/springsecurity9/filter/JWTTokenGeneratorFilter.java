package com.springsecurity9.filter;

import com.springsecurity9.constants.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // BasicAuthenticationFilter 이후에 실행하게 될 필터이기 때문에 Authentication 을 가져올 수 있다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (null != authentication) {
            SecretKey key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
            String jwt = Jwts.builder()
                    .issuer("Eazy Bank") // 발급 기관
                    .subject("JWT Token")
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .issuedAt(new Date()) // 발급 날짜
                    .expiration(new Date((new Date()).getTime() + 30000000)) // 만료 시간 ms로 넣음.
                    .signWith(key)
                    .compact();
            response.setHeader(SecurityConstants.JWT_HEADER, jwt);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/user"); // /user 호출 시에만 해당 필터가 작동하도록 작성
        // return 값이 false 일 경우에만 수행하기 때문에 앞에 ! 를 붙였다.
        // /user 라면 !true 라서 false 가 되고 다른 요청이라면 !false 라서 true 가 되어 작동하지 않는다.
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }
}
