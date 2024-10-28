package login.contents.controller;

import jakarta.validation.Valid;
import login.contents.form.MemberForm;
import login.contents.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "join/joinForm";
    }

    @PostMapping("/join")
    public String joinMember(@Valid MemberForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "join/joinForm";
        }

        if (!validateId(form, model)) {
            return "join/joinForm";
        }

        if (!validatePassword(form, model)) {
            return "join/joinForm";
        }

        joinService.save(form);
        return "redirect:/";
    }

    private boolean validateId(MemberForm form, Model model) {
        if (!joinService.isValidateId(form)) {
            model.addAttribute("validateId", "영어와 숫자로만 가능합니다.");
            return false;
        }

        if (joinService.duplicateId(form)) {
            model.addAttribute("duplicateMessage", "중복된 아이디가 있습니다.");
            return false;
        }

        return true;
    }

    private boolean validatePassword(MemberForm form, Model model) {
        String password = form.getPassword();
        String passwordValidate = form.getPasswordValidate();

        if (password == null || !password.equals(passwordValidate)) {
            model.addAttribute("failPassword", "비밀번호가 일치하지 않습니다.");
            return false;
        }

        return true;
    }
}
