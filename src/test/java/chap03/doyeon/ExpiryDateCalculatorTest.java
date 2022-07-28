package chap03.doyeon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {

    @Test
    @DisplayName("만원_납부하면_한달뒤_만료일")
    void 만원_납부하면_한달뒤_만료일() {

        this.assertExpiryDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .payAmount(10_000)
                .build(),
                LocalDate.of(2019, 4, 1));

        this.assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 5))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 5));
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedDate, realExpiryDate);
    }

    // 예외사항 처리
    @Test
    @DisplayName("납부일과_한달_뒤_일자가_같지_않음")
    void 납부일과_한달_뒤_일자가_같지_않음() {

        this.assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 2, 28));

        this.assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 30));

        this.assertExpiryDate(PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2020, 2, 29));

    }

    @Test
    void 첫_납부일_만료일일자_다를떄_만원_납부() {
        this.assertExpiryDate(PayData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 3, 31));

    }

}
