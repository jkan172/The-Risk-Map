package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
  private Map<Countries, List<Countries>> adjacencies;

  public Graph() {
    adjacencies = new HashMap<>();
  }

  public void addCountry(Countries country) {
    adjacencies.putIfAbsent(country, new ArrayList<>());
  }

  public void addAdjacency(Countries country1, Countries country2) {
    addCountry(country1);
    addCountry(country2);
    adjacencies.get(country1).add(country2);
    adjacencies.get(country2).add(country1);
  }

  public void removeCountry(Countries country) {
    adjacencies.remove(country);
    for (Countries key : adjacencies.keySet()) {
      adjacencies.get(key).remove(country);
    }
  }

  public void removeAdjacency(Countries country1, Countries country2) {
    if (adjacencies.containsKey(country1) && adjacencies.containsKey(country2)) {
      adjacencies.getOrDefault(country1, new ArrayList<>()).remove(country2);
      adjacencies.getOrDefault(country2, new ArrayList<>()).remove(country1);
    }

    //       public String toString() {
    //    StringBuilder sb = new StringBuilder();
    //    for (Countries n : adjacencies.keySet()) {
    //      sb.append(n);
    //      sb.append("->");
    //      sb.append(adjacencies.get(n));
    //      sb.append(System.lineSeparator());
    //    }
    //    return sb.toString();
    //  }
  }
}
