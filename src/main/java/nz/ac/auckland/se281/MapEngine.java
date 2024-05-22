package nz.ac.auckland.se281;

import java.util.LinkedList;
import java.util.List;

/** This class is the main entry point. */
public class MapEngine {

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

    // add countries to the graph
    for (String country : countries) {
      String[] countryArray = country.split(",");
      Countries newCountry =
          new Countries(countryArray[0], countryArray[1], Integer.parseInt(countryArray[2]));
      graph.addCountry(newCountry);
    }

    // add adjacencies to the graph
    for (String adjacency : adjacencies) {
      String[] adjacencyArray = adjacency.split(",");
      Countries country1 = graph.getCountryByName(adjacencyArray[0]);

      for (int i = 1; i < adjacencyArray.length; i++) {
        Countries country2 = graph.getCountryByName(adjacencyArray[i]);
        graph.addAdjacency(country1, country2);
      }
    }
  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    boolean countryFound = false;
    MessageCli.INSERT_COUNTRY.printMessage();

    // while the country is not found run the try catch mathod until the user inputs the correct
    // country
    while (!countryFound) {
      String input = Utils.scanner.nextLine();
      input = Utils.capitalizeFirstLetterOfEachWord(input);

      // check if the country is in the graph
      try {
        for (Countries country : graph.getCountriesSet()) {
          if (country.getName().equals(input)) {
            MessageCli.COUNTRY_INFO.printMessage(
                country.getName(), country.getContinent(), String.valueOf(country.getTax()));
            countryFound = true;
            break;
          }
        }
        if (!countryFound) {
          // if the country is not found throw an exception
          throw new IncorrectCountryException(input);
        }

      } catch (IncorrectCountryException e) {
        MessageCli.INVALID_COUNTRY.printMessage(e.getCountryName());
      }
    }
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {

    boolean sourceFound = false;
    boolean destinationFound = false;
    String source = null;
    String destination = null;
    Countries sourceCountry = null;
    Countries destinationCountry = null;

    while (!sourceFound) {
      MessageCli.INSERT_SOURCE.printMessage();
      source = Utils.scanner.nextLine();
      source = Utils.capitalizeFirstLetterOfEachWord(source);

      try {
        for (Countries country : graph.getCountriesSet()) {
          if (country.getName().equals(source)) {
            sourceFound = true;
            break;
          }
        }
        if (!sourceFound) {
          throw new IncorrectCountryException(source);
        }
      } catch (IncorrectCountryException e) {
        MessageCli.INVALID_COUNTRY.printMessage(e.getCountryName());
      }
    }

    while (!destinationFound) {
      MessageCli.INSERT_DESTINATION.printMessage();
      destination = Utils.scanner.nextLine();
      destination = Utils.capitalizeFirstLetterOfEachWord(destination);

      try {
        for (Countries country : graph.getCountriesSet()) {
          if (country.getName().equals(destination)) {
            destinationFound = true;
            break;
          }
        }
        if (!destinationFound) {
          throw new IncorrectCountryException(destination);
        }
      } catch (IncorrectCountryException e) {
        MessageCli.INVALID_COUNTRY.printMessage(e.getCountryName());
      }
    }

    if (source.equals(destination)) {
      MessageCli.NO_CROSSBORDER_TRAVEL.printMessage();
    }

    for (Countries country : graph.getCountriesSet()) {
      if (source.equals(country.getName())) {
        sourceCountry = country;
      }

      if (destination.equals(country.getName())) {
        destinationCountry = country;
      }
    }

    // find the shortest path
    List<Countries> path = graph.fastestRoute(sourceCountry, destinationCountry);
    MessageCli.ROUTE_INFO.printMessage(path.toString());

    List<String> continentList = new LinkedList<>();

    for (Countries continents : path) {

      if (!continentList.contains(continents.getContinent())) {
        continentList.add(continents.getContinent());
      }
    }

    MessageCli.CONTINENT_INFO.printMessage(continentList.toString());
  }
}
