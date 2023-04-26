package code.academy.paymentplans.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import code.academy.paymentplans.dao.payment.InstallmentDao;
import code.academy.paymentplans.exeptions.PaymentPlanException;
import code.academy.paymentplans.model.Installment;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
class InstallmentServiceImplTest
{

  @Mock
  private InstallmentDao installmentDao;

  @Autowired
  private InstallmentServiceImpl installmentService;

  @BeforeEach
  void setUp()
  {
    installmentDao = Mockito.mock(InstallmentDao.class);
    installmentService = new InstallmentServiceImpl(installmentDao);
  }

  @Test
  void check_connection()
  {
    assertNotNull(installmentService);
    log.info("Test ok");
  }

  @Test
  void create_installment_with_correct_data()
  {
    String planId = "EDAA244FB5334791E053020011AC1CE4";
    Installment installment =
        new Installment(planId, new BigDecimal("350"));
    String returnedId = "";
    when((installmentDao).createInstallment(installment))
        .thenReturn(returnedId);
    String actual = installmentService.createInstallment(installment, planId);
    assertEquals(actual, returnedId);
    verify(installmentDao, times(1)).createInstallment(installment);
    log.info("Test ok");
  }

  @Test
  void create_installment_with_wrong_data()
  {
    String planId = "EDAA244FB5334791E0530";
    Installment installment =
        new Installment(planId, new BigDecimal("350"));
    String returnedId = "";
    when((installmentDao).createInstallment(installment))
        .thenThrow(PaymentPlanException.class);
    assertThrows(PaymentPlanException.class, () -> installmentService.createInstallment(installment, planId));
    verify(installmentDao, times(1)).createInstallment(installment);
    log.info("Test ok");

  }

  @Test
  void find_all_installments()
  {
    List<Installment> expectedInstallments = new ArrayList<>();
    when(installmentDao.findAllInstallments()).thenReturn(expectedInstallments);
    List<Installment> actualInstallments = installmentService.findAllInstallments();
    assertEquals(expectedInstallments, actualInstallments);
    verify(installmentDao, times(1)).findAllInstallments();
  }


  @Test
  void delete_installment_by_id()
  {
    String installmentId = "EDAE25832C3B1691E053020011AC7BDE";
    installmentService.deleteInstallment(installmentId);
    verify(installmentDao, times(1)).deleteInstallment(installmentId);
    log.info("Test ok");
  }
}