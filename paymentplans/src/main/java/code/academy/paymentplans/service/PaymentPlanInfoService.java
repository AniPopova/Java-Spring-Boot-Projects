package code.academy.paymentplans.service;

import code.academy.paymentplans.dto.PaymentPlanInfo;
import java.util.List;

public interface PaymentPlanInfoService
{
  List<PaymentPlanInfo> findPaymentPlanInfoByInstallmentId(String installmentId);

  List<PaymentPlanInfo> findPaymentPlanInfoByPlanId(String planId);

  List<PaymentPlanInfo> getAllPaymentPlansInfo();
}
