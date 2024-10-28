package login.contents.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String userId;
    private String memberName;
    private String password;
    private LocalDateTime joinTime;

    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Enumerated(EnumType.STRING)
    private MemberSnsStatus memberSnsStatus;

    @PrePersist
    public void register() {
        this.joinTime = LocalDateTime.now();
    }
}
