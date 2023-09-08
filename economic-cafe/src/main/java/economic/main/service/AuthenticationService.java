package economic.main.service;

import economic.main.payload.request.AuthenticationLoginRequest;
import economic.main.payload.request.AuthenticationRefreshToken;
import economic.main.payload.request.AuthenticationRegisterRequest;
import economic.main.payload.respone.ApiResponce;

public interface AuthenticationService {
    ApiResponce login(AuthenticationLoginRequest body);
    ApiResponce register(AuthenticationRegisterRequest body);
    ApiResponce refreshToken(AuthenticationRefreshToken body);
}
