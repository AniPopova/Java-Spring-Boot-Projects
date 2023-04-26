package code.academy.paymentplans.service;

import static code.academy.paymentplans.utils.Messages.THERE_IS_NO_SUCH_PAYMENT_PLAN;

import code.academy.paymentplans.dao.paymentsplan.PaymentPlanDao;
import code.academy.paymentplans.exeptions.PaymentPlanException;
import code.academy.paymentplans.model.PaymentPlan;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentPlanServiceImpl implements PaymentPlanService
{

  private final PaymentPlanDao paymentPlanDao;

  @Override
  public String createPaymentPlan(PaymentPlan paymentPlan)
  {
    return paymentPlanDao.createPaymentPlan(paymentPlan);
  }

  @Override
  public PaymentPlan getPaymentPlanByID(String planId)
  {
    return paymentPlanDao
        .getPaymentPlanByID(planId)
        .orElseThrow(() -> new PaymentPlanException(THERE_IS_NO_SUCH_PAYMENT_PLAN));
  }

  @Override
  public List<PaymentPlan> getAllPaymentPlans()
  {
    return paymentPlanDao.getAllPaymentPlans();
  }

  @Override
  public void updatePaymentPlan(PaymentPlan paymentPlan, String planId)
  {
    paymentPlanDao.updatePaymentPlan(paymentPlan, planId);
  }

  @Override
  public void deletePaymentPlan(String planId)
  {
    paymentPlanDao.deletePaymentPlan(planId);
  }

}
