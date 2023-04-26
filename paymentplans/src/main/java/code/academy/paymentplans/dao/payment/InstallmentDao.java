package code.academy.paymentplans.dao.payment;

import code.academy.paymentplans.model.Installment;
import java.util.List;
import java.util.Optional;

public interface InstallmentDao
{
  String createInstallment(Installment installment);
  Optional<Installment> findInstallmentByID(String id);
  List<Installment> findAllInstallments();
  void updateInstallment(String installmentId, String planId);
  void deleteInstallment(String id);
}
