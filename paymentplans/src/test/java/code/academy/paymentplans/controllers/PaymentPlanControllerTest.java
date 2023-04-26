package code.academy.paymentplans.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
@Transactional
public class PaymentPlanControllerTest
{
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void delete_individual_test_with_correct_data() throws Exception
  {

    mockMvc
        .perform(delete("/api/plans/{id}","EDAA244FB5334791E053020011AC1CE4")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());

  }

  @Test
  public void delete_payment_plan_with_wrong_data() throws Exception
  {


    mockMvc
        .perform(delete("/api/plans/{id}","EDAA244FB5331E053020011AC1CE4")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }
}
