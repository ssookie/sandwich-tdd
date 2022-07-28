package chap02.doyeon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordCheckTest {

    PasswordChecker checker = new PasswordChecker();


    @Test
    void 모든케이스충족() {
        this.check("abcd1234", PasswordCheckEnum.강함);
    }


    @Test
    void 길이_8글자미만_0부터9사이숫자포함() {
        this.check("1234", PasswordCheckEnum.보통);
    }


    @Test
    void 길이_8글자이상_0부터9사이숫자미포함() {
        this.check("abcdefasdf", PasswordCheckEnum.보통);
    }

    @Test
    void Null_또는_빈값_입력() {
        this.check(null, PasswordCheckEnum.invalid);
        this.check("", PasswordCheckEnum.invalid);
    }

    @Test
    void 모든규칙_충족_안함() {
        this.check("aaaa", PasswordCheckEnum.약함);
    }

    private void check(String value, PasswordCheckEnum checkEnum) {
        PasswordCheckEnum result = checker.check(value);
        assertEquals(checkEnum, result);
    }
}
