package id.ac.tazkia.auth.authserver.controller;

import id.ac.tazkia.auth.authserver.dao.RoleDao;
import id.ac.tazkia.auth.authserver.entity.Role;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/management/role")
public class RoleController {
    
    @Autowired
    private RoleDao roleDao;
    
    
    @GetMapping("/list")
    public String daftarRole(ModelMap mm, @PageableDefault(size = 10) Pageable pageable) {
        Page<Role> result = roleDao.findAll(pageable);
        mm.addAttribute("data", result);
        return "role/list";
    }
    
    @GetMapping("/form")
    public String tampilkanForm(@RequestParam(required = false) String id, ModelMap mm) {
        Role role = new Role();
        if(StringUtils.hasText(id)){
            Optional <Role> r = roleDao.findById(id);
            if(r.isPresent()){
                role = r.get();
            }
        }
    
        mm.addAttribute("role", role);
        return "role/form";
    
    }
    
    @PostMapping("/form")
    public String prosesForm(@Valid Role role, BindingResult errors, ModelMap mm){
        if (errors.hasErrors()) {
            mm.addAttribute("role", role);
            mm.addAttribute("listRole", roleDao.findAll());
        }
    
        // =========== Validasi Name =============
        Role rName = roleDao.findByName(role.getName());
        if (rName != null && !rName.getId().equals(role.getId())) {
            errors.rejectValue("name", "name", "Nama role telah digunakan");
            mm.addAttribute("role", role);
            mm.addAttribute("listPermission", roleDao.findAll());
            return "role/form";
        }
        
        roleDao.save(role);
        return "redirect:/management/role/list";
    }
    
    @GetMapping("/delete")
    public String deleteData(@RequestParam(required = false) String id) {
        roleDao.deleteById(id);
        return "redirect:/management/role/list";
    }
    
    @ModelAttribute("pageTitle")
    public String pageTitle() {
        return "Daftar Role";
    }
    
}
