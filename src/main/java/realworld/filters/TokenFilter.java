package realworld.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.function.ServerRequest;
import realworld.user.LoginUser;
import realworld.user.service.UserService;
import realworld.utils.TokenService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


@Component
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        Optional.ofNullable(authorization).map(auth -> tokenService.getIdByToken(auth))
                .map(userId -> {
                    tokenService.updateExpiredTime(authorization);
                    return userService.findUserByUserId(userId);
                })
                .ifPresent(user -> {
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>()));
                });
        filterChain.doFilter(request, response);
    }
}
