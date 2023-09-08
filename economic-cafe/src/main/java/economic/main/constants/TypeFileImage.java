package economic.main.constants;

public enum TypeFileImage {
    CATEGORIES("categories"), 
    
    PRODUCT("products"), 
    
    
    
    ;

    private final String message;
    TypeFileImage(String message) { this.message = message; }
    public String value() { return message; }
}
