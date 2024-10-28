package login.contents.controller;

import login.contents.form.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login/loginForm";
    }

    /**
     * SecurityConfig
     */
//    @PostMapping("/login")
//    public String loginAuth(@Valid LoginForm form, BindingResult result) {
//        if (result.hasErrors()) {
//            log.info("실패!!");
//            return "login/loginForm";
//        }
//
//        log.info("사용자 ID = {}", form.getUseId());
//        loginService.loadUserByUsername(form.getUseId());
//        return "redirect:/";
//    }
}