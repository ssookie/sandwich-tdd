package chap05;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("assertions-test")
public class AssertionsTest {

    @Test
    @Tag("basic-test")
    @Timeout(1)
    void 테스트_성공_단언메서드() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now();
        assertEquals(date1, date2);
    }

    @Test
    @Disabled
    void 테스트_실패_fail메서드() {
        // fail();
        fail("테스트 무조건 실패할테야.");
        System.out.println("요게 실행 될까?");
    }

    @Test
    void 테스트_실패_fail메서드2() {
        try {
            AuthService authService = new AuthService();
            authService.authenticate(null);
        } catch(IllegalArgumentException e) {
            fail();
            System.out.println("Exception occured.");
        }
    }

    @Test
    @DisplayName("익셉션 발생 테스트")
    void assertThrowsTest() {
        // assertThrows()는 발생한 익셉션 객체를 리턴한다.
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> {
                    AuthService authService = new AuthService();
                    authService.authenticate(null);
                }
                );

        // 발생한 익셉션을 이용해서 추가로 검증이 필요하면, assertThrows()가 리턴한 익셉션 객체를 사용한다.
        assertTrue(thrown.getMessage().contains("auth"));
    }

    @Test
    @Disabled
    void assertAllTest() {
        assertAll(
                () -> assertEquals(3, 5/2),
                () -> assertEquals(4, 2*2),
                () -> assertEquals(6, 11/2)
        );
    }

    @Test
    void assertMessageTest() {
        List<Integer> actual = Arrays.asList(1, 2, 3);
        List<Integer> expected = Arrays.asList(1, 1, 1);

        for (int i=0; i<expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i), "actual index : " + i);
        }
    }
}
