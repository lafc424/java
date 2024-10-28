package login.contents.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String userId;
    private String memberName;
    private String memberStatus;
    private String joinTime;
}
