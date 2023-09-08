package economic.main.payload.respone;

import economic.main.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponce {
    private User user;
    private String token;
    private String refreshToken;


    public String getToken(){
        return "Bearer " + this.token;
    }

}
