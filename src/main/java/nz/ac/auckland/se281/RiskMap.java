package nz.ac.auckland.se281;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RiskMap {
  private Set<Countries> countriesSet;
  private Map<Countries, Set<Countries>> adjCountry;

  public RiskMap() {
    this.countriesSet = new HashSet<>();
    this.adjCountry = new HashMap<>();
  }

  public void addCountry(Countries country) {
    countriesSet.add(country);
    adjCountry.putIfAbsent(country, new HashSet<>());
  }

  public Countries getCountryByName(String name) {
    for (Countries country : countriesSet) {
      if (country.getName().equals(name)) {
        return country;
      }
    }
    return null;
  }

  public void addAdjacency(Countries country1, Countries country2) {
    addCountry(country1);
    addCountry(country2);
    adjCountry.get(country1).add(country2);
    adjCountry.get(country2).add(country1);
  }

  public void removeCountry(Countries country) {
    adjCountry.remove(country);
    for (Countries key : adjCountry.keySet()) {
      adjCountry.get(key).remove(country);
    }
  }

  public void removeAdjacency(Countries country1, Countries country2) {
    adjCountry.getOrDefault(country1, new HashSet<>()).remove(country2);
    adjCountry.getOrDefault(country2, new HashSet<>()).remove(country1);
  }

  public Set<Countries> getAdjCountry(Countries country) {
    return adjCountry.get(country);
  }

  public Countries[] getCountriesSet() {
    return countriesSet.toArray(new Countries[0]);
  }

  //       public String toString() {
  //    StringBuilder sb = new StringBuilder();
  //    for (Countries n : map.keySet()) {
  //      sb.append(n);
  //      sb.append("->");
  //      sb.append(map.get(n));
  //      sb.append(System.lineSeparator());
  //    }
  //    return sb.toString();
  //  }

}
