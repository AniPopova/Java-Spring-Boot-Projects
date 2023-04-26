package code.academy.paymentplans.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentPlanInfo
{
  private String planId;
  private BigDecimal amountToPay;
  private String installmentId;
  private BigDecimal amountPaid;

}
