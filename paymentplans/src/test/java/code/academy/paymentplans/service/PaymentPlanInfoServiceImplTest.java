package code.academy.paymentplans.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import code.academy.paymentplans.dao.pplaninfo.PaymentPlanInfoDao;
import code.academy.paymentplans.dto.PaymentPlanInfo;
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
class PaymentPlanInfoServiceImplTest
{
  @Mock
  private PaymentPlanInfoDao paymentPlanInfoDao;

  @Autowired
  private PaymentPlanInfoServiceImpl paymentPlanInfoService;

  @BeforeEach
  void setUp()
  {
    paymentPlanInfoDao = Mockito.mock(PaymentPlanInfoDao.class);
    paymentPlanInfoService = new PaymentPlanInfoServiceImpl(paymentPlanInfoDao);
  }

  @Test
  void check_connection()
  {
    assertNotNull(paymentPlanInfoService);
    log.info("Test ok");
  }


  @Test
  void test_find_payment_plan_info_by_existing_installment_id()
  {
    String id = "EDAE25832C3B1691E053020011AC7BDE";
    List<PaymentPlanInfo> expected = new ArrayList<>();
    when(paymentPlanInfoDao.findPaymentPlanInfoByInstallmentId(id)).thenReturn(expected);
    List<PaymentPlanInfo> actuals = paymentPlanInfoService.findPaymentPlanInfoByInstallmentId(id);
    assertEquals(expected, actuals);
    verify(paymentPlanInfoDao, times(1)).findPaymentPlanInfoByInstallmentId(id);
    log.info("Test ok");
  }

  @Test
  void test_find_payment_plan_info_by_non_existing_installment_id()
  {
    String id = "EDAA244FB5334791E053020011AC1C77";
    when(paymentPlanInfoDao.findPaymentPlanInfoByInstallmentId(id)).thenThrow(PaymentPlanException.class);
    assertThrows(PaymentPlanException.class, ()-> paymentPlanInfoService.findPaymentPlanInfoByInstallmentId(id));
    verify(paymentPlanInfoDao, times(1)).findPaymentPlanInfoByInstallmentId(id);
    log.info("Test ok");
  }

  @Test
  void test_find_payment_plan_info_by_existing_plan_id()
  {
    String id = "EDAA244FB5334791E053020011AC1CE4";
    List<PaymentPlanInfo> expected = new ArrayList<>();
    when(paymentPlanInfoDao.findPaymentPlanInfoByPlanId(id)).thenReturn(expected);
    List<PaymentPlanInfo> actuals = paymentPlanInfoService.findPaymentPlanInfoByPlanId(id);
    assertEquals(expected, actuals);
    verify(paymentPlanInfoDao, times(1)).findPaymentPlanInfoByPlanId(id);
    log.info("Test ok");
  }

  @Test
  void test_find_payment_plan_info_by_non_existing_plan_id()
  {
    String id = "EDAA244FB5334791E053020011AC1C77";
    when(paymentPlanInfoDao.findPaymentPlanInfoByPlanId(id)).thenThrow(PaymentPlanException.class);
    assertThrows(PaymentPlanException.class, ()-> paymentPlanInfoService.findPaymentPlanInfoByPlanId(id));
    verify(paymentPlanInfoDao, times(1)).findPaymentPlanInfoByPlanId(id);
    log.info("Test ok");
  }

  @Test
  void test_get_all_payment_plans_info()
  {

    List<PaymentPlanInfo> expected = new ArrayList<>();
    when(paymentPlanInfoDao.getAllPaymentPlansInfo()).thenReturn(expected);
    List<PaymentPlanInfo> actuals = paymentPlanInfoService.getAllPaymentPlansInfo();
    assertEquals(expected, actuals);
    verify(paymentPlanInfoDao, times(1)).getAllPaymentPlansInfo();
    log.info("Test ok");
  }
}