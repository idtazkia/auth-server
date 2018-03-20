package id.ac.tazkia.auth.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author agung
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){
        
        return "login";
     }

}
