package economic.main.ultils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import economic.main.constants.MessageResponse;
import economic.main.payload.respone.ApiResponce;
import economic.main.reponsitory.UserReponsitory;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ResponError {
    
    @Autowired
    private ObjectMapper objectMapper;


    public void handleAuthorisationDenied(HttpServletResponse response, MessageResponse message) throws JsonProcessingException, IOException  {
        ApiResponce errorResponse = new ApiResponce();
        errorResponse.setErrors(true);
        errorResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setMessage(MessageResponse.TOKEN_EXPIRED.getValue());
        errorResponse.setData(null);


        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    
}
