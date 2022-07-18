package chap03.jyp;

import chap03.jyp.ExpiryDateCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.image.LookupOp;
import java.time.LocalDate;

public class ExpiryDateCalculatorTest {
    /*
        a. 서비스를 사용하려면 매달 1만원을 선불로 납부. 납부일 기준으로 한 달 뒤가 서비스 만료일이 됨.
        b. 2개월 이상 요금을 납부할 수 있음.
        c. 10만원을 납부하면 서비스를 1년 제공함.
     */

    // 1. 쉬운 것 부터 테스트
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        LocalDate billingDate = LocalDate.of(2019, 3, 1);
        int payAmout = 10_000;

        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmout);

        assertEquals(LocalDate.of(2019,4,1), expiryDate);

        // 2. 예를 추가하면서 구현을 일반화
        LocalDate billingDate2 = LocalDate.of(2019, 5, 5);
        int payAmout2 = 10_000;

        ExpiryDateCalculator cal2 = new ExpiryDateCalculator();
        LocalDate expiryDate2 = cal2.calculateExpiryDate(billingDate2, payAmout2);

        assertEquals(LocalDate.of(2019, 6, 5), expiryDate2);
    }

    // 3. 코르 정리 : 중복 제거
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨2() {
        assertExpiryDate(LocalDate.of(2019, 3, 1), 10_000, LocalDate.of(2019, 4, 1));
        assertExpiryDate(LocalDate.of(2019, 5, 5), 10_000, LocalDate.of(2019, 6, 5));
    }

    private void assertExpiryDate(LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(billingDate, payAmount);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

    // 4. 예외 상황 처리
    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(LocalDate.of(2019, 1, 31), 10_000, LocalDate.of(2019, 2, 28));
        assertExpiryDate(LocalDate.of(2019, 5, 31), 10_000, LocalDate.of(2019, 6, 30));
        assertExpiryDate(LocalDate.of(2020, 1, 31), 10_000, LocalDate.of(2020, 2, 29));
    }

    // 5. 다음 테스트 선택 : 다시 예외 상황
    /*
        쉬운 상황의 예
        - 2만원을 지불하면 만료일이 두 달 뒤가 된다.
        - 3만원을 지불하면 만료일이 세 달 뒤가 된다.

        예외 상황의 예
        - 첫 납부일이 2019-01-31이고 만료되는 2019-02-28에 1만원을 납부하면 다음 만료일은 2019-03-31 이다.
        - 첫 납부일이 2019-01-30이고 만료되는 2019-02-28에 1만원을 납부하면 다음 만료일은 2019-03-30 이다.
        - 첫 납부일이 2019-05-31이고 만료되는 2019-06-30에 1만원을 납부하면 다음 만료일은 2019-07-31 이다.

        --> 이전 테스트가 1개월 요금 지불을 기준으로 하므로, 1개월 예외상황에 대한 테스트를 먼저 하고
            그 다음 2개월 이상의 테스트를 진행하는 것이 좋을 것 같음.
     */

    // 6. 다음 테스트 추가하기 전에 리팩토링
    /*
        파라미터가 세 개 이상이면 가독성이 떨어지기 시작하므로 객체로 바꿔 한개로 줄이는 것을 고려해야 함.
        위 예제에서 (LocalDate billingDate, int payAmount, LocalDate expectedExpiryDate) 을 PayData 라는 객체로 만들 것임.
     */
    private void assertExpiryDate2(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate2(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨3() {

        // AS-IS
        assertExpiryDate(LocalDate.of(2019, 3, 1), 10_000, LocalDate.of(2019, 4, 1));
        assertExpiryDate(LocalDate.of(2019, 5, 5), 10_000, LocalDate.of(2019, 6, 5));

        // TO-BE
        assertExpiryDate2(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 4, 1));

        assertExpiryDate2(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 5))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 5));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음2() {

        // AS-IS
        assertExpiryDate(LocalDate.of(2019, 1, 31), 10_000, LocalDate.of(2019, 2, 28));
        assertExpiryDate(LocalDate.of(2019, 5, 31), 10_000, LocalDate.of(2019, 6, 30));
        assertExpiryDate(LocalDate.of(2020, 1, 31), 10_000, LocalDate.of(2020, 2, 29));

        // TO-BE
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

    private void assertExpiryDate3(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate3(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }


}
