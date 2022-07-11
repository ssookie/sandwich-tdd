package chap02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String input) {
        // null 또는 빈값 체크
        if (input == null || input.isEmpty()) {
            return PasswordStrength.INVALID;
        }

        int meetCounts = getMeetCriteriaCounts(input); // 조건 충족 갯수
        // 길이 체크
//       if (input.length() < 8) {
//            return PasswordStrength.NORMAL;
//       }

        // 숫자 포함 체크
//        boolean isContainsNum = meetsContainingNumberCriteria(input);
//        for (char ch : input.toCharArray()) {
//            if (ch >= '0' && ch <= '9') { // 숫자 포함하는 경우
//                isContainsNum = true;
//                break;
//            }
//        }
//        if (!isContainsNum) return PasswordStrength.NORMAL;

//        if (input.length() >= 8) meetCounts++;
//        if (meetsContainingNumberCriteria(input)) meetCounts++;
//
        if (meetCounts == 0) return PasswordStrength.WEEK;
        if (meetCounts == 1) return PasswordStrength.NORMAL;

        return PasswordStrength.STRONG;

    }

    private int getMeetCriteriaCounts(String input) {
        int meetCounts = 0;
        if (input.length() >= 8) meetCounts++;
        if (meetsContainingNumberCriteria(input)) meetCounts++;

        return meetCounts;
    }

    private boolean meetsContainingNumberCriteria(String input) {
        for (char ch : input.toCharArray()) {
            if (ch >= '0' && ch <= '9') { // 숫자 포함하는 경우
                return true;
            }
        }
        return false;
    }
}
