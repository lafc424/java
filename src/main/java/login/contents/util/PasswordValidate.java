package login.contents.util;

public class PasswordValidate {

    public static void validatePassword(String password, String passwordValidate) {
        if (password == null || password.isEmpty()) {
            throw new IllegalStateException("비밀번호를 입력해주세요.");
        }

        if (!password.equals(passwordValidate)) {
            throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다.");
        }
    }
}
