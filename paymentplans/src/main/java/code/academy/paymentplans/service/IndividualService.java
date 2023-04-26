package code.academy.paymentplans.service;

import code.academy.paymentplans.model.Individual;
import java.util.List;

public interface IndividualService
{
  String createIndividual(Individual individual);

  Individual findIndividualByID(String id);

  List<Individual> findAllIndividuals();

  void updateIndividual(Individual individual, String id);

  void deleteIndividual(String id);
}
