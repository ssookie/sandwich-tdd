package chap02.ssookie;

import chap02.PasswordStrength;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordCheckerTest {

    // 테스트 코드를 개발하면서 지속정인 리팩토링을 진행하라.
    PasswordChecker passwordChecker = new PasswordChecker();

    // 반복적인 구문은 새로운 메서드로 만든다.
    private void assertStrength(String password, PasswordStrengthEnum expStr) {
        PasswordStrengthEnum result = passwordChecker.check(password);
        assertEquals(expStr, result);
    }

    @Test
    @DisplayName("모든 케이스를 충족하는 경우") // 이 경우 테스트를 가장 쉽게 통과시킬 수 있다.
    void meetAllConditionTest() {
        assertStrength("abcd1234", PasswordStrengthEnum.강함);
    }

    @Test
    void 길이가_8글자_미만이고_0부터_9사이_숫자_포함하는_경우() {
        assertStrength("1234", PasswordStrengthEnum.보통);
    }

    @Test
    void 길이가_8글자_이상이고_0부터_9사이_숫자_포함하지_않는_경우() {
        assertStrength("abcdefgh", PasswordStrengthEnum.보통);
    }

    @Test
    void Null_또는_빈_값_입력() {
        assertStrength(null, PasswordStrengthEnum.INVALID);
        assertStrength("", PasswordStrengthEnum.INVALID);
    }

    @Test
    void 모든_규칙을_충족하지_않는_경우() {
        assertStrength("aaa", PasswordStrengthEnum.약함);
    }
}
