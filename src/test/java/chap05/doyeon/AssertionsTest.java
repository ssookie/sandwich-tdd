package chap05.doyeon;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Tag("assertions-test")
public class AssertionsTest {

    @Test
    void 테슽트_성공_단언메서드() {
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = LocalDate.now();
        assertEquals(localDate1, localDate2);
    }

    @Test
    void 테스트_실패() {
        fail("테스트 무조건 실패");
        System.out.println("실패~");
    }

    @Test
    void 테스트_실패_메서드_Exxception() {
        IllegalArgumentException thrown =  assertThrows(IllegalArgumentException.class, () -> {
            AuthService authService = new AuthService();
            authService.authenticate(null);
        });

        assertTrue(thrown.getMessage().contains("Exxception"));
    }

    @Test
    void assertAllTest() {
        assertAll(
                () -> assertEquals(3, 5/2),
                () -> assertEquals(4, 8/2),
                () -> assertEquals(2, 5/2)
        );
    }

    @Test
    void assertMessageTest() {
        // {1, 2, 3} {1, 1, 1}

        List<Integer> actual = Arrays.asList(1, 2, 3);
        List<Integer> expected = Arrays.asList(1, 1, 1);

        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i) , "actual index : " + i);
        }
    }


}
