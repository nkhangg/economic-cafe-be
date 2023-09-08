package economic.main.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import economic.main.constants.MessageResponse;
import economic.main.service.impl.UserServiceImpl;
import economic.main.ultils.JwtTokenProvider;
import economic.main.ultils.ResponError;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private ResponError responError;




    private String getJwtFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            System.out.println("in filter token");
            String jwt = getJwtFromRequest(request);
            if (jwt != null) {
                String username = jwtTokenProvider.extractUsername(jwt);

                UserDetails userDetails = userServiceImpl.loadUserByUsername(username);

                if(jwtTokenProvider.isTokenValid(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                
            }
            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException expired) {
            responError.handleAuthorisationDenied(response,MessageResponse.TOKEN_EXPIRED);
            System.out.println(expired.getMessage());
        }catch(SignatureException invalid){
            responError.handleAuthorisationDenied(response, MessageResponse.TOKEN_INVALID);
        }

        System.out.println("out filter token");

        
        
    }

    // public static void handleAuthorisationDenied(HttpServletResponse response, String message) throws JsonProcessingException, IOException  {
    //     ApiResponce errorResponse = new ApiResponce();
    //     errorResponse.setError(message);
    //     errorResponse.setStatus(HttpStatus.UNAUTHORIZED);
    //     errorResponse.setData(null);

    //     response.setContentType("application/json");
    //     response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    //     response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    // }
}
