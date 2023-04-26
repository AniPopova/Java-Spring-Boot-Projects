package code.academy.paymentplans.controllers;

import code.academy.paymentplans.dto.PaymentPlanInfo;
import code.academy.paymentplans.paymentvalidation.PaymentValidation;
import code.academy.paymentplans.paymentvalidation.PaymentValidationImpl;
import code.academy.paymentplans.service.PaymentPlanInfoService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api")
public class PaymentPlanInfoController
{

  private final PaymentPlanInfoService paymentPlanInfoService;
  private final PaymentValidation      paymentValidation;

  @GetMapping("/paymentplansinfo/{planId}")
  @ResponseStatus(HttpStatus.OK)
  public List<PaymentPlanInfo> getPaymentPlanInfoByPlanId(@Valid
                                                          @PathVariable
                                                          @Size(min = 32, max = 32) String planId)
  {
    paymentValidation.checkIfPaymentPlanExist(planId);
    return paymentPlanInfoService.findPaymentPlanInfoByPlanId(planId);
  }

  @GetMapping("/paymentplansinfo/{installmentId}")
  @ResponseStatus(HttpStatus.OK)
  public List<PaymentPlanInfo> getPaymentPlanInfoByPaymentId(@Valid
                                                             @PathVariable
                                                             @Size(min = 32, max = 32) String installmentId)
  {
    paymentValidation.checkIfInstallmentExist(installmentId);
    return paymentPlanInfoService.findPaymentPlanInfoByInstallmentId(installmentId);
  }

  @GetMapping("/pplansinfo")
  @ResponseStatus(HttpStatus.OK)
  public List<PaymentPlanInfo> getAllPaymentPlansInfo()
  {
    return paymentPlanInfoService.getAllPaymentPlansInfo();
  }

}
