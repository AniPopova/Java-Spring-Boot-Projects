package code.academy.paymentplans.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import code.academy.paymentplans.dao.individual.IndividualDao;
import code.academy.paymentplans.exeptions.PaymentPlanException;
import code.academy.paymentplans.model.Individual;
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
class IndividualServiceImplTest
{

  @Mock
  private IndividualDao     individualDao;
  @Autowired
  private IndividualServiceImpl individualService;

  @BeforeEach
  void setUp()
  {
    individualDao = Mockito.mock(IndividualDao.class);
    individualService = new IndividualServiceImpl(individualDao);
  }


  @Test
  void check_connection()
  {
    assertNotNull(individualService);
    log.info("Test ok");
  }


  @Test
  void test_create_individual_with_correct_data()
  {
    Individual newIndividual =
        new Individual("John Doe", "Sofia");

    String returnedId = "";
    when((individualDao).createIndividual(newIndividual))
        .thenReturn(returnedId);
    String actual = individualService.createIndividual(newIndividual);
    assertEquals(actual, returnedId);
    assertThat(newIndividual).isNotNull();
    verify(individualDao, times(1)).createIndividual(newIndividual);
    log.info("Test ok");
  }


  @Test
  void test_create_individual_wrong_name()
  {
    Individual newIndividual =
        new Individual("7", "Varna");

    when(individualService.createIndividual(newIndividual)).thenThrow(PaymentPlanException.class);
    assertThrows(PaymentPlanException.class, ()-> individualService.createIndividual(newIndividual));
    verify(individualDao, times(1)).createIndividual(newIndividual);
    log.info("Test ok");
  }
  @Test
  void test_create_individual_wrong_address()
  {
    Individual newIndividual =
        new Individual("Aneta Pavlova", "123@");

    when(individualService.createIndividual(newIndividual)).thenThrow(PaymentPlanException.class);
    assertThrows(PaymentPlanException.class, ()-> individualService.createIndividual(newIndividual));
    verify(individualDao, times(1)).createIndividual(newIndividual);
    log.info("Test ok");
  }

  @Test
  void test_update_individual_with_correct_data()
  {
    Individual newIndividual =
        new Individual( "John Doe", "Sofia");
    String id ="ED5960652ADD1470E053020005AC0545";
    individualService.updateIndividual(newIndividual, id);
    verify(individualDao, times(1)).updateIndividual(newIndividual, id);
  }

  @Test
  void find_all_individuals()
  {
    List<Individual> expectedIndividuals = new ArrayList<>();
    when(individualDao.findAllIndividuals()).thenReturn(expectedIndividuals);
    List<Individual> actualIndividuals = individualService.findAllIndividuals();
    assertEquals(expectedIndividuals, actualIndividuals);
    verify(individualDao, times(1)).findAllIndividuals();
  }


  @Test
  void delete_individual_by_id_with_correct_data()
  {
    String id = "ED970DF338723ECFE053020011ACAE35";
    individualService.deleteIndividual(id);
    verify(individualDao, times(1)).deleteIndividual(id);
    log.info("Test ok");
  }
}