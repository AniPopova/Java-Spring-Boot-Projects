package code.academy.paymentplans.controllers;

import static code.academy.paymentplans.utils.Messages.THERE_IS_NO_SUCH_INDIVIDUAL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import code.academy.paymentplans.model.Individual;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@AutoConfigureMockMvc
@Transactional
@SpringBootTest
public class IndividualControllerTest
{

  @Autowired
  private MockMvc      mockMvc;
  @Autowired
  private ObjectMapper objectMapper;


  @Test
  public void create_individual_test_with_correct_data() throws Exception
  {
    Individual individual =
        new Individual("Katty Perry", "Dupnitsa");

    mockMvc
        .perform(post("/api/individuals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(individual)))
        .andExpect(status().isCreated());

  }

  @Test
  public void create_individual_with_wrong_data() throws Exception
  {
    Individual individual =
        new Individual("J", "@");

    mockMvc
        .perform(post("/api/individuals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(individual)))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }

  @Test
  public void update_individual_with_correct_data() throws Exception
  {
    Individual individual =
        new Individual("Katty Perry", "Dupnitsa");

    mockMvc
        .perform(patch("/api/individuals/{id}", "ED97008E27FF3CF6E053020011ACFA68")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(individual)))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  public void update_individual_with_wrong_data() throws Exception
  {
    Individual individual =
        new Individual("27", "Dupnitsa");

    mockMvc
        .perform(patch("/api/individuals/{id}", "ED97808E27FF3CF6E053020011ACFA68")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(individual)))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }
  @Test
  public void delete_individual_test_with_correct_data() throws Exception
  {
String id = "ED958F6D39BD2805E053020011ACB405";
    mockMvc
        .perform(delete("/api/individuals/{id}",id)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isResetContent())
        .andDo(print());

  }

  @Test
  public void get_individual_by_correct_id() throws Exception
  {
    String id = "ED958F6D39BD2805E053020011ACB405";
    mockMvc
        .perform(get("/api/individuals/{id}",id))
        .andExpect(jsonPath("$.name").value("John Doe"))
        .andExpect(jsonPath("$.address").value("Sofia"))
        .andExpect(status().isOk())
        .andDo(print());

  }

  @Test
  public void get_individual_by_wrong_id() throws Exception
  {
    String id = "ED958F6D39BD2805E053020011ACB777";
    mockMvc
        .perform(get("/api/individuals/{id}",id))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(THERE_IS_NO_SUCH_INDIVIDUAL))
        .andDo(print());

  }

  @Test
  public void get_all_individuals() throws Exception
  {
    mockMvc
        .perform(get("/api/individuals/db"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andDo(print());
  }

  @Test
  public void delete_individual_with_wrong_data() throws Exception
  {
    Individual individual =
        new Individual("J", "@");

    mockMvc
        .perform(post("/api/individuals")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(individual)))
        .andExpect(status().isBadRequest())
        .andDo(print());
  }
}
