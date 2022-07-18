package chap03.jyp;

import java.time.LocalDate;

public class ExpiryDateCalculator {


    public LocalDate calculateExpiryDate(LocalDate billingDate, int payAmout) {
        //return LocalDate.of(2019, 4, 1);

        // 2. 예를 추가하면서 구현을 일반화
        return billingDate.plusMonths(1);
    }

    // 6. 다음 테스트 추가하기 전에 리팩토링
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
