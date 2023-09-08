package economic.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import economic.main.payload.request.AuthenticationLoginRequest;
import economic.main.payload.request.AuthenticationRefreshToken;
import economic.main.payload.request.AuthenticationRegisterRequest;
import economic.main.payload.respone.ApiResponce;
import economic.main.reponsitory.UserReponsitory;
import economic.main.service.AuthenticationService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    UserReponsitory userReponsitory;

    @Autowired
    AuthenticationService authenticationService;
    
    @PostMapping("/login")
    public ResponseEntity<ApiResponce> login(@RequestBody AuthenticationLoginRequest body){
        System.out.println("in login: " + body);
        return ResponseEntity.ok(authenticationService.login(body));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponce> register(@RequestBody @Valid AuthenticationRegisterRequest body){
        System.out.println("in register: " + body);
        return ResponseEntity.ok(authenticationService.register(body));
    }

    @PostMapping("/auth/refresh-token")
    public ResponseEntity<ApiResponce> refreshToken(@RequestBody @Valid AuthenticationRefreshToken body){
        return ResponseEntity.ok(authenticationService.refreshToken(body));
    }

    
}
