package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = passwordStrengthMeter.meter(password);
        assertEquals(expStr, result);
    }

    @Test
    void 모든_조건_충족() {
     assertStrength("abcd4567", PasswordStrength.STRONG);
    }

    @Test
    void 길이가_8글자_미만이고_0부터_9사이_숫자_포함() {
        assertStrength("4567", PasswordStrength.NORMAL);
    }

    @Test
    void 길이가_8글자_이상이고_0부터_9사이_숫자_미포함() {
        assertStrength("abcdefgh", PasswordStrength.NORMAL);
    }

    @Test
    void 모든_규칙_충족하지_않음() {
        assertStrength("a", PasswordStrength.WEEK);
    }

    @Test
    void 값이_null_또는_빈값() {
        assertStrength(null, PasswordStrength.INVALID);
        assertStrength("", PasswordStrength.INVALID);
    }

}
