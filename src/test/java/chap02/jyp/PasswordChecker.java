package chap02.jyp;

public class PasswordChecker {


    public PasswordStrength check(String password) {

        if (password == null || "".equals(password)) {
            return PasswordStrength.INVALID;
        }

        int meetCounts = 0; // 조건 충족 갯수

        if (password.length() >= 8) {
            //return PasswordStrength.보통;
            meetCounts++;
        }


        //boolean isContainsNum = false;
        for (char ch : password.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                //isContainsNum = true;
                meetCounts++;
                break;
            }
        }


        /*
        if (!isContainsNum) {
            return PasswordStrength.보통;
        }
        */

        if (meetCounts == 0 ) {
            return PasswordStrength.약함;
        } else if (meetCounts == 1) {
            return PasswordStrength.보통;
        }

        return PasswordStrength.강함;

    }

}
