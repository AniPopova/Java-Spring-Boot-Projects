package code.academy.paymentplans.dao.individual;

import code.academy.paymentplans.model.Individual;
import java.util.List;
import java.util.Optional;

public interface IndividualDao
{
  String createIndividual(Individual individual);

  Optional<Individual> findIndividualByID(String id);

  List<Individual> findAllIndividuals();

  void updateIndividual(Individual individual, String id);

  void deleteIndividual(String id);
}
