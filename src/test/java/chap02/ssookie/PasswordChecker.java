package chap02.ssookie;

public class PasswordChecker {
    public PasswordStrengthEnum check(String password) {

        if (password == null || password.isEmpty()) {
            return PasswordStrengthEnum.INVALID;
        }

        // 조건 충족 갯수 (Refactoring을 위한 변수)
        int meetCounts = 0;

        // 조건 하나씩 충족하는지 확인
        if (password.length() >= 8) {
            meetCounts ++;
        }

        for (char c : password.toCharArray()) {
            if (c >= '0' && c <= '9') {
                meetCounts ++;
                break;
            }
        }

        switch (meetCounts) {
            case 0 : return PasswordStrengthEnum.약함;
            case 1 : return PasswordStrengthEnum.보통;
        }

        return PasswordStrengthEnum.강함;
    }
}
