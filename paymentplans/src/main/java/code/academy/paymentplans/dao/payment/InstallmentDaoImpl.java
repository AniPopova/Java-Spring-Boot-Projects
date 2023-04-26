package code.academy.paymentplans.dao.payment;

import code.academy.paymentplans.model.Installment;
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
public class InstallmentDaoImpl implements InstallmentDao
{
  private final NamedParameterJdbcOperations jdbcOperations;

  @Override
  public String createInstallment(Installment installment)
  {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("planId", installment.getPlanId())
        .addValue("amountPaid", installment.getAmountPaid());

    String sql ="INSERT INTO INSTALLMENTS(         "
              + "INSTALLMENTID,                    "
              + "PLANID,                           "
              + "AMOUNTPAID,                       "
              + "PAYMENTDATE)                      "
              + "VALUES(                           "
              + "(SELECT SYS_GUID() FROM dual),    "
              + ":planId,                          "
              + ":amountPaid,                      "
              + "SYSDATE)                          ";

    jdbcOperations.update(sql, params,
        keyHolder, new String[]{"INSTALLMENTID"});

    String id = (String) keyHolder
        .getKeys()
        .get("INSTALLMENTID");

    log.info("An installment passed successful!");
    return id;
  }

  @Override
  public Optional<Installment> findInstallmentByID(String id)
  {
    try {
      SqlParameterSource sqlParam = new MapSqlParameterSource()
          .addValue("installmentId", id);

      return jdbcOperations.queryForObject(""
              + "SELECT                               "
              + "INSTALLMENTID,                       "
              + "PLANID,                              "
              + "AMOUNTPAID,                          "
              + "PAYMENTDATE                          "
              + "FROM INSTALLMENTS                    "
              + "WHERE INSTALLMENTID = :installmentId ",
          sqlParam,
          (rs, rowNum) -> Optional.of(new Installment(
              rs.getString("planId"),
              rs.getBigDecimal("amountPaid"))));
    }
    catch (EmptyResultDataAccessException erdae) {
      return Optional.empty();
    }
  }

  @Override
  public List<Installment> findAllInstallments()
  {
    String sql = "SELECT            "
               + "INSTALLMENTID,    "
               + "PLANID,           "
               + "AMOUNTPAID,       "
               + "PAYMENTDATE       "
               + "FROM INSTALLMENTS ";

    return jdbcOperations.query(sql,
        (rs, rowNum) -> new Installment(
            rs.getString("planId"),
            rs.getBigDecimal("amountPaid")));
  }

  @Override
  public void updateInstallment(String idInstallment, String planId)
  {
    SqlParameterSource params = new MapSqlParameterSource()
        .addValue("installmentId", idInstallment)
        .addValue("planId", planId);

    String sql = ""
        + "UPDATE INSTALLMENTS                  "
        + "SET PLANID      = :planId            "
        + "WHERE INSTALLMENTID = :installmentId ";

    jdbcOperations.update(sql, params);
  }

  @Override
  public void deleteInstallment(String id)
  {
    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("installmentId", id);

    String sql = "DELETE                              "
               + "FROM   INSTALLMENTS                 "
               + "WHERE INSTALLMENTID = :installmenId ";

    jdbcOperations.update(sql, param);
    log.info("Installment has been deleted successful!");
  }
}
