package login.contents.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "회원아이디를 입력해주세요.")
    @Size(min = 4, message = "최소 4자 이상이어야 합니다.")
    private String userId;

    @NotEmpty(message = "회원이름을 입력해주세요.")
    private String memberName;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    @Size(min = 8, message = "최소 8자 이상입니다.")
    private String password;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    private String passwordValidate;
}
