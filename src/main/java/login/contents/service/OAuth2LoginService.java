package login.contents.service;

import login.contents.domain.Member;
import login.contents.domain.MemberSnsStatus;
import login.contents.domain.MemberStatus;
import login.contents.repository.MemberRepository;
import login.contents.util.PasswordCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2LoginService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final DefaultOAuth2UserService defaultOAuth2UserService = new DefaultOAuth2UserService();
    private final MemberRepository memberRepository;
    private final PasswordCreate passwordCreate;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = defaultOAuth2UserService.loadUser(userRequest);

        // 사용자 정보 가져오기
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        if (email == null || name == null) {
            throw new OAuth2AuthenticationException("Google 로그인 정보에 email 또는 name이 없습니다.");
        }

        Optional<Member> findMember = memberRepository.findByUserId(email);
        if (!findMember.isPresent()) {
            String snsEncodePassword = passwordCreate.randomPassword();

            Member member = new Member();
            member.setUserId(email);
            member.setMemberName(name);
            member.setPassword(snsEncodePassword);
            member.setMemberStatus(MemberStatus.USER);
            member.setMemberSnsStatus(MemberSnsStatus.GOOGLE);
            memberRepository.save(member);

        } else {
            log.info("이미 존재하는 사용자: {}", email);
        }

        return oauth2User;
    }
}

