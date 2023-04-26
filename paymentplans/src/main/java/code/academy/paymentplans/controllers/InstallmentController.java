package code.academy.paymentplans.controllers;

import code.academy.paymentplans.model.Installment;
import code.academy.paymentplans.paymentvalidation.PaymentValidation;
import code.academy.paymentplans.service.InstallmentService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api")
public class InstallmentController
{
  private final InstallmentService installmentService;
  private final PaymentValidation paymentValidation;

  @PostMapping("/installments/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public String createInstallment(@Valid
                                  @RequestBody Installment installment,
                                  @Size(min = 32, max = 32)
                                  @PathVariable String id)
  {
    paymentValidation.checkIfPaymentPlanExist(id);
    return installmentService.createInstallment(installment, id);
  }

  @PatchMapping("/installments/{idInstallment}")
  @ResponseStatus(HttpStatus.OK)
  public void updateInstallment(@Valid
                                @PathVariable("idInstallment") String installmentId,
                                @RequestParam String planId)
  {
    paymentValidation.checkIfInstallmentExist(installmentId);
    installmentService.updateInstallment(installmentId, planId);
  }

  @GetMapping("/installments")
  @ResponseStatus(HttpStatus.OK)
  public List<Installment> getAllPayments()
  {
    return installmentService.findAllInstallments();
  }

  @GetMapping("/installments/{idInstallment}")
  @ResponseStatus(HttpStatus.OK)
  public Installment getInstallmentById(@Valid
                                        @Size(min = 32, max = 32)
                                        @PathVariable String idInstallment)
  {
    paymentValidation.checkIfInstallmentExist(idInstallment);
    return installmentService.findInstallmentByID(idInstallment);
  }

  @DeleteMapping("/installments/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteInstallment(@Valid
                                @Size(min = 32, max = 32)
                                @PathVariable String id)
  {
    paymentValidation.checkIfInstallmentExist(id);
    installmentService.deleteInstallment(id);
  }

}
