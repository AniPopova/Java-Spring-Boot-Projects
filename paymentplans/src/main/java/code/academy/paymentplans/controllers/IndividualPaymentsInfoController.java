package code.academy.paymentplans.controllers;

import code.academy.paymentplans.dto.IndividualPaymentsInfo;
import code.academy.paymentplans.paymentvalidation.PaymentValidation;
import code.academy.paymentplans.service.IndividualPaymentInfoService;
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
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class IndividualPaymentsInfoController
{
 private final IndividualPaymentInfoService individualPaymentInfoService;
 private final PaymentValidation            paymentValidation;

 @GetMapping("/individual-info/{individualId}")
 @ResponseStatus(HttpStatus.OK)
  public List<IndividualPaymentsInfo> getByIndividualId(@Valid
                                                        @PathVariable
                                                        @Size(min=32, max=32) String individualId)
 {
   paymentValidation.checkIfIndividualExist(individualId);
   return individualPaymentInfoService.showIndividualPaymentsByIndividualId(individualId);
 }

  @GetMapping("/individual-info-plans/{planId}")
  @ResponseStatus(HttpStatus.OK)
  public List<IndividualPaymentsInfo> getByPlanId(@Valid
                                                  @PathVariable
                                                  @Size(min=32, max=32) String planId)
  {
    paymentValidation.checkIfPaymentPlanExist(planId);
    return individualPaymentInfoService.showIndividualPaymentsByPlanId(planId);
  }

  @GetMapping("/individual-info-payments/{paymentId}")
  @ResponseStatus(HttpStatus.OK)
  public List<IndividualPaymentsInfo> getByPaymentId(@Valid
                                                     @PathVariable
                                                     @Size(min=32, max=32) String paymentId)
  {
    paymentValidation.checkIfInstallmentExist(paymentId);
    return individualPaymentInfoService.showIndividualPaymentsByPaymentId(paymentId);
  }

  @GetMapping("/individual-info-payment-plans")
  @ResponseStatus(HttpStatus.OK)
  public List<IndividualPaymentsInfo> getAllIndividualPaymentsInfo()
  {
    return individualPaymentInfoService.showAllIndividualPayments();
  }

}
