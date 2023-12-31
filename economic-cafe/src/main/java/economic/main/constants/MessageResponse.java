package economic.main.constants;



public enum MessageResponse {

    SUCCESS("Successfully"), 
    
    FAILURE("Failures"), 
    
    INTERNAL_SERVER_ERROR("Internal Server Error"),

    TOKEN_EXPIRED("Can't authentication because Token Expired"),

    TOKEN_INVALID("Can't authentication because Token Invalid"),
    
    USERNAME_ALREADY("Username already"),

    EMAIl_ALREADY("Email already"),

    INVALID_REQUEST("The request invalid")
    
    ;

    private final String message;
    MessageResponse(String message) { this.message = message; }
    public String getValue() { return message; }


}
