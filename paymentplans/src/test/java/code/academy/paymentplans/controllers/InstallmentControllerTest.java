package code.academy.paymentplans.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@AutoConfigureMockMvc
@Transactional
@SpringBootTest
public class InstallmentControllerTest
{
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void test_get_all_installments() throws Exception
  {
    mockMvc
        .perform(get("/api/installments"))
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn();

  }

  @Test
  void test_get_installment_by_id()throws Exception
  {
    {
      String installmentId = "EDAE25832C3C1691E053020011AC7BDE";
      mockMvc
          .perform(get("/api/installments/{idInstallment}", installmentId))
          .andExpect(jsonPath("$.planId").value("EDAA244FB5334791E053020011AC1CE4"))
          .andExpect(jsonPath("$.amountPaid").value(70))
          .andExpect(status().isOk())
          .andDo(print());

      log.info("Test ok!");
    }
  }
}
