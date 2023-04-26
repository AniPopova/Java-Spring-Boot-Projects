package code.academy.paymentplans.service;

import code.academy.paymentplans.dto.IndividualPaymentsInfo;
import java.util.List;

public interface IndividualPaymentInfoService
{
  List<IndividualPaymentsInfo> showIndividualPaymentsByPlanId(String id);
  List<IndividualPaymentsInfo> showIndividualPaymentsByPaymentId(String id);
  List<IndividualPaymentsInfo> showIndividualPaymentsByIndividualId(String id);
  List<IndividualPaymentsInfo> showAllIndividualPayments();
}