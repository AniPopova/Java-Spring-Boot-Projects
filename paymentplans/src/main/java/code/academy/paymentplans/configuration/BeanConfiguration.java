package code.academy.paymentplans.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration
{
  @Bean
  public ObjectMapper objectMapper()
  {
    return new ObjectMapper();
  }

}
