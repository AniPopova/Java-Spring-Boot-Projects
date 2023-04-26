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
public class IndividualPaymentsInfo
{
  private String individualId;
  private String name;
  private String planId;
  private String paymentId;
  private BigDecimal creditAmount;
  private BigDecimal paymentMade;
  private BigDecimal remainingAmount;
}
