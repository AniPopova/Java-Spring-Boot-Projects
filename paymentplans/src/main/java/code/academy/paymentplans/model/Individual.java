package code.academy.paymentplans.model;

import static code.academy.paymentplans.utils.Messages.EMPTY_ADDRESS;
import static code.academy.paymentplans.utils.Messages.EMPTY_NAME;
import static code.academy.paymentplans.utils.Messages.INVALID_NAME;
import static code.academy.paymentplans.utils.Messages.NAME_TOO_LONG;
import static code.academy.paymentplans.utils.Messages.NAME_TOO_SHORT;
import static code.academy.paymentplans.utils.Messages.VALIDATE_NAME;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Individual
{
  @Pattern(regexp = VALIDATE_NAME, message = INVALID_NAME)
  @NotEmpty(message = EMPTY_NAME)
  @Size(min = 5, message = NAME_TOO_SHORT)
  @Size(max = 100, message = NAME_TOO_LONG)
  private String name;
  @NotBlank(message = EMPTY_ADDRESS)
  private String address;

}
