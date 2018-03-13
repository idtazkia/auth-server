package id.ac.tazkia.auth.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/permission")
public class PermissionController {
    
    @GetMapping("/list")
    public String daftarPermission(){
        return "permission/list";
    }
    
    @ModelAttribute("pageTitle")
    public String pageTitle() {
        return "Daftar Permission";
    }
    
}
