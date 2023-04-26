package code.academy.paymentplans.utils;

public final class Messages
{

  public static final String INVALID_NAME   = "Please enter a valid name!";
  public static final String EMPTY_NAME     = "Please enter your name!";
  public static final String NAME_TOO_SHORT = "Your full name can not be five letters only!";
  public static final String NAME_TOO_LONG  = "Your full name must not exceed a hundred letters!";
  public static final String VALIDATE_NAME  =
      "^[^- '](?=(?![A-Z]?[A-Z]))(?=(?![a-z]+[A-Z]))(?=(?!.*[A-Z][A-Z]))"
          + "(?=(?!.*[- '][- '.]))(?=(?!.*[.][-'.]))[A-Za-z- '.]{2,}$";
  public static final String EMPTY_ADDRESS                 = "Please enter address!";
  public static final String INVALID_ID                    = "Please enter valid identification number!";
  public static final String THERE_IS_NO_SUCH_INDIVIDUAL   = "The person you are looking for does not exists!";
  public static final String THERE_IS_NO_SUCH_PAYMENT_PLAN = "Such payment plan does not exists!";
  public static final String PAYMENT_PLAN_ALREADY_PAID = "The payment plan is already paid! You do not owe more "
      + "installments!";

  public static final String THERE_IS_NO_SUCH_PAYMENT      = "No payment was found!";
  public static final String PAYMENT_BIGGER_THAN_REMAINING_AMOUNT     = "You are trying to pay more than you owe. "
      + "Check again!";
  public static final String NEGATIVE_PAYMENT_AMOUNT_NOT_ALLOWED     = "The sum you are trying to pay must be higher than zero!";

  private Messages()

  {
  }
}
