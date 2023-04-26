package code.academy.paymentplans.service;

import code.academy.paymentplans.model.PaymentPlan;
import java.util.List;

public interface PaymentPlanService
{
  String createPaymentPlan(PaymentPlan paymentPlan);

  PaymentPlan getPaymentPlanByID(String id);

  List<PaymentPlan> getAllPaymentPlans();

  void updatePaymentPlan(PaymentPlan paymentPlan, String id);

  void deletePaymentPlan(String id);
}