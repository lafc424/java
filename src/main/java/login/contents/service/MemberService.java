package login.contents.service;

import login.contents.domain.Member;
import login.contents.dto.MemberDTO;
import login.contents.form.MemberForm;
import login.contents.form.SnsMemberForm;
import login.contents.repository.MemberJpaRepository;
import login.contents.repository.MemberRepository;
import login.contents.util.PasswordValidate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public List<MemberDTO> MemberList() {
        List<Member> members = memberRepository.findAll();
        return memberDto(members);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    public Member findMemberUserId(String userId) {
        return memberRepository.findByUserId(userId).orElse(null);
    }

    public List<MemberDTO> memberSearch(String memberSearch) {
        if (memberSearch == null || memberSearch.isEmpty()) {
            return null;

        } else {
            String member = memberSearch.replace(" ", "");
            List<Member> members = memberJpaRepository.searchMember(member);
            return memberDto(members);
        }
    }

    @Transactional
    public void updateMember(Long memberId, MemberForm memberForm) {
        Member member = memberRepository.findById(memberId).get();

        PasswordValidate.validatePassword(memberForm.getPassword(), memberForm.getPasswordValidate());
        String hashPassword = passwordEncoder.encode(memberForm.getPassword());

        member.setMemberName(memberForm.getMemberName());
        member.setPassword(hashPassword);
        memberRepository.save(member);
    }

    @Transactional
    public void updateSnsMember(Long memberId, SnsMemberForm memberForm) {
        Member member = memberRepository.findById(memberId).get();

        member.setMemberName(memberForm.getMemberName());
        memberRepository.save(member);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    private List<MemberDTO> memberDto(List<Member> members) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return members.stream()
                .map(member -> new MemberDTO(
                        member.getId(),
                        member.getUserId(),
                        member.getMemberName(),
                        member.getMemberSnsStatus().toString(),
                        member.getJoinTime().format(formatter)))
                .collect(Collectors.toList());
    }
}
