package code.academy.paymentplans.dao.individualpayments;

import code.academy.paymentplans.dto.IndividualPaymentsInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class IndividualPaymentsInfoDaoImpl implements IndividualPaymentsInfoDao
{
  private final NamedParameterJdbcOperations jdbcOperations;
  @Override
  public List<IndividualPaymentsInfo> showIndividualPaymentsByPlanId(String planId)
  {

      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("planId", planId);
      String sql =
                "SELECT PERSON_ID,               "
              + "       FULL_NAME,               "
              + "       PAYMENT_PLAN_NR,         "
              + "       PAYMENT_ID,              "
              + "       CREDIT_AMOUNT,           "
              + "       PAYMENT_MADE,            "
              + "       REMAINING_AMOUNTS        "
              + "FROM INDIVIDUAL_PAYMENT_INFO    "
              + "WHERE PAYMENT_PLAN_NR =:planId  "
              + "ORDER BY PAYMENT_PLAN_NR        ";

      return jdbcOperations.query(sql,
          sqlParam,
          (rs, rowNum) -> new IndividualPaymentsInfo(
              rs.getString("PERSON_ID"),
              rs.getString("FULL_NAME"),
              rs.getString("PAYMENT_PLAN_NR"),
              rs.getString("PAYMENT_ID"),
              rs.getBigDecimal("CREDIT_AMOUNT"),
              rs.getBigDecimal("PAYMENT_MADE"),
              rs.getBigDecimal("REMAINING_AMOUNTS")));

  }

  @Override
  public List<IndividualPaymentsInfo> showIndividualPaymentsByPaymentId(String paymentId)
  {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("paymentId", paymentId);
    String sql =
              "SELECT PERSON_ID,               "
            + "       FULL_NAME,               "
            + "       PAYMENT_PLAN_NR,         "
            + "       PAYMENT_ID,              "
            + "       CREDIT_AMOUNT,           "
            + "       PAYMENT_MADE,            "
            + "       REMAINING_AMOUNTS        "
            + "FROM INDIVIDUAL_PAYMENT_INFO    "
            + "WHERE PAYMENT_ID =:paymentId    "
            + "ORDER BY PAYMENT_ID             ";


    return jdbcOperations.query(sql,
          sqlParam,
          (rs, rowNum) -> new IndividualPaymentsInfo(
              rs.getString("PERSON_ID"),
              rs.getString("FULL_NAME"),
              rs.getString("PAYMENT_PLAN_NR"),
              rs.getString("PAYMENT_ID"),
              rs.getBigDecimal("CREDIT_AMOUNT"),
              rs.getBigDecimal("PAYMENT_MADE"),
              rs.getBigDecimal("REMAINING_AMOUNTS")));

  }

  @Override
  public List<IndividualPaymentsInfo> showIndividualPaymentsByIndividualId(String individualId)
  {
    SqlParameterSource sqlParam = new MapSqlParameterSource()
        .addValue("individualId", individualId);

    String sql =
              "SELECT PERSON_ID,                "
            + "       FULL_NAME,                "
            + "       PAYMENT_PLAN_NR,          "
            + "       PAYMENT_ID,               "
            + "       CREDIT_AMOUNT,            "
            + "       PAYMENT_MADE,             "
            + "       REMAINING_AMOUNTS         "
            + "FROM INDIVIDUAL_PAYMENT_INFO     "
            + "WHERE PAYMENT_ID =:individualId  "
            + "ORDER BY PERSON_ID               ";


    return jdbcOperations.query(sql,
        sqlParam,
        (rs, rowNum) -> new IndividualPaymentsInfo(
            rs.getString("PERSON_ID"),
            rs.getString("FULL_NAME"),
            rs.getString("PAYMENT_PLAN_NR"),
            rs.getString("PAYMENT_ID"),
            rs.getBigDecimal("CREDIT_AMOUNT"),
            rs.getBigDecimal("PAYMENT_MADE"),
            rs.getBigDecimal("REMAINING_AMOUNTS")));

  }

  @Override
  public List<IndividualPaymentsInfo> showAllIndividualPayments()
  {
    SqlParameterSource sqlParam = new MapSqlParameterSource();

    String sql =
              "SELECT PERSON_ID,                "
            + "       FULL_NAME,                "
            + "       PAYMENT_PLAN_NR,          "
            + "       PAYMENT_ID,               "
            + "       CREDIT_AMOUNT,            "
            + "       PAYMENT_MADE,             "
            + "       REMAINING_AMOUNTS         "
            + "FROM INDIVIDUAL_PAYMENT_INFO     ";


    return jdbcOperations.query(sql,
        sqlParam,
        (rs, rowNum) -> new IndividualPaymentsInfo(
            rs.getString("PERSON_ID"),
            rs.getString("FULL_NAME"),
            rs.getString("PAYMENT_PLAN_NR"),
            rs.getString("PAYMENT_ID"),
            rs.getBigDecimal("CREDIT_AMOUNT"),
            rs.getBigDecimal("PAYMENT_MADE"),
            rs.getBigDecimal("REMAINING_AMOUNTS")));
  }
}
