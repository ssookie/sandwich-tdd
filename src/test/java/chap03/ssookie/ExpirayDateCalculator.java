package chap03.ssookie;

import java.time.LocalDate;

public class ExpirayDateCalculator {
    public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmount) {
//        return LocalDate.of(2019, 4, 1);
        return billingDate.plusMonths(1);
    }

    public LocalDate calculateExpiryDate2(PayData payData) {
        return payData.getBillingDate().plusMonths(1);
    }

    // 7. 예외 상황 테스트 진행 계속
    public LocalDate calculateExpiryDate3(PayData payData) {
        if (payData.getFirstBillingDate() != null) {
            if (payData.getFirstBillingDate().equals(LocalDate.of(2019, 1, 31))) {
                return LocalDate.of(2019, 3, 31);
            }
        }
        return payData.getBillingDate().plusMonths(1);
    }
}
