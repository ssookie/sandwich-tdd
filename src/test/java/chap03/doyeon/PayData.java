package chap03.doyeon;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PayData {
    LocalDate billingDate;
    int payAmount;
    LocalDate firstBillingDate;
}
