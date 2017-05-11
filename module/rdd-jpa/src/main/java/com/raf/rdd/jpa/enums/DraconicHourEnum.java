package com.raf.rdd.jpa.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum for Draconic Hours.
 * 
 * @author RAF
 */
@Getter
@RequiredArgsConstructor
public enum DraconicHourEnum {
  /** The Vessel hour. */
  VESSEL("Vaisseau"),
  /** The Siren hour. */
  SIREN("Sirène"),
  /** The Falcon hour. */
  FALCON("Faucon"),
  /** The Crown hour. */
  CROWN("Couronne"),
  /** The Dragon hour. */
  DRAGON("Dragon"),
  /** The Swords hour. */
  SWORDS("Épées"),
  /** The Lyre hour. */
  LYRE("Lyre"),
  /** The Snake hour. */
  SNAKE("Serpent"),
  /** The Acrobat Fish hour. */
  ACROBAT_FISH("Poisson Acrobate"),
  /** The Spider hour. */
  SPIDER("Araignée"),
  /** The Reed hour. */
  REED("Roseau"),
  /** The Sleeping Castle hour. */
  SLEEPING_CASTLE("Château Dormant");

  /**
   * Map for enum conversion.
   */
  private static final Map<String, DraconicHourEnum> DRACONIC_HOURS = new HashMap<>(
      DraconicHourEnum.values().length);
  static {
    for (DraconicHourEnum draconicHourEnum : DraconicHourEnum.values()) {
      DRACONIC_HOURS.put(draconicHourEnum.name, draconicHourEnum);
    }
  }

  /** Name for the enum. */
  private final String name;

  /**
   * Return the enum corresponding to the name.
   * 
   * @param name
   *          Name of the enum
   * @return the enum.
   */
  public static DraconicHourEnum get(final String name) {
    return DRACONIC_HOURS.get(name);
  }

}
