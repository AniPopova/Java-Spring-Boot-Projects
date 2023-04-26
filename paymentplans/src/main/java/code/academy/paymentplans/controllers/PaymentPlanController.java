package code.academy.paymentplans.controllers;

import code.academy.paymentplans.model.PaymentPlan;
import code.academy.paymentplans.paymentvalidation.PaymentValidation;
import code.academy.paymentplans.service.PaymentPlanService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/api")
public class PaymentPlanController
{
  private final PaymentPlanService paymentPlanService;
  private final PaymentValidation  paymentValidation;

  @PostMapping("/plans")
  @ResponseStatus(HttpStatus.CREATED)
  public String createPaymentPlan(@Valid
                                  @RequestBody PaymentPlan paymentPlan)
  {
    log.info("Request for payment by its id.");
    return paymentPlanService.createPaymentPlan(paymentPlan);
  }

  @PatchMapping("/plans/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void updatePaymentPlan(@Valid
                                @RequestBody PaymentPlan paymentPlan,
                                @PathVariable
                                @Size(min=32, max=32) String id)
  {
    paymentValidation.checkIfPaymentPlanExist(id);
    paymentPlanService.updatePaymentPlan(paymentPlan, id);
  }

  @GetMapping("/payment-plans")
  @ResponseStatus(HttpStatus.OK)
  public List<PaymentPlan> getAllPaymentPlans()
  {
    return paymentPlanService.getAllPaymentPlans();
  }

  @GetMapping("/payment-plans/{id}")
  @ResponseStatus(HttpStatus.OK)
  public PaymentPlan getPaymentPlanById(@Valid
                                        @PathVariable String id)
  {
    paymentValidation.checkIfPaymentPlanExist(id);
    return paymentPlanService.getPaymentPlanByID(id);
  }

  @DeleteMapping("/plans/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deletePaymentPlan(@Valid
                                @PathVariable String id)
  {
    paymentValidation.checkIfPaymentPlanExist(id);
    paymentPlanService.deletePaymentPlan(id);
  }
}
