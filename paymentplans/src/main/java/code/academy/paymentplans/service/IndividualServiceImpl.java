package code.academy.paymentplans.service;

import static code.academy.paymentplans.utils.Messages.THERE_IS_NO_SUCH_INDIVIDUAL;

import code.academy.paymentplans.dao.individual.IndividualDao;
import code.academy.paymentplans.exeptions.PaymentPlanException;
import code.academy.paymentplans.model.Individual;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualServiceImpl implements IndividualService
{
  private final IndividualDao individualDao;

  @Override
  public String createIndividual(Individual individual)
  {
    return individualDao.createIndividual(individual);
  }

  @Override
  public void updateIndividual(Individual individual, String id)
  {
    individualDao.updateIndividual(individual, id);
  }

  @Override
  public Individual findIndividualByID(String individualId)
  {
    return individualDao
        .findIndividualByID(individualId)
        .orElseThrow(() -> new PaymentPlanException(THERE_IS_NO_SUCH_INDIVIDUAL));
  }

  @Override
  public List<Individual> findAllIndividuals()
  {
    return individualDao.findAllIndividuals();
  }


  @Override
  public void deleteIndividual(String individualId)
  {
    individualDao.deleteIndividual(individualId);
  }
}
