package economic.main.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationRefreshToken {

    @NotBlank(message = "Token isn't empty")
    private String tokenExpired;
    @NotBlank(message = "Refresh token isn't empty")
    private String refreshToken;

}
