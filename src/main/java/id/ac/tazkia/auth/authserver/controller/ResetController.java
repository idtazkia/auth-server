package id.ac.tazkia.auth.authserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ResetController {

    @RequestMapping("/reset")
    public String reset(){

        return "reset";
    }

}
