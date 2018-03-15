package id.ac.tazkia.auth.authserver.controller;

import id.ac.tazkia.auth.authserver.dao.ClientDao;
import id.ac.tazkia.auth.authserver.entity.Client;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/management/client")
public class ClientController {
    
    @Autowired
    private ClientDao clientDao;
    
    @GetMapping("/list")
    public String daftarClient(ModelMap mm, @PageableDefault(size = 10) Pageable page){
        Page<Client> result = clientDao.findAll(page);
        mm.addAttribute("dataClient", result);
        return "client/list";
    }
    
    @ModelAttribute("pageTitle")
    public String pageTitle() {
        return "Daftar Client";
    }
    
    @GetMapping("/form")
    public String showForm(@RequestParam(required = false) String id, ModelMap mm ){
        Client client = new Client();
        if (StringUtils.hasText(id)){
            Optional<Client> c = clientDao.findById(id);
            if (c.isPresent()){
                client = c.get();
            }
        }
        mm.addAttribute("client", client);
        return "client/form";
    }
    
    @PostMapping("/form")
    public String prossesForm(@ModelAttribute @Valid Client c, BindingResult errors, ModelMap mm){
        if(errors.hasErrors()){
            mm.addAttribute("client", c);
            return "client/form"; 
        }
        
        clientDao.save(c);
        return "redirect:/management/client/list";
    }
    
    @GetMapping("/delete")
    public String deleteData(@RequestParam(required = false) String id){
    
        clientDao.deleteById(id);
        return "redirect:/management/client/list";
    }
    
        
}
