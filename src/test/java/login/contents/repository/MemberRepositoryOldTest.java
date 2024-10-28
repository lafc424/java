package login.contents.repository;

import login.contents.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryOldTest {

    @Autowired
    MemberRepositoryOld memberRepositoryOld;

    @Test
    public void joinUser() {

    }

}