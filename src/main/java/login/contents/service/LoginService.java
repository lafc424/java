package login.contents.service;

import login.contents.domain.Member;
import login.contents.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> loginMember = memberRepository.findByUserId(username);
        if (!loginMember.isPresent()) {
            throw new UsernameNotFoundException("없는 회원 아이디입니다.");
        }

        Member foundMember = loginMember.get();

        // UserDetails 객체를 반환
        return new User(foundMember.getUserId(), foundMember.getPassword(), Collections.emptyList());
    }
}
