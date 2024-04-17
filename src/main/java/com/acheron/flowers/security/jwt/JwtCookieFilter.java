//package com.acheron.flowers.security.jwt;
//
//
//import io.jsonwebtoken.ExpiredJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
//@Component
//@RequiredArgsConstructor
//public class JwtCookieFilter extends OncePerRequestFilter {
//    private final JwtUtil jwtUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        Cookie[] cookies = request.getCookies();
//        Cookie cookie = Arrays.stream(cookies).filter(cookie1 -> cookie1.getName().equals("access")).findFirst().orElse(null);
//        String jwt = null;
//        String username = null;
//        if (cookie != null) {
//            String accessToken = cookie.getValue();
//            if(accessToken!=null && !accessToken.isBlank()){
//
//                jwt = accessToken.substring(7);
//            }
//            try {
//                username = jwtUtil.getUsername(jwt);
//            } catch (ExpiredJwtException e) {
//                response.sendError(403, "Token has expired");
//                return;
//            } catch (Exception e) {
//                response.sendError(403, "Token is  invalid");
//                return;
//            }
//        }
////        else {
////            response.sendError(401);
////            return;
////        }
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                    username,
//                    null,
//                    jwtUtil.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
//            );
//            SecurityContextHolder.getContext().setAuthentication(token);
//        }
//        filterChain.doFilter(request, response);
//    }
//}
