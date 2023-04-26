package code.academy.paymentplans.dao.individual;

import code.academy.paymentplans.model.Individual;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Log4j2
@Repository
@RequiredArgsConstructor
public class IndividualDaoImpl implements IndividualDao
{

  private final NamedParameterJdbcOperations jdbcOperations;

  @Override
  public String createIndividual(Individual individual)
  {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("name", individual.getName())
        .addValue("address", individual.getAddress());

    String sql = ""
        + "INSERT INTO INDIVIDUAL(INDIVID, NAME, ADDRESS)        "
        + "VALUES((SELECT SYS_GUID() FROM dual), :name, :address)";


    jdbcOperations.update(sql, params, keyHolder, new String[]{"INDIVID"});
    String id = (String) keyHolder
        .getKeys()
        .get("INDIVID");
    log.info("An individual profile has been created successful!");
    return id;
  }

  @Override
  public Optional<Individual> findIndividualByID(String individ)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("individ", individ);
      return jdbcOperations.queryForObject(
             "SELECT                    "
              + " NAME,                     "
              + " ADDRESS                   "
              + " FROM INDIVIDUAL           "
              + " WHERE INDIVID = :individ  ",
          sqlParam,
          (rs, rowNum) -> Optional.of(new Individual(
              rs.getString("NAME"),
              rs.getString("ADDRESS"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public List<Individual> findAllIndividuals()
  {
    String sql =
              "SELECT         "
            + "INDIVID,       "
            + "NAME,          "
            + "ADDRESS        "
            + "FROM INDIVIDUAL";

    return jdbcOperations.query(sql,
        (rs, rowNum) -> new Individual(
            rs.getString("NAME"),
            rs.getString("ADDRESS")));
  }

  @Override
  public void updateIndividual(Individual individual, String id)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("individ", id)
        .addValue("name", individual.getName())
        .addValue("address", individual.getAddress());

    String sql =
          "UPDATE INDIVIDUAL      "
        + "SET NAME    = :name,   "
        + "    ADDRESS = :address "
        + "WHERE INDIVID=:individ ";

    jdbcOperations.update(sql, params);
    log.info("An individual has been updated!");
  }

  @Override
  public void deleteIndividual(String individ)
  {
    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("individ", individ);
    String sql = ""
        + "DELETE FROM INDIVIDUAL  "
        + "WHERE INDIVID=:individ  ";

    jdbcOperations.update(sql, param);
    log.info("An individual has been deleted successful!");
  }
}
