package economic.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import economic.main.payload.respone.ApiResponce;
import economic.main.service.AppPageService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1/home-page")
public class AppPageController {

    @Autowired
    private AppPageService appPageService;
    
    @GetMapping("")
    public ResponseEntity<ApiResponce> homePage(HttpServletRequest request){
        System.out.println("in homePage api");
        return ResponseEntity.ok(appPageService.homePage(request));
    }
}
