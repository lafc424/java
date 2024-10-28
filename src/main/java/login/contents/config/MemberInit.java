package login.contents.config;

import jakarta.annotation.PostConstruct;
import login.contents.domain.Member;
import login.contents.domain.MemberSnsStatus;
import login.contents.domain.MemberStatus;
import login.contents.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberInit {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Member user  = new Member();
        user.setUserId("user");
        user.setMemberName("일반유저");
        user.setPassword(passwordEncoder.encode("123123123"));
        user.setMemberStatus(MemberStatus.USER);
        user.setMemberSnsStatus(MemberSnsStatus.BASIC);
        memberRepository.save(user);

        Member admin = new Member();
        admin.setUserId("admin");
        admin.setMemberName("관리자");
        admin.setPassword(passwordEncoder.encode("123123123"));
        admin.setMemberStatus(MemberStatus.ADMIN);
        admin.setMemberSnsStatus(MemberSnsStatus.BASIC);
        memberRepository.save(admin);

        Member owner = new Member();
        owner.setUserId("owner");
        owner.setMemberName("총책임자");
        owner.setPassword(passwordEncoder.encode("123123123"));
        owner.setMemberStatus(MemberStatus.OWNER);
        owner.setMemberSnsStatus(MemberSnsStatus.BASIC);
        memberRepository.save(owner);
    }
}
