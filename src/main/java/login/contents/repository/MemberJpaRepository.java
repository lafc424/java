package login.contents.repository;

import jakarta.persistence.EntityManager;
import login.contents.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

    private final EntityManager em;

    public List<Member> searchMember(String searchMember) {
        return em.createQuery("select m from Member m where m.memberName like :searchMember or m.userId like :searchMember", Member.class)
                .setParameter("searchMember", "%" + searchMember + "%")
                .getResultList();
    }
}
