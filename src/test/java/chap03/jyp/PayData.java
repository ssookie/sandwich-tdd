package chap03.jyp;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class PayData {

    private LocalDate billingDate;
    private int payAmount;
    private LocalDate firstBillingDate;

}
