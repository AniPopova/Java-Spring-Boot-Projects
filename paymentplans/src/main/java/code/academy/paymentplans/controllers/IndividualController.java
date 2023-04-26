package code.academy.paymentplans.controllers;

import code.academy.paymentplans.model.Individual;
import code.academy.paymentplans.paymentvalidation.PaymentValidationImpl;
import code.academy.paymentplans.service.IndividualService;
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
public class IndividualController
{

  private final IndividualService     individualService;
  private final PaymentValidationImpl paymentValidation;

  @PostMapping("/individuals")
  @ResponseStatus(HttpStatus.CREATED)
  public String createIndividual(@Valid
                                 @RequestBody Individual individual)
  {
    log.info("Request for creating user profile requested.");
    return individualService.createIndividual(individual);
  }

  @PatchMapping("/individuals/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void updateIndividual(@Valid
                               @RequestBody Individual individual,
                               @Size(min=32, max=32)
                               @PathVariable String id)
  {
    paymentValidation.checkIfIndividualExist(id);
    log.info("Update of user info requested.");
    individualService.updateIndividual(individual, id);
  }

  @GetMapping("/individuals/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Individual getIndividualById(@Valid
                                      @Size(min = 32, max = 32)
                                      @PathVariable String id)
  {
    paymentValidation.checkIfIndividualExist(id);
    return individualService.findIndividualByID(id);
  }

  @GetMapping(value = "/individuals/db")
  @ResponseStatus(HttpStatus.OK)
  public List<Individual> getAllIndividuals()
  {
    log.info("All users info in the DB requested");
    return individualService.findAllIndividuals();
  }

  @DeleteMapping(value = "/individuals/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteIndividualById(@Valid
                                   @Size(min = 32, max = 32)
                                   @PathVariable String id)
  {
    paymentValidation.checkIfIndividualExist(id);
    log.info("Request for deleting user was sent.");
    individualService.deleteIndividual(id);
  }

}

