package economic.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import economic.main.model.Role;
import economic.main.model.User;
import economic.main.reponsitory.RoleReponsitory;
import economic.main.reponsitory.UserReponsitory;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserReponsitory userRep;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleReponsitory roleReponsitory;


    @GetMapping("")
    public List<User> selectAll() {
        return userRep.findAll();
    }

    @GetMapping("/add")
    public Object add() {

        // String password = passwordEncoder.encode("123");


        

        // User user = new User();
        // user.setUsername("vipn");
        // user.setPassword(password);

        return userRep.existsByEmail("ngan@gmail.com");
    }
}
