package umb.khh.edudate.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final AuthProvider authProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if(header != null) {
            String[] parts = header.split(" ");

            if(parts.length == 2 && "Bearer".equals(parts[0]))
                try {
                    SecurityContextHolder.getContext().setAuthentication(authProvider.checkJWTToken(parts[1]));
                } catch (Exception e) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("Invalid or expired token");
                    throw e;
                }
            }


        filterChain.doFilter(request, response);
        }
}
