package com.school.management.configure;

import java.io.IOException;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.school.management.service.JWTServiceImpl;
import com.school.management.service.UserServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTServiceImpl jwtService;
	@Autowired
    private UserServiceImpl  userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");

        if(!StringUtils.hasLength(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String jwt = authHeader.substring(7);
            String userEmail = jwtService.extractUserName(jwt);

            if(StringUtils.hasLength(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = userService.loadUserByUsername(userEmail);
                if(jwtService.isTokenValid(jwt, userDetails)){
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    securityContext.setAuthentication(token);
                    SecurityContextHolder.setContext(securityContext);
                }
            }        } catch (ExpiredJwtException e) {
            // Handle expired JWT here
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("JWT Token has expired");
            return;  // Stop further processing
        }

//        if(StringUtils.hasLength(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = userService.loadUserByUsername(userEmail);
//
//            if(jwtService.isTokenValid(jwt, userDetails)){
//                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
//
//                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities()
//                );
//                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                securityContext.setAuthentication(token);
//                SecurityContextHolder.setContext(securityContext);
//            }
//        }
        filterChain.doFilter(request, response);
    }
}


//try {
//String jwt = authHeader.substring(7);
//String userEmail = jwtService.extractUserName(jwt);
//
//    if(StringUtils.hasLength(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null){
//UserDetails userDetails = userService.loadUserByUsername(userEmail);
//        if(jwtService.isTokenValid(jwt, userDetails)){
//        // Proceed with setting the authentication context
//        }
//        }
//        } catch (ExpiredJwtException e) {
//        // Handle expired JWT here
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//    response.getWriter().write("JWT Token has expired");
//    return;  // Stop further processing
//            }