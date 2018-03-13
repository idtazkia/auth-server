package id.ac.tazkia.auth.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/user")
public class UserController {
    
    @GetMapping("/list")
    public String daftarUser(){
        return "user/list";
    }
    
    @ModelAttribute("pageTitle")
    public String pageTitle() {
        return "Daftar User";
    }
    
}
