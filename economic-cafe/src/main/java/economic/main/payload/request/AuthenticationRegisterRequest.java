package economic.main.payload.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRegisterRequest {
    
    @NotBlank(message = "Name isn't blank")
    @Length(min = 4, message = "Name is invalid")
    private String username;

    @NotBlank(message = "Password isn't blank")
    private String password;

    @NotBlank(message = "Email isn't blank")
    @Email(message = "Email is invalid")
    private String email;
}
