package economic.main.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import economic.main.constants.MessageResponse;
import economic.main.model.User;
import economic.main.payload.request.AuthenticationLoginRequest;
import economic.main.payload.request.AuthenticationRefreshToken;
import economic.main.payload.request.AuthenticationRegisterRequest;
import economic.main.payload.respone.ApiResponce;
import economic.main.payload.respone.AuthenticationResponce;
import economic.main.reponsitory.UserReponsitory;
import economic.main.service.AuthenticationService;
import economic.main.ultils.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService{
    @Autowired
    private UserReponsitory userReponsitory;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ApiResponce login(AuthenticationLoginRequest body) {

        // check username or password from request body is null
        if(body.getUsername() == null || body.getPassword() == null){
            return ApiResponce.builder().errors(true).status(HttpStatus.BAD_REQUEST.value()).message(MessageResponse.FAILURE.getValue()).data(null).build();
        }


        // get user from infomation request body
        User user = this.validateFromLoginRequest(body.getUsername(), body.getPassword());


        // checking user is null
        if(user == null){
            return ApiResponce.builder()
            .errors(true)
            .message(MessageResponse.FAILURE.getValue())
            .status(HttpStatus.NOT_FOUND.value())
            .data(null).build();
        }

        

        // all good
        String token = jwtTokenProvider.generateToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        // set refresh token to database
        user.setRefreshToken(refreshToken);
        // userReponsitory.setRefreshToken(refreshToken, user.getId());

        

        // create Authentication responce
        AuthenticationResponce data = AuthenticationResponce.builder()
        .user(user)
        .token(token)
        .refreshToken(refreshToken)
        .build();


        // System.out.println(data);

        // return data
        return ApiResponce.builder()
        .errors(false)
        .status(HttpStatus.OK.value())
        .data(data).message("")
        .message(MessageResponse.SUCCESS.getValue())
        .build();
    }

    @Override
    public ApiResponce register(AuthenticationRegisterRequest body) {


        //check email and username are already exists
        if(userReponsitory.existsByUsername(body.getUsername()) && userReponsitory.existsByEmail(body.getEmail())){
            return ApiResponce.builder().errors(true)
            .status(HttpStatus.ALREADY_REPORTED.value())
            .message(MessageResponse.USERNAME_ALREADY.getValue() + " " + MessageResponse.EMAIl_ALREADY.getValue())
            .data(null)
            .build();

        }


        // check if the username already exists
        if(userReponsitory.existsByUsername(body.getUsername())){
            return ApiResponce.builder().errors(true).status(HttpStatus.ALREADY_REPORTED.value()).message(MessageResponse.USERNAME_ALREADY.getValue()).data(null).build();
        }

        // check if the email already exists
        if(userReponsitory.existsByEmail(body.getEmail())){
            return ApiResponce.builder().errors(true).status(HttpStatus.ALREADY_REPORTED.value()).message(MessageResponse.EMAIl_ALREADY.getValue()).data(null).build();
        }

        // haspassword 
        String hashPassword = passwordEncoder.encode(body.getPassword());


        //all good
        User user = new User();
        user.setUsername(body.getUsername());
        user.setPassword(hashPassword);
        user.setEmail(body.getEmail());

        // generate token
        String token = jwtTokenProvider.generateToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        // set refrestoken to user
        user.setRefreshToken(refreshToken);

        return ApiResponce.builder()
        .errors(false)
        .status(HttpStatus.OK.value())
        .message(MessageResponse.SUCCESS.getValue())
        .data(
            AuthenticationResponce.builder()
            .user(userReponsitory.save(user))
            .token(token)
            .refreshToken(refreshToken)
            .build())
        .build();
    }


    private User validateFromLoginRequest(String username, String password) {
        User user = userReponsitory.findByUsername(username).orElse(null);
        if(user != null){
            // checking password is matched request password
            if(passwordEncoder.matches(password, user.getPassword())){
                return user;
            }
        }
        return null;
    }

    @Override
    public ApiResponce refreshToken(AuthenticationRefreshToken body) {

        // check if token is expired then response error
        try {
            boolean isTokenExpired = jwtTokenProvider.isTokenExpired(body.getTokenExpired());

            
            if(!isTokenExpired){
                return ApiResponce.builder().errors(true).status(HttpStatus.BAD_REQUEST.value()).message(MessageResponse.INVALID_REQUEST.getValue()).data(null).build();
            }
        } catch (ExpiredJwtException e) {
            // check if refresh isn't expired handle re login response
            try {
                // check to throw error if refresh token expired
                jwtTokenProvider.isTokenExpired(body.getRefreshToken());

                // extract username from refresh token
                String username = jwtTokenProvider.extractUsername(body.getRefreshToken());

                // handle error if username is null
                if(username == null){
                    return ApiResponce.builder().errors(true).status(HttpStatus.NOT_FOUND.value()).message(MessageResponse.FAILURE.getValue()).data(null).build();
                }

                // get user from username
                User user = userReponsitory.findByUsername(username).orElse(null);

                // handle error if user is null
                if(user == null){
                    return ApiResponce.builder().errors(true).status(HttpStatus.NOT_FOUND.value()).message(MessageResponse.FAILURE.getValue()).data(null).build();
                }

                // check current refresh token with token got from username
                if(!body.getRefreshToken().equals(user.getRefreshToken())){
                    return ApiResponce.builder().errors(true).status(HttpStatus.NOT_EXTENDED.value()).message(MessageResponse.TOKEN_INVALID.getValue()).data(null).build();
                }

                // all good
                // generate new token
                String newToken = jwtTokenProvider.generateToken(user);
                 // generate new refresh token
                String newRefreshToken = jwtTokenProvider.generateRefreshToken(user);

                // remove old refresh token, add a new refresh token
                userReponsitory.setRefreshToken(newRefreshToken, user.getId());
                

                return ApiResponce.builder()
                .errors(false)
                .status(HttpStatus.OK.value())
                .message(MessageResponse.SUCCESS.getValue())
                .data(
                    AuthenticationResponce.builder()
                    .user(user)
                    .token(newToken)
                    .build())
                .build();

            } catch (ExpiredJwtException ex) {

                // handle error if refresh toekn is expired (if in case response data = 'Relogin' for client relogin )
                return ApiResponce.builder().errors(false).status(HttpStatus.EXPECTATION_FAILED.value()).message(MessageResponse.FAILURE.getValue()).data("Relogin").build();
            }
        }

        // response if token isn't expired
        return ApiResponce.builder().errors(false).status(HttpStatus.OK.value()).message(MessageResponse.EMAIl_ALREADY.getValue()).data(null).build();
    }



    
    
}
