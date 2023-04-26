package code.academy.paymentplans.service;

import code.academy.paymentplans.dao.pplaninfo.PaymentPlanInfoDao;
import code.academy.paymentplans.dto.PaymentPlanInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentPlanInfoServiceImpl implements PaymentPlanInfoService
{

  private final PaymentPlanInfoDao paymentPlanInfoDao;

  @Override
  public List<PaymentPlanInfo> findPaymentPlanInfoByInstallmentId(String installmentId)
  {
    return paymentPlanInfoDao.findPaymentPlanInfoByInstallmentId(installmentId);
  }

  @Override
  public List<PaymentPlanInfo> findPaymentPlanInfoByPlanId(String planId)
  {
    return paymentPlanInfoDao.findPaymentPlanInfoByPlanId(planId);
  }

  @Override
  public List<PaymentPlanInfo> getAllPaymentPlansInfo()
  {
    return paymentPlanInfoDao.getAllPaymentPlansInfo();
  }

}
