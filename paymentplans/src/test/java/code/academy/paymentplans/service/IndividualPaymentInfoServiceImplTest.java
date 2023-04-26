package code.academy.paymentplans.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import code.academy.paymentplans.dao.individualpayments.IndividualPaymentsInfoDao;
import code.academy.paymentplans.dao.individualpayments.IndividualPaymentsInfoDaoImpl;
import code.academy.paymentplans.dto.IndividualPaymentsInfo;
import code.academy.paymentplans.exeptions.PaymentPlanException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
class IndividualPaymentInfoServiceImplTest
{

  @Mock
  private IndividualPaymentsInfoDao individualPaymentsInfoDao;

  @Autowired
  private IndividualPaymentInfoServiceImpl individualPaymentInfoService;

  @BeforeEach
  void setUp()
  {
    individualPaymentsInfoDao = Mockito.mock(IndividualPaymentsInfoDaoImpl.class);
    individualPaymentInfoService = new IndividualPaymentInfoServiceImpl(individualPaymentsInfoDao);
  }


  @Test
  void check_connection()
  {
    assertNotNull(individualPaymentInfoService);
    log.info("Test ok");
  }


  @Test
  void test_show_individual_payments_by_existing_plan_id()
  {
    String id = "EDAA244FB5334791E053020011AC1CE4";
    List<IndividualPaymentsInfo> expected = new ArrayList<>();
    when(individualPaymentsInfoDao.showIndividualPaymentsByPlanId(id)).thenReturn(expected);
    List<IndividualPaymentsInfo> actuals = individualPaymentInfoService.showIndividualPaymentsByPlanId(id);
    assertEquals(expected, actuals);
    verify(individualPaymentsInfoDao, times(1)).showIndividualPaymentsByPlanId(id);
    log.info("Test ok");
  }

  @Test
  void test_show_individual_payments_by_non_existing_plan_id()
  {
    String id = "EDAA244FB5334791E053020011AC1CE5";
    when(individualPaymentsInfoDao.showIndividualPaymentsByPlanId(id)).thenThrow(PaymentPlanException.class);
    assertThrows(PaymentPlanException.class, ()-> individualPaymentInfoService.showIndividualPaymentsByPlanId(id));
    verify(individualPaymentsInfoDao, times(1)).showIndividualPaymentsByPlanId(id);
    log.info("Test ok");

  }

  @Test
  void test_show_individual_payments_by_existing_payment_id()
  {
    String id = "EDAA244FB5354791E053020011AC1CE4";
    List<IndividualPaymentsInfo> expected = new ArrayList<>();
    when(individualPaymentsInfoDao.showIndividualPaymentsByPlanId(id)).thenReturn(expected);
    List<IndividualPaymentsInfo> actuals = individualPaymentInfoService.showIndividualPaymentsByPaymentId(id);
    assertEquals(expected, actuals);
    verify(individualPaymentsInfoDao, times(1)).showIndividualPaymentsByPaymentId(id);
    log.info("Test ok");
  }

  @Test
  void test_show_individual_payments_by_non_existing_payment_id()
  {
    String id = "EDAA244FB5354791E053020011AC1C77";
    when(individualPaymentsInfoDao.showIndividualPaymentsByPaymentId(id)).thenThrow(PaymentPlanException.class);
    assertThrows(PaymentPlanException.class, ()-> individualPaymentInfoService.showIndividualPaymentsByPaymentId(id));
    verify(individualPaymentsInfoDao, times(1)).showIndividualPaymentsByPaymentId(id);
    log.info("Test ok");
  }

  @Test
  void test_show_individual_payments_by_existing_individual_id()
  {
    String id = "EDAA156A43B846C5E053020011AC5C39";
    List<IndividualPaymentsInfo> expected = new ArrayList<>();
    when(individualPaymentsInfoDao.showIndividualPaymentsByIndividualId(id)).thenReturn(expected);
    List<IndividualPaymentsInfo> actuals = individualPaymentInfoService.showIndividualPaymentsByIndividualId(id);
    assertEquals(expected, actuals);
    verify(individualPaymentsInfoDao, times(1)).showIndividualPaymentsByIndividualId(id);
    log.info("Test ok");
  }

  @Test
  void test_show_individual_payments_by_non_existing_individual_id()
  {
    String id = "EDAA244FB5354791E053020011AC1C77";
    when(individualPaymentsInfoDao.showIndividualPaymentsByIndividualId(id)).thenThrow(PaymentPlanException.class);
    assertThrows(PaymentPlanException.class, ()-> individualPaymentInfoService.showIndividualPaymentsByIndividualId(id));
    verify(individualPaymentsInfoDao, times(1)).showIndividualPaymentsByIndividualId(id);
    log.info("Test ok");
  }

  @Test
  void showAllIndividualPayments()
  {
    List<IndividualPaymentsInfo> expected = new ArrayList<>();
    when(individualPaymentsInfoDao.showAllIndividualPayments()).thenReturn(expected);
    List<IndividualPaymentsInfo> actuals = individualPaymentInfoService.showAllIndividualPayments();
    assertEquals(expected, actuals);
    verify(individualPaymentsInfoDao, times(1)).showAllIndividualPayments();
    log.info("Test ok");
  }
}