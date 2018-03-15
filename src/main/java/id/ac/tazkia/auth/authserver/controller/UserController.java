package id.ac.tazkia.auth.authserver.controller;

import id.ac.tazkia.auth.authserver.dao.ClientDao;
import id.ac.tazkia.auth.authserver.dao.RoleDao;
import id.ac.tazkia.auth.authserver.dao.UserDao;
import id.ac.tazkia.auth.authserver.dao.UserPasswordDao;
import id.ac.tazkia.auth.authserver.entity.User;
import id.ac.tazkia.auth.authserver.entity.UserPassword;
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
@RequestMapping("/management/user")
public class UserController {

    @Autowired
    public UserDao userDao;

    @Autowired
    public ClientDao clientDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    UserPasswordDao userPasswordDao;

    @GetMapping("/list")
    public String daftarUser(ModelMap mm, @RequestParam(value = "key", required = false) String key,
            @PageableDefault(size = 10) Pageable page) {
        Page<User> result;

        if (key != null) {
            result = userDao.findByusernameContainingIgnoreCase(key, page);
            mm.addAttribute("key", key);
        } else {
            result = userDao.findAll(page);
        }

        mm.addAttribute("data", result);
        return "user/list";
    }

    @ModelAttribute("pageTitle")
    public String pageTitle() {
        return "Daftar User";
    }

    @GetMapping("/form")
    public String formUser(ModelMap mm, @RequestParam(required = false) String id) {

        User user = new User();
        if (StringUtils.hasText(id)) {
            Optional<User> u = userDao.findById(id);
            if (u.isPresent()) {
                user = u.get();
            }
        }
        mm.addAttribute("listRole", roleDao.findAll());
        mm.addAttribute("listClient", clientDao.findAll());
        mm.addAttribute("user", user);
        return "user/form";
    }

    @PostMapping("/form")
    public String prosesForm(@ModelAttribute @Valid User user, BindingResult errors, ModelMap mm) {
        if (errors.hasErrors()) {
            mm.addAttribute("user", user);
            return "user/form";
        }

        User pUsername = userDao.findByUsername(user.getUsername());
        if (pUsername != null && !pUsername.getId().equals(user.getId())) {
            errors.rejectValue("username", "username", "username telah digunakan");
            mm.addAttribute("user", user);
            mm.addAttribute("listRole", roleDao.findAll());
            return "user/form";
        }
        if (StringUtils.hasText(user.getId())) {
            if (StringUtils.hasText(user.getPassword())) {
                UserPassword userPassword = new UserPassword();
                Optional<UserPassword> optional = userPasswordDao.findById(user.getId());
                if (optional.isPresent()) {
                    userPassword = optional.get();
                }
                userPassword.setPassword(user.getPassword());
                userPasswordDao.save(userPassword);
            }
        } else {
            String password = "admin";
            if (StringUtils.hasText(user.getPassword())) {
                password = user.getPassword();
            }

            UserPassword up = new UserPassword();
            up.setPassword(password);
            up.setUser(user);
            user.setUserPassword(up);
        }
        userDao.save(user);
        return "redirect:/management/user/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(required = false) String id) {
        userDao.deleteById(id);
        return "redirect:/management/user/list";
    }

}
