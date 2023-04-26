package code.academy.paymentplans.service;

import code.academy.paymentplans.model.Installment;
import java.util.List;

public interface InstallmentService
{
  String createInstallment(Installment installment, String idPaymentPlan);
  Installment findInstallmentByID(String id);
  List<Installment> findAllInstallments();
  void updateInstallment(String installmentId, String planId);
  void deleteInstallment(String id);
}
