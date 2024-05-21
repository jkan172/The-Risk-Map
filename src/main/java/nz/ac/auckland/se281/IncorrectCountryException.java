package nz.ac.auckland.se281;

/** Exception thrown when the country name is incorrect. */
public class IncorrectCountryException extends Exception {

  private String countryName;

  /**
   * Constructor for the exception.
   *
   * @param countryName
   */
  public IncorrectCountryException(String countryName) {
    super(MessageCli.INVALID_COUNTRY.getMessage(countryName));
    this.countryName = countryName;
  }

  public String getCountryName() {
    return countryName;
  }
}
