package economic.main.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import economic.main.payload.respone.ApiResponce;

@RestController
@RequestMapping("/api/")
public class Test {
     
    
    @PostMapping("login")
    public ResponseEntity<ApiResponce> login() {

        return ResponseEntity.ok(ApiResponce.builder().data("Khang").build());
    }

}
