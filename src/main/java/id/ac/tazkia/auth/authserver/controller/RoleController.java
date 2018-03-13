package id.ac.tazkia.auth.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/role")
public class RoleController {
    
    @GetMapping("/list")
    public String daftarRole(){
        return "role/list";
    }
    
    @ModelAttribute("pageTitle")
    public String pageTitle() {
        return "Daftar Role";
    }
    
}
