package login.contents.service;

import login.contents.domain.Member;
import login.contents.domain.MemberSnsStatus;
import login.contents.domain.MemberStatus;
import login.contents.form.MemberForm;
import login.contents.repository.MemberRepository;
import login.contents.util.PasswordValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JoinService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String USERID_PATTERN = "^[a-zA-Z0-9]+$";

    @Transactional
    public void save(MemberForm form) {
        PasswordValidate.validatePassword(form.getPassword(), form.getPasswordValidate());

        String hashPassword = passwordEncoder.encode(form.getPassword());

        Member member = new Member();
        member.setUserId(form.getUserId());
        member.setMemberName(form.getMemberName());
        member.setPassword(hashPassword);
        member.setMemberStatus(MemberStatus.USER);
        member.setMemberSnsStatus(MemberSnsStatus.BASIC);

        memberRepository.save(member);
    }

    public boolean isValidateId(MemberForm form) {
        return Pattern.matches(USERID_PATTERN, form.getUserId());
    }

    public boolean duplicateId(MemberForm form) {
        Optional<Member> duplicateUserId = memberRepository.findByUserId(form.getUserId());
        return duplicateUserId.isPresent();
    }
}
