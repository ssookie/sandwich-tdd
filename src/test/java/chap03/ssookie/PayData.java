package chap03.ssookie;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class PayData {
    private LocalDate billingDate;
    private int payAmount;
    private LocalDate firstBillingDate;
}
