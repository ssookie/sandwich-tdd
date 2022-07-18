package chap02.jyp;

import chap02.jyp.PasswordStrength;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordCheckerTest {

    PasswordChecker checker = new PasswordChecker();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = checker.check(password);
        assertEquals(expStr, result);
    }

    @Test
    @DisplayName("모든 케이스를 충족하는 경우")
    void all_case() {
        // 처음엔 이렇게 작성하다가.. 두번째 테스트 메서드부터 슬슬 리팩토링 생각하면서 공통부분 빼냄
        PasswordChecker checker = new PasswordChecker();
        PasswordStrength result = checker.check("abcd1234");
        assertEquals(PasswordStrength.강함, result);
    }

    @Test
    @DisplayName("길이가 8글자 미만이고 0부터 9사이 숫자 포함하는 경우")
    void letterCount8under() {
        // 테스트코드 만들면서도 계속 리팩토링 해나가면서..
        assertStrength("1234", PasswordStrength.보통);
    }

    @Test
    @DisplayName("길이가 8글자 이상이고 0부터 9사이 숫자 포함하지 않는 경우")
    void letterCount8upper() {
        assertStrength("abcdefgh", PasswordStrength.보통);
    }

    @Test
    @DisplayName("null")
    void nullCheck() {
        assertStrength("", PasswordStrength.INVALID);
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("모든 규칙을 충족하지 않는 경우")
    void nothing() {
        assertStrength("aa", PasswordStrength.약함);
    }

}
