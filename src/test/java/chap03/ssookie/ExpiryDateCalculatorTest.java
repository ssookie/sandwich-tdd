package chap03.ssookie;

import chap03.jyp.ExpiryDateCalculator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    // 1. 쉬운 것 부터 테스트
    @Test
    void 만원_납부하면_한달_뒤가_만료일() {
        LocalDate billingDate = LocalDate.of(2019, 3, 1);
        int payAmount = 10000;

        ExpirayDateCalculator cal = new ExpirayDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);

        assertEquals(LocalDate.of(2019, 4, 1), expiryDate);

        // 2. 예를 추가하면서 구현을 일반화
        LocalDate billingDate2 = LocalDate.of(2019, 5, 1);
        int payAmount2 = 10000;

        ExpirayDateCalculator cal2 = new ExpirayDateCalculator();
        LocalDate expiryDate2 = cal2.calculateExpiryDate(billingDate2, payAmount2);

        assertEquals(LocalDate.of(2019, 6, 1), expiryDate2);
    }

    // 3. 코드 정리: 중복을 제거하기 위해 메서드로 분리하자.
    @Test
    void 만원_납부하면_한달_뒤가_만료일2() {
        assertExpiryDate(LocalDate.of(2019, 3, 1), 10_000, LocalDate.of(2019, 4, 1));
        assertExpiryDate(LocalDate.of(2019, 5, 1), 10_000, LocalDate.of(2019, 6, 1));
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpirayDateCalculator cal = new ExpirayDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(billingDate, payAmount);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

    // 6. 다음 테스트 추가하기 전에 리팩토링
    /*
        파라미터가 세 개 이상이면 가독성이 떨어지기 시작하므로 객체로 바꿔 한개로 줄이는 것을 고려해야 함.
        위 예제에서 (LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) 을 PayData 라는 객체로 만들 것임.
     */
    private void assertExpiryDate2(PayData payData, LocalDate expectedExpiryDate) {
        ExpirayDateCalculator cal = new ExpirayDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate2(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

    // 4. 예외 상황 처리 (날짜에서 복잡한 것 - 윤년 계산 등)
    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(LocalDate.of(2019, 1, 31), 10_000, LocalDate.of(2019, 2, 28));
        assertExpiryDate(LocalDate.of(2019, 5, 31), 10_000, LocalDate.of(2019, 6, 30));
        assertExpiryDate(LocalDate.of(2020, 1, 31), 10_000, LocalDate.of(2020, 2, 29));
    }

    // 5. 다음 테스트 선택: 다시 예외 상황 / 코드의 수정 범위가 적은 CASE 먼저 처리한다.
    @Test
    void 만원_납부하면_한달_뒤가_만료일3() {

        // AS-IS
        assertExpiryDate(LocalDate.of(2019, 3, 1), 10_000, LocalDate.of(2019, 4, 1));
        assertExpiryDate(LocalDate.of(2019, 1, 31), 10_000, LocalDate.of(2019, 2, 28));

        // TO-BE
        // assertExpiryDate2(new PayData(LocalDate.of(2019, 3, 1), 10_000), LocalDate.of(2019, 4, 1));
        assertExpiryDate2(  // Builder Pattern 사용
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 4, 1)
        );
    }

    // BuilderPattern 적용하기
    @Test
    void 납부일과_한달_뒤_일자가_같지_않음2() {
        assertExpiryDate2(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 2, 28));
        assertExpiryDate2(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 30));
        assertExpiryDate2(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2020, 2, 29));
    }

    // 7. 예외 상황 테스트 진행 계속
    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        PayData payData= PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate3(payData, LocalDate.of(2019, 3, 31));
    }

    public void assertExpiryDate3(PayData payData, LocalDate expectedExpiryDate) {
        ExpirayDateCalculator cal = new ExpirayDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate3(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }
}
