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
import realworld.user.User;
import realworld.user.service.UserService;
import realworld.utils.TokenService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


@SuppressWarnings("SpringJavaAutowiringInspection")
public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = StringUtils.substring(request.getHeader("Authorization"), 6);
        Optional.ofNullable(authorization).map(auth ->
                        {
                            Long idByToken = tokenService.getIdByToken(auth);
                            return idByToken;
                        }
                )
                .map(userId -> {
                    tokenService.updateExpiredTime(authorization);
                    User userByUserId = userService.findUserByUserId(userId);
                    return userByUserId;
                })
                .ifPresent(user -> {
                    LoginUser loginUser = new LoginUser(user);
                    loginUser.setToken(authorization);
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(loginUser, null,
                                    new ArrayList<>()));
                });
        filterChain.doFilter(request, response);
    }
}
