package code.academy.paymentplans.exeptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler
{

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Map<String, Object>> handleConstraintExceptions(ConstraintViolationException exception)
  {
    Map<String, Object> exceptedBody = new HashMap<>();
    for (ConstraintViolation<?> e : exception.getConstraintViolations()) {
      exceptedBody.put("message: ", e.getMessage());
    }
    return new ResponseEntity<>(exceptedBody, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({MethodArgumentNotValidException.class})
  protected  ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex)
  {
    Map<String, String> validationErrors = new HashMap<>();
    List<ObjectError> objectErrorList = ex
        .getBindingResult()
        .getAllErrors();
    for (ObjectError objectError : objectErrorList) {
      validationErrors.put(((FieldError) objectError).getField(), objectError.getDefaultMessage());
    }
    return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({PaymentPlanException.class})
  public ResponseEntity<String> handlerPaymentPlanException(PaymentPlanException ppe)
  {
    return new ResponseEntity<>(ppe.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
