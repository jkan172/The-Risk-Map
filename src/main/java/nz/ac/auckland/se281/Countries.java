package nz.ac.auckland.se281;

/** Represents a country in the Risk game. */
public class Countries {
  private String name;
  private String continent;
  private int tax;

  /**
   * Constructor for the country.
   *
   * @param name The name of the country.
   * @param continent The continent of the country.
   * @param tax The tax of the country.
   */
  public Countries(String name, String continent, int tax) {
    this.name = name;
    this.continent = continent;
    this.tax = tax;
  }

  @Override
  public String toString() {
    return String.format(name);
  }

  /** Returns the hash code of the country. */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    // Check if the name, continent, and tax is null and generate hash code.
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((continent == null) ? 0 : continent.hashCode());
    result = prime * result + tax;
    return result;
  }

  /** Checks if two countries are equal. */
  @Override
  public boolean equals(Object obj) {
    // Check if the object is the same as the country.
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Countries other = (Countries) obj;
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }

    return true;
  }

  /**
   * Returns the name of the country.
   *
   * @return The name of the country.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the continent of the country.
   *
   * @return The continent of the country.
   */
  public String getContinent() {
    return continent;
  }

  /**
   * Returns the tax of the country.
   *
   * @return The tax of the country.
   */
  public int getTax() {
    return tax;
  }
}
