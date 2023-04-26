package code.academy.paymentplans.service;

import static code.academy.paymentplans.utils.Messages.THERE_IS_NO_SUCH_PAYMENT;
import static code.academy.paymentplans.utils.Messages.THERE_IS_NO_SUCH_PAYMENT_PLAN;

import code.academy.paymentplans.dao.payment.InstallmentDao;
import code.academy.paymentplans.dao.paymentsplan.PaymentPlanDao;
import code.academy.paymentplans.exeptions.PaymentPlanException;
import code.academy.paymentplans.model.Installment;
import code.academy.paymentplans.model.PaymentPlan;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InstallmentServiceImpl implements InstallmentService
{
  private final InstallmentDao installmentDao;

  @Override
  public String createInstallment(Installment installment, String idPaymentPlan)
  {
        return installmentDao.createInstallment(installment);
  }

  @Override
  public Installment findInstallmentByID(String installmentId)
  {
    return installmentDao
        .findInstallmentByID(installmentId)
        .orElseThrow(() -> new PaymentPlanException(THERE_IS_NO_SUCH_PAYMENT));
  }

  @Override
  public List<Installment> findAllInstallments()
  {
    return  installmentDao.findAllInstallments();
  }

  public void updateInstallment(String installmentId, String planId)
  {
    if (installmentDao
        .findInstallmentByID(installmentId)
        .isPresent()) {
      installmentDao.updateInstallment(installmentId, planId);
    }
    else {
      throw new PaymentPlanException(THERE_IS_NO_SUCH_PAYMENT);
    }
  }

  @Override
  public void deleteInstallment(String installmentId)
  {
    installmentDao.deleteInstallment(installmentId);
  }

}
