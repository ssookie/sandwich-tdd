package chap02.doyeon;


public class PasswordChecker {
    public PasswordCheckEnum check(String password) {

        if (password == null || password.length() == 0) {
            return PasswordCheckEnum.invalid;
        }

        int meetCount = 0; // 조건 충족 개수

        if (password.length() >=  8) {
            meetCount++;
        }

        for (char ch : password.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                meetCount++;
                break;
            }
        }

        if (meetCount == 0) {
            return PasswordCheckEnum.약함;
        } else if (meetCount == 1) {
            return PasswordCheckEnum.보통;
        }

        return PasswordCheckEnum.강함;
    }
}
