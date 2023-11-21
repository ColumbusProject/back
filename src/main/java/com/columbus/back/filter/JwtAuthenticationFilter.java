package com.columbus.back.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.columbus.back.provider.JwtProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  
  private final JwtProvider jwtProvider;

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

  try {

      String token = parseBearerToken(request);
      
      if (token == null) {
        filterChain.doFilter(request, response);
        return;
      }

      String userId = jwtProvider.validate(token);

      if (userId == null) {
        filterChain.doFilter(request, response);
        return;
      }

      AbstractAuthenticationToken authorizationToken = 
        new UsernamePasswordAuthenticationToken(userId, null, AuthorityUtils.NO_AUTHORITIES);
      authorizationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

      SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
      securityContext.setAuthentication(authorizationToken);

      SecurityContextHolder.setContext(securityContext);

    } catch (Exception exception) {
      exception.printStackTrace();
    }

    filterChain.doFilter(request, response);
  
  }

  private String parseBearerToken(HttpServletRequest request) {

    String authorization = request.getHeader("Authorization");

    boolean hasAuthorization = StringUtils.hasText(authorization);
    if (!hasAuthorization) return null;

    boolean isBearer = authorization.startsWith("Bearer ");
    if (!isBearer) return null;

    String token = authorization.substring(7);
    return token;

  }

}
