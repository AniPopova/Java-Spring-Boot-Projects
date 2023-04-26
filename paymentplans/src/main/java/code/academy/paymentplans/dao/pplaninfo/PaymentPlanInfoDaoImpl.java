package code.academy.paymentplans.dao.pplaninfo;

import code.academy.paymentplans.dto.PaymentPlanInfo;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Log4j2
@RequiredArgsConstructor
@Repository
public class PaymentPlanInfoDaoImpl implements PaymentPlanInfoDao
{
  private final NamedParameterJdbcOperations jdbcOperations;


  @Override
  public List<PaymentPlanInfo> findPaymentPlanInfoByInstallmentId(String installmentId)
  {

      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("installmentId", installmentId);
      return jdbcOperations.query(""
              + "SELECT PAYMENT_PLAN_NR,         "
              + "       TOTAL_AMOUNT,            "
              + "       PAYMENT_ID,              "
              + "       PAYMENT_MADE             "
              + "FROM PAYMENT_PLAN_INFO          "
              + "WHERE PAYMENT_ID=:installmentId "
              + "ORDER BY PAYMENT_ID             ",
          sqlParam,
          (rs, rowNum) -> new PaymentPlanInfo(
              rs.getString("PAYMENT_PLAN_NR"),
              rs.getBigDecimal("TOTAL_AMOUNT"),
              rs.getString("PAYMENT_ID"),
              rs.getBigDecimal("PAYMENT_MADE")));

  }

  @Override
  public List<PaymentPlanInfo> findPaymentPlanInfoByPlanId(String planId)
  {

      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("planId", planId);
      return jdbcOperations.query(""
              + "SELECT PAYMENT_PLAN_NR,       "
              + "       TOTAL_AMOUNT,          "
              + "       PAYMENT_ID,            "
              + "       PAYMENT_MADE           "
              + "FROM PAYMENT_PLAN_INFO        "
              + "WHERE PAYMENT_PLAN_NR=:planId "
              + "ORDER BY PAYMENT_PLAN_NR      ",
          sqlParam,
          (rs, rowNum) -> new PaymentPlanInfo(
                  rs.getString("PAYMENT_PLAN_NR"),
                  rs.getBigDecimal("TOTAL_AMOUNT"),
                  rs.getString("PAYMENT_ID"),
                  rs.getBigDecimal("PAYMENT_MADE")));

  }

  @Override
  public List<PaymentPlanInfo> getAllPaymentPlansInfo()
  {
    String sql =
          "SELECT                   "
        + "       PAYMENT_PLAN_NR,  "
        + "       TOTAL_AMOUNT,     "
        + "       PAYMENT_ID,       "
        + "       PAYMENT_MADE      "
        + "FROM PAYMENT_PLAN_INFO   ";

    return jdbcOperations.query(
        sql,
        (rs, rowNum) -> new PaymentPlanInfo(
            rs.getString("PAYMENT_PLAN_NR"),
            rs.getBigDecimal("TOTAL_AMOUNT"),
            rs.getString("PAYMENT_ID"),
            rs.getBigDecimal("PAYMENT_MADE")));
  }
}
