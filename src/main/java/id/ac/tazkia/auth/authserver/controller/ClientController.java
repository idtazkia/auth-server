package id.ac.tazkia.auth.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management/client")
public class ClientController {
    
    @GetMapping("/list")
    public String daftarClient(){
        return "client/list";
    }
    
    @ModelAttribute("pageTitle")
    public String pageTitle() {
        return "Daftar Client";
    }
    
}
