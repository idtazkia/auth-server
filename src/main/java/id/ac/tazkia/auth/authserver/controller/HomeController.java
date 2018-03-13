package id.ac.tazkia.auth.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String dashboard(){
        return "home";
    }
    
    @ModelAttribute("pageTitle")
    public String pageTitle() {
        return "Home";
    }
    
}
