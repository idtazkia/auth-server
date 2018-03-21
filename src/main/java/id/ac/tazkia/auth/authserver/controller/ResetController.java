package id.ac.tazkia.auth.authserver.controller;

import id.ac.tazkia.auth.authserver.dao.ResetPasswordDao;
import id.ac.tazkia.auth.authserver.dao.UserDao;
import id.ac.tazkia.auth.authserver.dao.UserPasswordDao;
import id.ac.tazkia.auth.authserver.entity.ResetPassword;
import id.ac.tazkia.auth.authserver.entity.User;
import id.ac.tazkia.auth.authserver.entity.UserPassword;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Controller
public class ResetController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResetController.class);

    @Autowired private UserDao userDao;
    @Autowired private ResetPasswordDao resetDao;
    @Autowired private UserPasswordDao userPasswordDao;

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    public void reset(){}

    @RequestMapping(value = "/reset-sukses", method = RequestMethod.GET)
    public void sukses(){}

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public void blank(){}

    @PostMapping(value = "/reset")
    public String reset(@Valid @RequestParam String user){
        User u = userDao.findByUsername(user);

        if(u == null){
            LOGGER.info("Reset password untuk username belum terdaftar : {}", user);
            return "redirect:reset-sukses";
        }

        ResetPassword rp = resetDao.findByUser(u);

        if(rp == null){
            rp = new ResetPassword();
            rp.setUser(u);
        }

        rp.setCode(RandomStringUtils.randomAlphabetic(6));
        rp.setExpired(LocalDate.now().plusDays(3));

        resetDao.save(rp);
        return "redirect:reset-sukses";
    }


    @GetMapping(value = "/confirm")
    public String confirm(@RequestParam String code,Model m) {
        ResetPassword resetPassword = resetDao.findByCode(code);

        if (resetPassword == null){
            return "redirect:/404";
        }

        if (code != null && !code.isEmpty()){
            Optional<UserPassword> userPassword= userPasswordDao.findById(resetPassword.getUser().getId());
            if (userPassword != null){
                m.addAttribute("confirm", userPassword);
                System.out.println("as" + userPassword);
            }

        }

        if(resetPassword.getExpired().isBefore(LocalDate.now())){
            return "redirect:404";
        }

        m.addAttribute("code", code);
        return "/confirm";
    }

}
