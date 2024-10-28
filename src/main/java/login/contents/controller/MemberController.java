package login.contents.controller;

import jakarta.validation.Valid;
import login.contents.domain.Member;
import login.contents.domain.MemberSnsStatus;
import login.contents.dto.MemberDTO;
import login.contents.form.MemberForm;
import login.contents.form.SnsMemberForm;
import login.contents.repository.MemberSearch;
import login.contents.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/list")
    public String list(@ModelAttribute("memberSearch") MemberSearch memberSearch, Model model) {
        List<MemberDTO> memberList = new ArrayList<>();
        //리스트
        if (memberSearch.getMemberSearch() != null && !memberSearch.getMemberSearch().trim().isEmpty()) {
            memberList = memberService.memberSearch(memberSearch.getMemberSearch());

        } else {
            memberList = memberService.MemberList();
        }

        //로그인 유저 정보
        String loginMember = getCurrentUserId();
        Member loginMemberInfo = memberService.findMemberUserId(loginMember);

        //sns 로그인 유무
        boolean isSns = loginMemberInfo.getMemberSnsStatus() == MemberSnsStatus.BASIC;

        model.addAttribute("memberList", memberList);
        model.addAttribute("loginMemberInfo", loginMemberInfo);
        model.addAttribute("isSns", isSns);
        return "member/memberList";
    }

    @GetMapping("/list/edit/{memberId}")
    public String editMember(@PathVariable("memberId") Long memberId, Model model) {
        Member editMember = memberService.findMember(memberId);

        MemberForm editForm = new MemberForm();
        editForm.setUserId(editMember.getUserId());
        editForm.setMemberName(editMember.getMemberName());
        editForm.setPassword(editMember.getPassword());
        editForm.setPasswordValidate(editMember.getPassword());

        model.addAttribute("editForm", editForm);
        return "member/editMember";
    }

    @PostMapping("/list/edit/{memberId}")
    public String updateMember(@PathVariable("memberId") Long memberId,
                               @Valid @ModelAttribute("editForm") MemberForm memberForm,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            return "member/editMember";
        }

        if (memberForm.getPassword() == null || memberForm.getPasswordValidate() == null ||
                !memberForm.getPassword().equals(memberForm.getPasswordValidate())) {
            model.addAttribute("failMessage", "비밀번호가 일치하지 않습니다.");
            return "member/editMember";
        }

        return "redirect:/list";
    }

    @GetMapping("/list/editSns/{memberId}")
    public String editSnsMember(@PathVariable("memberId") Long memberId, Model model) {
        Member editMember = memberService.findMember(memberId);

        MemberForm editSnsForm = new MemberForm();
        editSnsForm.setUserId(editMember.getUserId());
        editSnsForm.setMemberName(editMember.getMemberName());
        model.addAttribute("editSnsForm", editSnsForm);
        return "member/editSnsMember";
    }

    @PostMapping("/list/editSns/{memberId}")
    public String updateSnsMember(@PathVariable("memberId") Long memberId,
                               @Valid @ModelAttribute("editSnsForm") SnsMemberForm snsMemberForm,
                               BindingResult result) {

        if (result.hasErrors()) {
            return "member/editSnsMember";
        }

        memberService.updateSnsMember(memberId, snsMemberForm);
        return "redirect:/list";
    }

    @PostMapping("/list/delete/{memberId}")
    public String deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.deleteMember(memberId);
        return "redirect:/list";
    }

    /**
     * 로그인 아이디 가져오기
     */
    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                //사용자의 아이디를 가져옴 (username)
                return ((UserDetails) principal).getUsername();

            } else if (principal instanceof OAuth2User) {
                OAuth2User oAuth2User = (OAuth2User) principal;
                return oAuth2User.getAttribute("email");

            } else {
                return principal.toString();
            }
        }

        return null;
    }
}
