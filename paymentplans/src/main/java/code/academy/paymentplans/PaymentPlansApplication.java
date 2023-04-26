package code.academy.paymentplans;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Log4j2
@SpringBootApplication
public class PaymentPlansApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(PaymentPlansApplication.class, args);

    log.info("Application is running...");

  }
}
