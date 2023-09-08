package economic.main.payload.respone;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponce {

    private String message;
    private int status;
    private Object errors;
    private Object data;
}
