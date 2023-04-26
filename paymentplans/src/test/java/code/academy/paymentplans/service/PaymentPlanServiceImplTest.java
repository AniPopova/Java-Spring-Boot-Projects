package code.academy.paymentplans.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import code.academy.paymentplans.dao.paymentsplan.PaymentPlanDao;
import code.academy.paymentplans.dao.paymentsplan.PaymentPlanDaoImpl;
import code.academy.paymentplans.exeptions.PaymentPlanException;
import code.academy.paymentplans.model.PaymentPlan;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
@Sql(scripts = "classpath:test_correct_data.sql")
class PaymentPlanServiceImplTest
{

  @Mock
  private PaymentPlanDao         paymentPlanDao;
  @Autowired
  private PaymentPlanServiceImpl paymentPlanServiceImpl;

  @BeforeEach
  void setUp()
  {
    paymentPlanDao = Mockito.mock(PaymentPlanDaoImpl.class);
    paymentPlanServiceImpl = new PaymentPlanServiceImpl(paymentPlanDao);
  }

  @Test
  void checkConnection()
  {
    assertNotNull(paymentPlanServiceImpl);
    log.info("Test ok");
  }

  @Test
  void get_payment_plan_by_wrong_id()
  {
    String id = "ED958F6D39BE2805E053020011ACB405";
    assertThrows(PaymentPlanException.class, () -> paymentPlanServiceImpl.getPaymentPlanByID(id));

  }

  @Test
  void get_payment_plan_by_correct_id()
  {
    PaymentPlan expectedPaymentPlan = new PaymentPlan(
        "ED970DF338723ECFE053020011ACAE35", new BigDecimal("17000.00"));
    String id = "ED973B5BF3904236E053020011AC1512";
    when(paymentPlanDao.getPaymentPlanByID(id)).thenReturn(
        Optional.of(expectedPaymentPlan));
    PaymentPlan actualPaymentPlan = paymentPlanServiceImpl.getPaymentPlanByID(id);
    assertEquals(expectedPaymentPlan, actualPaymentPlan);
    verify(paymentPlanDao, times(1)).getPaymentPlanByID(id);
  }


  @Test
  void test_getAll_payment_plans()
  {
    List<PaymentPlan> expectedPlans = new ArrayList<>();
    when(paymentPlanDao.getAllPaymentPlans()).thenReturn(expectedPlans);
    List<PaymentPlan> actual = paymentPlanServiceImpl.getAllPaymentPlans();
    assertEquals(expectedPlans, actual);
    verify(paymentPlanDao, times(1)).getAllPaymentPlans();
  }

  @Test
  void test_delete_payment_plan_by_id()
  {
    String id = "ED970DF338723ECFE053020011ACAE36";
    paymentPlanServiceImpl.deletePaymentPlan(id);
    verify(paymentPlanDao, times(1)).deletePaymentPlan(id);
  }
}