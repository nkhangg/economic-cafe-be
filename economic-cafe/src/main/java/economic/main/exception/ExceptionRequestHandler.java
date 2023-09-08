package economic.main.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import economic.main.payload.respone.ApiResponce;


@ControllerAdvice
public class ExceptionRequestHandler extends ResponseEntityExceptionHandler{


        @Override
        @Nullable
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {


            List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

            List<String> listErrors = new ArrayList<>();

            for (FieldError fieldError : fieldErrors) {
                String errorMessage = fieldError.getDefaultMessage();
                listErrors.add(errorMessage);
            }

            ApiResponce apiResponceErrors = ApiResponce.builder()
                                            .message("User invalid")
                                            .status(status.value())
                                            .errors(listErrors)
                                            .build();

            

            return new ResponseEntity<>(apiResponceErrors,  headers, status);
        }

    
}
