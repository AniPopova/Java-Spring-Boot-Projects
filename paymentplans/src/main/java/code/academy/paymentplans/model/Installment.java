package code.academy.paymentplans.model;

import static code.academy.paymentplans.utils.Messages.INVALID_ID;

import java.math.BigDecimal;
import java.sql.Date;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Installment
{

  @Size(min=32, max=32)
  @NotEmpty(message = INVALID_ID)
  private String     planId;

  @DecimalMin(value = "0.00", inclusive = false)
  @DecimalMax(value = "99999.99", inclusive = false)
  @Digits(integer = 5, fraction =2)
  private BigDecimal amountPaid;

}
