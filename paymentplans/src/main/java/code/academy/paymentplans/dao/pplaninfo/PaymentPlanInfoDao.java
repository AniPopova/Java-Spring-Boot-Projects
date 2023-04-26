package code.academy.paymentplans.dao.pplaninfo;

import code.academy.paymentplans.dto.PaymentPlanInfo;
import java.util.List;
import java.util.Optional;

public interface PaymentPlanInfoDao
{

  List<PaymentPlanInfo> findPaymentPlanInfoByInstallmentId(String installmentId);

  List<PaymentPlanInfo> findPaymentPlanInfoByPlanId(String planId);

  List<PaymentPlanInfo> getAllPaymentPlansInfo();
}
