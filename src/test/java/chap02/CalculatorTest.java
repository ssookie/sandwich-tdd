package chap02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test   //JUnit은 @Test 어노테이션을 붙인 메서드를 테스트 메서드로 인식한다.
    void plus() {
        int result = Calculator.plus(1, 2);
        assertEquals(3, result);    // 두 값이 같은지 비교, 충족하지 않는 경우 익셉션을 발생시킨다.
        assertEquals(5, Calculator.plus(4, 1));
    }
}