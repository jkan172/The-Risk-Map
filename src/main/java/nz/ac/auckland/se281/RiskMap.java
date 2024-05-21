package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** Represents the map of the Risk game. */
public class RiskMap {
  private Set<Countries> countriesSet;
  private Map<Countries, Set<Countries>> adjCountry;

  /** Constructor for the RiskMap. */
  public RiskMap() {
    this.countriesSet = new HashSet<>();
    this.adjCountry = new HashMap<>();
  }

  /**
   * Adds a country to the map.
   *
   * @param country
   */
  public void addCountry(Countries country) {
    countriesSet.add(country);
    adjCountry.putIfAbsent(country, new HashSet<>());
  }

  /**
   * Gets a country by its name.
   *
   * @param name
   * @return
   */
  public Countries getCountryByName(String name) {
    for (Countries country : countriesSet) {
      if (country.getName().equals(name)) {
        return country;
      }
    }
    return null;
  }

  /**
   * Adds an adjacency between two countries.
   *
   * @param country1
   * @param country2
   */
  public void addAdjacency(Countries country1, Countries country2) {
    // Add the countries if they are not already in the map.
    addCountry(country1);
    addCountry(country2);
    // Add the adjacency.
    adjCountry.get(country1).add(country2);
    adjCountry.get(country2).add(country1);
  }

  /**
   * Removes a country from the map.
   *
   * @param country
   */
  public void removeCountry(Countries country) {
    adjCountry.remove(country);
    for (Countries key : adjCountry.keySet()) {
      adjCountry.get(key).remove(country);
    }
  }

  /**
   * Removes an adjacency between two countries.
   *
   * @param country1
   * @param country2
   */
  public void removeAdjacency(Countries country1, Countries country2) {
    adjCountry.getOrDefault(country1, new HashSet<>()).remove(country2);
    adjCountry.getOrDefault(country2, new HashSet<>()).remove(country1);
  }

  /**
   * Gets the adjacent countries of a country.
   *
   * @param country
   * @return
   */
  public Set<Countries> getAdjCountry(Countries country) {
    return adjCountry.get(country);
  }

  /**
   * Gets the set of countries in the map.
   *
   * @return
   */
  public Countries[] getCountriesSet() {
    return countriesSet.toArray(new Countries[0]);
  }
}
