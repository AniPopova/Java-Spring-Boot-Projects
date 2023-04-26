package code.academy.paymentplans.paymentvalidation;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

public interface PaymentValidation
{
  void checkIfIndividualExist(String individualId);

  void checkIfPaymentPlanExist(String planId);

  void checkIfInstallmentExist(String installmentId);

  void checkIfPaymentPlanAlreadyPaid(String planId);

  void checkIfInstallmentBiggerThanRemainingAmount(String planId, BigDecimal amount);

  void checkIfAmountLessOrEqualsToZero(BigDecimal amount);
}
