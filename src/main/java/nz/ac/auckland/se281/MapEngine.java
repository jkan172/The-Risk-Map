package nz.ac.auckland.se281;

import java.util.List;

/** This class is the main entry point. */
public class MapEngine {

  private String[] countriesArray;
  private RiskMap graph;

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    // add code here to create your data structures

    graph = new RiskMap();

    for (String country : countries) {
      String[] countryArray = country.split(",");
      Countries newCountry =
          new Countries(countryArray[0], countryArray[1], Integer.parseInt(countryArray[2]));
      graph.addCountry(newCountry);
    }

    for (String adjacency : adjacencies) {
      String[] adjacencyArray = adjacency.split(",");
      Countries country1 = graph.getCountryByName(adjacencyArray[0]);

      for (int i = 1; i < adjacencyArray.length; i++) {
        Countries country2 = graph.getCountryByName(adjacencyArray[i]);
        graph.addAdjacency(country1, country2);
      }
    }

    // countriesArray = countries.split(",");

  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {

    MessageCli.INSERT_COUNTRY.printMessage();
    String input = Utils.scanner.nextLine();
    String capInput = Utils.capitalizeFirstLetterOfEachWord(input);

    // MessageCli.COUNTRY_INFO.printMessage();


    boolean countryFound = false;
      

    try {
      for (Countries country : graph.getCountriesSet()) {
        if (country.getName().equals(capInput)) {
          MessageCli.COUNTRY_INFO.printMessage(
              country.getName(), country.getContinent(), String.valueOf(country.getTax()));
          countryFound = true;
          break;
        }
      }
      if (!countryFound) {
      throw new IncorrectCountryException(input);
      }
      

    } catch (IncorrectCountryException e) {
      MessageCli.INVALID_COUNTRY.printMessage(e.getCountryName());
      
    }
  }
  

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
