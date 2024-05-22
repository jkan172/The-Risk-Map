package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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
   * @param country The country to add.
   */
  public void addCountry(Countries country) {
    countriesSet.add(country);
    adjCountry.putIfAbsent(country, new HashSet<>());
  }

  /**
   * Gets a country by its name.
   *
   * @param name The name of the country.
   * @return The country with the given name.
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
   * @param country1 The first input country.
   * @param country2 The second input country.
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
  public Countries[] getCountriesSet() {
    return countriesSet.toArray(new Countries[0]);
  }

  public List<Countries> breathFirstTraversal(
      Countries sourceCountry, Countries destinationCountry) {
    if (!adjCountry.containsKey(sourceCountry) || !adjCountry.containsKey(destinationCountry)) {
      return null;
    }

    List<Countries> visited = new ArrayList<>();
    Queue<Countries> queue = new LinkedList<>();
    Map<Countries, Countries> parent = new HashMap<>();

    queue.add(sourceCountry);
    visited.add(sourceCountry);
    parent.put(sourceCountry, null);

    // visited.add(rootCountry);
    while (!queue.isEmpty()) {
      Countries current = queue.poll();

      if (current.equals(destinationCountry)) {
        List<Countries> path = new ArrayList<>();
        for (Countries country = destinationCountry;
            country != null;
            country = parent.get(country)) {
          path.add(country);
        }
        Collections.reverse(path);
        return path;
      }

      for (Countries n : adjCountry.get(current)) {
        if (!visited.contains(n)) {
          visited.add(n);
          parent.put(n, current);
          queue.add(n);
        }
      }

      // if (!visited.contains(current)) {
      //   visited.add(current);
      //   for (Countries n : adjCountry.get(current)) {
      //     if (!visited.contains(n)) {
      //       parent.put(n, current);
      //       queue.add(n);
      //     } else if (!n.equals(parent.get(current)) && n.equals(sourceCountry)) {
      //       List<Countries> path = new ArrayList<>();
      //       path.add(n);
      //       Countries temp = current;
      //       while (temp != null) {
      //         path.add(temp);
      //         temp = parent.get(temp);
      //         if (temp != null && temp.equals(n)) {
      //           path.add(temp);
      //           break;
      //         }
      //       }
      //       Collections.reverse(path);
      //       return path;
      //     }
      //   }
      // }
    }
    // return visited;
    return null;
  }
}
