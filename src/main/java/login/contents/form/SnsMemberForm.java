package login.contents.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnsMemberForm {

    private String userId;

    @NotEmpty(message = "회원이름을 입력해주세요.")
    private String memberName;
}
