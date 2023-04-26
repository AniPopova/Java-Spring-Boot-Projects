package code.academy.paymentplans.dao.paymentsplan;

import code.academy.paymentplans.model.PaymentPlan;
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
@RequiredArgsConstructor
@Repository
public class PaymentPlanDaoImpl implements PaymentPlanDao
{

  private final NamedParameterJdbcOperations jdbcOperations;

  @Override
  public String createPaymentPlan(PaymentPlan paymentPlan)
  {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("indivId", paymentPlan.getIndivId())
        .addValue("amountToPay", paymentPlan.getAmountToPay());

    String sql =
              "INSERT INTO                    "
            + "PAYMENTPLANS(                  "
            + "        PLANID,                "
            + "        INDIVID,               "
            + "        AMOUNTTOPAY)           "
            + "VALUES(                        "
            + "(SELECT SYS_GUID() FROM dual), "
            + " :indivId,                     "
            + ":amountToPay)                  ";

    jdbcOperations.update(sql, params, keyHolder, new String[]{"PLANID"});
    String id = (String) keyHolder
        .getKeys()
        .get("PLANID");
    log.info("A payment plan has been created successful!");
    return id;
  }

  @Override
  public Optional<PaymentPlan> getPaymentPlanByID(String id)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("planId", id);
      return jdbcOperations.queryForObject(""
              + "SELECT                "
              + "       INDIVID,       "
              + "       AMOUNTTOPAY    "
              + "FROM PAYMENTPLANS     "
              + "WHERE PLANID=:planId  ",
          sqlParam,
          (rs, rowNum) -> Optional.of(new PaymentPlan(
              rs.getString("INDIVID"),
              rs.getBigDecimal("AMOUNTTOPAY"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public List<PaymentPlan> getAllPaymentPlans()
  {
    String sql =
              "SELECT              "
            + "       PLANID,      "
            + "       INDIVID,     "
            + "       AMOUNTTOPAY  "
            + "FROM PAYMENTPLANS   ";

    return jdbcOperations.query(
        sql,
        (rs, rowNum) -> new PaymentPlan(
            rs.getString("indivId"),
            rs.getBigDecimal("amountToPay")));
  }

  @Override
  public void updatePaymentPlan(PaymentPlan paymentPlan, String id)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("planId", id)
        .addValue("indivId", paymentPlan.getIndivId())
        .addValue("amountToPay", paymentPlan.getAmountToPay());

    String sql = ""
        + "UPDATE PAYMENTPLANS         "
        + "SET                         "
        + "    indivID = :indivId,     "
        + "AMOUNTTOPAY = :amountToPay  "
        + "WHERE PLANID=:planId        ";

    jdbcOperations.update(sql, params);
    log.info("Installment has been updated successful!");
  }

  @Override
  public void deletePaymentPlan(String id)
  {
    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("planID", id);
    String sql = "DELETE FROM PAYMENTPLANS WHERE PLANID=:planID";

    jdbcOperations.update(sql, param);
    log.info("Installment plan has been deleted!");
  }
}
