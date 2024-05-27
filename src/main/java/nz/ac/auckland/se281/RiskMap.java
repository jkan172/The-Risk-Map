package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/** Represents the map of the Risk game. */
public class RiskMap {
  private Map<Countries, Set<Countries>> adjCountry;

  /** Constructor for the RiskMap. */
  public RiskMap() {
    this.adjCountry = new HashMap<>();
  }

  /**
   * Adds a country to the map.
   *
   * @param country The country to add.
   */
  public void addCountry(Countries country) {
    adjCountry.putIfAbsent(country, new LinkedHashSet<>());
  }

  /**
   * Gets a country by its name.
   *
   * @param name The name of the country.
   * @return The country with the given name.
   */
  public Countries getCountryByName(String name) {
    for (Countries country : adjCountry.keySet()) {
      if (country.getName().equals(name)) {
        return country;
      }
    }
    return null;
  }

  /**
   * Adds an adjacency between two countries.
   *
   * @param country1 The first input country.
   * @param country2 The second input country.
   */
  public void addAdjacency(Countries country1, Countries country2) {
    // Add the countries if they are not already in the map.
    addCountry(country1);
    addCountry(country2);
    // Add the adjacency.
    adjCountry.get(country1).add(country2);
  }

  /**
   * Removes a country from the map.
   *
   * @param country The country to remove.
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
   * @param country1 The first input country.
   * @param country2 The second input country.
   */
  public void removeAdjacency(Countries country1, Countries country2) {
    adjCountry.getOrDefault(country1, new HashSet<>()).remove(country2);
    adjCountry.getOrDefault(country2, new HashSet<>()).remove(country1);
  }

  /**
   * Gets the adjacent countries of a country.
   *
   * @param country The input country.
   * @return The set of adjacent countries.
   */
  public Set<Countries> getAdjCountry(Countries country) {
    return adjCountry.getOrDefault(country, new HashSet<>());
  }

  /**
   * Gets the set of countries in the map.
   *
   * @return The set of countries.
   */
  public Set<Countries> getCountriesSet() {
    return adjCountry.keySet();
  }

  /**
   * Gets the shortest path between two countries.
   *
   * @param sourceCountry
   * @param destinationCountry
   * @return
   */
  public List<Countries> shortestPath(Countries sourceCountry, Countries destinationCountry) {
    if (!adjCountry.containsKey(sourceCountry) || !adjCountry.containsKey(destinationCountry)) {
      return null;
    }

    Set<Countries> visited = new HashSet<>();
    Queue<Countries> queue = new LinkedList<>();
    Map<Countries, Countries> parent = new HashMap<>();

    queue.add(sourceCountry);
    visited.add(sourceCountry);
    parent.put(sourceCountry, null);

    // while the queue is not empty operate the BFS algorithm
    while (!queue.isEmpty()) {
      Countries currentCountry = queue.poll();

      // if the destination country is reached, return the path
      if (currentCountry.equals(destinationCountry)) {
        List<Countries> path = new ArrayList<>();
        for (Countries country = destinationCountry;
            country != null;
            country = parent.get(country)) {
          path.add(country);
        }
        Collections.reverse(path);
        return path;
      }

      // add the adjacent countries to the queue
      for (Countries n : adjCountry.get(currentCountry)) {
        if (!visited.contains(n)) {
          visited.add(n);
          parent.put(n, currentCountry);
          queue.add(n);
        }
      }
    }

    return null;
  }
}
