package nz.ac.auckland.se281;

import java.util.List;

/** This class is the main entry point. */
public class MapEngine {

  private String[] countriesArray;

  public MapEngine() {
    // add other code here if you want
    loadMap(); // keep this mehtod invocation
  }

  /** invoked one time only when constracting the MapEngine class. */
  private void loadMap() {
    List<String> countries = Utils.readCountries();
    List<String> adjacencies = Utils.readAdjacencies();
    // add code here to create your data structures

    Graph graph = new Graph();


    for (String country : countries) {
      // graph.addCountry(country);
      String[] countryArray = country.split(",");
      Countries newCountry = new Countries(countryArray[0], countryArray[1], Integer.parseInt(countryArray[2]));
    }

    // for (String adjacency : adjacencies) {
    //   String[] adjacencyArray = adjacency.split(",");
    //   graph.addAdjacency(adjacencyArray[0], adjacencyArray[1]);
    // }

    // countriesArray = countries.split(",");

    // Map<String, Countries> countriesMap = new HashMap<>();



  }

  /** this method is invoked when the user run the command info-country. */
  public void showInfoCountry() {
    
  }

  /** this method is invoked when the user run the command route. */
  public void showRoute() {}
}
