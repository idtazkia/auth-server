package id.ac.tazkia.auth.authserver.controller;

import id.ac.tazkia.auth.authserver.dao.PermissionDao;
import id.ac.tazkia.auth.authserver.entity.Permission;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/management/permission")
public class PermissionController {
    
    @Autowired
    private PermissionDao permissionDao;
    
    @GetMapping("/list")
    public String daftarPermission(ModelMap mm, @RequestParam(value = "key", required = false) String key,
            @PageableDefault(size = 10) Pageable page) {
        Page<Permission> result;
        
        if (key != null) {
            
            result = permissionDao.findBypermissionValueContainingIgnoreCase(key, page);
            mm.addAttribute("key", key);
        } else {
            
            result = permissionDao.findAll(page);
        }
        mm.addAttribute("data", result);
        return "permission/list";
    }
    
    @ModelAttribute("pageTitle")
    public String pageTitle() {
        return "Daftar Permission";
    }
    
    @GetMapping("/form")
    public String tampilkanForm(@RequestParam(required = false) String id, ModelMap mm) {
        Permission permission = new Permission();
        if (StringUtils.hasText(id)) {
            Optional<Permission> p = permissionDao.findById(id);
            if (p.isPresent()) {
                permission = p.get();
            }
        }
        
        mm.addAttribute("permission", permission);
        return "permission/form";
    }
    
    @PostMapping("/form")
    public String prossesForm(@ModelAttribute @Valid Permission p, BindingResult errors, ModelMap mm) {
        if (errors.hasErrors()) {
            mm.addAttribute("permission", p);
            
            return "permission/form";
        }
//         validasi value       \\
        Permission pLabel = permissionDao.findByPermissionLabel(p.getPermissionLabel());
        if (pLabel != null && !pLabel.getId().equals(p.getId())) {
            errors.rejectValue("permissionLabel", "permissionLabel", "Label Permission telah digunakan");
            mm.addAttribute("permission", p);
            
            return "permission/form";
        }
        
        Permission pValue = permissionDao.findByPermissionValue(p.getPermissionValue());
        if (pValue != null && !pValue.getId().equals(p.getId())) {
            errors.rejectValue("permissionValue", "permissionValue", "Value Permission telah digunakan");
            mm.addAttribute("permission", p);
            
            return "permission/form";
        }
        
        permissionDao.save(p);
        return "redirect:/management/permission/list";
    }
    
    @GetMapping("/delete")
    public String deleteData(@RequestParam(value = "id", required = false) String id) {
        permissionDao.deleteById(id);
        
        return "redirect:/management/permission/list";
        
    }
    
}
