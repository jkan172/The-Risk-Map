package nz.ac.auckland.se281;

public class IncorrectCountryException extends Exception {

  private String countryName;

  public IncorrectCountryException(String countryName) {
    super(MessageCli.INVALID_COUNTRY.getMessage(countryName));
    this.countryName = countryName;
  }

  public String getCountryName() {
    return countryName;
  }
}
