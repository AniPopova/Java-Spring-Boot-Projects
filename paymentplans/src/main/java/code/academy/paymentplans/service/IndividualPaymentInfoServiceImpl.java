package code.academy.paymentplans.service;

import code.academy.paymentplans.dao.individualpayments.IndividualPaymentsInfoDao;
import code.academy.paymentplans.dto.IndividualPaymentsInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualPaymentInfoServiceImpl implements IndividualPaymentInfoService
{

  private final IndividualPaymentsInfoDao individualPaymentsInfo;

  @Override
  public List<IndividualPaymentsInfo> showIndividualPaymentsByPlanId(String planId)
  {
    return individualPaymentsInfo.showIndividualPaymentsByPlanId(planId);
  }

  @Override
  public List<IndividualPaymentsInfo> showIndividualPaymentsByPaymentId(String installmentId)
  {
    return individualPaymentsInfo.showIndividualPaymentsByPaymentId(installmentId);
  }

  @Override
  public List<IndividualPaymentsInfo> showIndividualPaymentsByIndividualId(String individualId)
  {
    return individualPaymentsInfo.showIndividualPaymentsByIndividualId(individualId);
  }

  @Override
  public List<IndividualPaymentsInfo> showAllIndividualPayments()
  {
    return individualPaymentsInfo.showAllIndividualPayments();
  }

}
