package code.academy.paymentplans.dao.paymentsplan;

import code.academy.paymentplans.model.PaymentPlan;
import java.util.List;
import java.util.Optional;

public interface PaymentPlanDao
{
 String createPaymentPlan(PaymentPlan paymentPlan);

 Optional<PaymentPlan> getPaymentPlanByID(String id);

 List<PaymentPlan> getAllPaymentPlans();

 void updatePaymentPlan(PaymentPlan paymentPlan, String id);

 void deletePaymentPlan(String id);
}
