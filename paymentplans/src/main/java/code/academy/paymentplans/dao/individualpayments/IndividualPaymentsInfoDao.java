package code.academy.paymentplans.dao.individualpayments;

import code.academy.paymentplans.dto.IndividualPaymentsInfo;
import java.util.List;


public interface IndividualPaymentsInfoDao
{
 List<IndividualPaymentsInfo> showIndividualPaymentsByPlanId(String id);
 List<IndividualPaymentsInfo> showIndividualPaymentsByPaymentId(String id);
 List<IndividualPaymentsInfo> showIndividualPaymentsByIndividualId(String id);
 List<IndividualPaymentsInfo> showAllIndividualPayments();
}
