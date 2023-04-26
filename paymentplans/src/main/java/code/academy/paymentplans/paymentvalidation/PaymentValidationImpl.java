package code.academy.paymentplans.paymentvalidation;

import static code.academy.paymentplans.utils.Messages.NEGATIVE_PAYMENT_AMOUNT_NOT_ALLOWED;
import static code.academy.paymentplans.utils.Messages.PAYMENT_BIGGER_THAN_REMAINING_AMOUNT;
import static code.academy.paymentplans.utils.Messages.PAYMENT_PLAN_ALREADY_PAID;
import static code.academy.paymentplans.utils.Messages.THERE_IS_NO_SUCH_INDIVIDUAL;
import static code.academy.paymentplans.utils.Messages.THERE_IS_NO_SUCH_PAYMENT;
import static code.academy.paymentplans.utils.Messages.THERE_IS_NO_SUCH_PAYMENT_PLAN;

import code.academy.paymentplans.dao.individual.IndividualDao;
import code.academy.paymentplans.dao.payment.InstallmentDao;
import code.academy.paymentplans.dao.paymentsplan.PaymentPlanDao;
import code.academy.paymentplans.exeptions.PaymentPlanException;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("PaymentValidationImpl.class")
@RequiredArgsConstructor
public class PaymentValidationImpl implements PaymentValidation
{

  private final IndividualDao      individualDao;
  private final InstallmentDao     installmentDao;
  private final PaymentPlanDao     paymentPlanDao;

  @Override
  public void checkIfIndividualExist(String individualId)
  {
    if (!individualDao
        .findIndividualByID(individualId)
        .isPresent()) {
      throw new PaymentPlanException(THERE_IS_NO_SUCH_INDIVIDUAL);
    }
  }

  @Override
  public void checkIfPaymentPlanExist(String planId)
  {
    if (!paymentPlanDao
        .getPaymentPlanByID(planId)
        .isPresent()
    ) {
      throw new PaymentPlanException(THERE_IS_NO_SUCH_PAYMENT_PLAN);
    }
  }

  @Override
  public void checkIfInstallmentExist(String installmentId)
  {
    if (!installmentDao
        .findInstallmentByID(installmentId)
        .isPresent()) {
      throw new PaymentPlanException(THERE_IS_NO_SUCH_PAYMENT);
    }
  }

  @Override
  public void checkIfPaymentPlanAlreadyPaid(String planId)
  {
    if (paymentPlanDao
        .getPaymentPlanByID(planId)
        .isPresent()) {
      if (paymentPlanDao
          .getPaymentPlanByID(planId)
          .get()
          .getAmountToPay()
          .compareTo(BigDecimal.ZERO) == 0) {
        throw new PaymentPlanException(PAYMENT_PLAN_ALREADY_PAID);
      }
    }
  }

  @Override
  public void checkIfInstallmentBiggerThanRemainingAmount(String planId, BigDecimal installmentAmount)
  {
    if (paymentPlanDao
        .getPaymentPlanByID(planId)
        .isPresent()) {
      if (installmentAmount
          .compareTo(paymentPlanDao
              .getPaymentPlanByID(planId)
              .get()
              .getAmountToPay()) > 0) {
        throw new PaymentPlanException(PAYMENT_BIGGER_THAN_REMAINING_AMOUNT);
      }
    }
  }

  public void checkIfAmountLessOrEqualsToZero(BigDecimal amount)
  {
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new PaymentPlanException(NEGATIVE_PAYMENT_AMOUNT_NOT_ALLOWED);
    }
  }
}