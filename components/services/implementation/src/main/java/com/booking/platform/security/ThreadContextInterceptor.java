package com.booking.platform.security;

import com.booking.platform.iface.enums.Role;
import com.booking.platform.iface.exceptions.userException.UserForbiddenException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class ThreadContextInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public ThreadContextInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod handlerMethod)) {
            return true;
        }
        RequiredSuperAdmin methodAnnotation = handlerMethod.getMethodAnnotation(RequiredSuperAdmin.class);

        String token = jwtUtil.resolveToken(request);

        if (token != null) {
            Claims claims = jwtUtil.resolveClaims(token);
            Role role = Enum.valueOf(Role.class, (String) claims.get("role"));

            if (methodAnnotation != null && !role.equals(Role.SUPER_ADMIN)) {
                throw new UserForbiddenException("You are not allowed to access this resource");
            }
        }
        return true;
    }
}
